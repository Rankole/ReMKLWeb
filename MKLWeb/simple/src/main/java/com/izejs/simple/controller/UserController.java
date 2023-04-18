package com.izejs.simple.controller;
//gitest
//gittest2
//gitest3
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.api.R;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.izejs.simple.dto.UserDTO;
import com.izejs.simple.entity.User;
import com.izejs.simple.util.MailUtil;
import com.izejs.simple.service.IUserService;
import com.wf.captcha.utils.CaptchaUtil;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.Random;
import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.net.URLDecoder;
import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.concurrent.atomic.*;


@Controller
public class UserController {

    @Resource
    private IUserService userService;
    @Resource
    private MailUtil mailUtil;

    /**
     * 用户的登录
     * @param user
     * @param session
     * @param verCode
     * @return
     */
    @PostMapping("/api/login")
    @ResponseBody
    public R login(User user, String verCode, HttpSession session, HttpServletRequest request){
        // 判断验证码是否正确
        if (!CaptchaUtil.ver(verCode, request)) {
            return R.failed("验证码不正确");
        }
        // 根据用户名查询用户
        User loginUser = userService.selectUserByUserName(user.getUserName());
        // 如果查询用户为空, 则该用户不存在
        if(loginUser == null){
            return R.failed("该用户不存在!");
        // 如果查询用户不为空, 则将查询出来的密码和传入的密码对比
        }else if(!loginUser.getPassword().equals(user.getPassword())){
            return R.failed("用户名或密码错误!");
        }else if(loginUser.getStatus() == 0){
            // 判断用户状态是否是被禁用
            return R.failed("该用户已被禁用!");
        }else{
            // 登录成功, 将用户对象存入session中
            session.setAttribute("loginUser", loginUser);
            return R.ok("登录成功!");
        }
    }

    /**
     * 用户注册
     * @param session
     * @param request
     * @return
     */
    @PostMapping("/api/register")
    @ResponseBody
    public R register(User userModel, String code, HttpSession session, HttpServletRequest request){

        User loginUser = (User) session.getAttribute("loginUser");
        // session中获取验证码
        String sessionCode = (String) session.getAttribute("code");
        if(loginUser == null || loginUser.getRole() != 1){
            if(code == null || !code.equals(sessionCode)){
                return R.failed("验证码不正确!");
            }
        }
        String userName = userModel.getUserName();
        String password = userModel.getPassword();
        User user = userService.getOne(new QueryWrapper<User>().eq("user_name", userName));
        // 判断该用户名是否已存在
        if(user != null){
            return R.failed("该用户名已存在!");
        }else{
            User inserUser = new User();
            inserUser.setUserName(userName);
            inserUser.setPassword(password);
            inserUser.setPhone(userModel.getPhone());
            inserUser.setAddress(userModel.getAddress());
            inserUser.setRole(2);
            userService.save(inserUser);
        }
        return R.ok(null);
    }

    /**
     * 发送验证码功能
     * @return
     */
    @PostMapping("/api/sendEmail")
    @ResponseBody
    public R sendEmail(String toMailUser, HttpSession session) {
        Random random = new Random();
        String code = "";
        // 生成6位随机码
        for(int i=0; i<6; i++){
            code += random.nextInt(9);
        }
        LocalDateTime now = LocalDateTime.now();
        LocalDateTime oldSendTime = (LocalDateTime) session.getAttribute("sendCodeTime");
        if(oldSendTime == null || now.isAfter(oldSendTime.plusMinutes(1))){
            // 设置到session中
            session.setAttribute("code", code);
            session.setAttribute("sendCodeTime", LocalDateTime.now());
        }else{
            return R.failed("请勿频繁发送验证码!(间隔60秒可再次发送)");
        }

        try {
            mailUtil.setToMailUser(toMailUser);
            mailUtil.setSubject("洗衣机自助平台验证码");
            mailUtil.setContent(code);
            mailUtil.sendSimpleMail();
            System.out.println(code);
        }catch (Exception e){
            return R.failed("发送失败! 请确认您的邮箱地址是否正确!");
        }
        return R.ok("发送成功!");
    }

    /**
     * 退出登录功能
     * @param request
     * @return
     */
    @GetMapping("/api/logout")
    public String logout(HttpServletRequest request){
        request.getSession().removeAttribute("loginUser");
        return "/login/login";
    }

    /**
     * 生成验证码图片
     * @param request
     * @param response
     * @throws Exception
     */
    @RequestMapping("/captcha")
    public void captcha(HttpServletRequest request, HttpServletResponse response) throws Exception {
        CaptchaUtil.out(130, 35, 4, request, response);
    }

    /**
     * 获取当前登录用户的具体信息
     * @param session
     */
    @GetMapping("/api/getLoginUserInfo")
    @ResponseBody
    public R getLoginUserInfo(HttpSession session){
        User user = (User)session.getAttribute("loginUser");
        user = userService.getById(user.getId());
        return R.ok(user);
    }

    /**
     * 显示头像
     * @param request
     * @param response
     * @param session
     * @throws IOException
     */
    @GetMapping("/showAvatar")
    public void showAvatar(Integer userId, HttpServletRequest request, HttpServletResponse response, HttpSession session) throws IOException {
        // 设置响应头
        response.setContentType("text/html; charset=UTF-8");
        response.setContentType("image/jpeg");
        String filePath = Thread.currentThread().getContextClassLoader().getResource("").getPath();
        filePath = filePath + "/static/images/logo.png";
        filePath = URLDecoder.decode(filePath, "UTF-8");
        FileInputStream fis = null;
        OutputStream os = null;
        try {
            fis = new FileInputStream(filePath);
            os = response.getOutputStream();
            int count = 0;
            byte[] buffer = new byte[1024 * 1024];
            while ((count = fis.read(buffer)) != -1){
                os.write(buffer, 0, count);
            }
            os.flush();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (os != null)
                os.close();
            if (fis != null)
                fis.close();
        }
    }

    /**
     * 新增用户信息
     * @param user
     * @param session
     * @return
     */
    @PostMapping("/api/addUser")
    @ResponseBody
    public R addUser(User user, HttpSession session){
        userService.save(user);
        return R.ok(null);
    }

    /**
     * 根据id批量删除用户信息
     * @param session
     * @param userIds
     * @return
     */
    @DeleteMapping("/api/deleteUserByIds/{userIds}")
    @ResponseBody
    public R deleteUserByIds(HttpSession session, @PathVariable String userIds){
        // 将批量删除用户信息的id根据逗号切割成数组
        String[] ids = userIds.split(",");
        userService.removeByIds(Arrays.asList(ids));
        return R.ok(null);
    }


    /**
     * 根据用户信息id获取详情
     * @param userId
     * @return
     */
    @GetMapping("/api/getUserById")
    @ResponseBody
    public R getUserById(Integer userId, HttpSession session){
        if(userId == null){
            User loginUser = (User)session.getAttribute("loginUser");
            userId = loginUser.getId();
        }
        User user = userService.getById(userId);
        return R.ok(user);
    }

    /**
     * 查询所有的user
     * @param session
     * @param userDTO
     * @param page
     * @return
     */
    @GetMapping("/api/getUserList")
    @ResponseBody
    public R getUserList(HttpSession session, UserDTO userDTO, Page page){

        IPage<User> userPage = userService.getAllUser(page, userDTO);
        return R.ok(userPage);
    }


    /**
     * 修改用户信息
     * @param user
     * @return
     */
    @PostMapping("/api/updateUser")
    @ResponseBody
    public R updateUser(User user, HttpSession session){
        if(user.getId() == null){
            user.setId(((User)session.getAttribute("loginUser")).getId());
        }
        userService.updateById(user);
        return R.ok(null);
    }
}
