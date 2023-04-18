package com.izejs.simple.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.izejs.simple.dto.UserDTO;
import com.izejs.simple.entity.User;
import com.izejs.simple.mapper.UserMapper;
import com.izejs.simple.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author KunKa
 * @since
 */
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements IUserService {

    @Autowired
    private UserMapper userMapper;

    @Override
    public User selectUserByUserName(String userName) {
        return userMapper.selectOne(new QueryWrapper<User>().eq("user_name", userName));
    }

    /**
     * 根据条件查询所有的用户信息
     * @param page
     * @param user
     * @return
     */
    @Override
    public IPage<User> getAllUser(Page page, UserDTO user) {
        LambdaQueryWrapper<User> queryWrapper = new LambdaQueryWrapper<> ();
        queryWrapper.ne(User::getRole, 1);
        if(user.getUserName() != null && !"".equals(user.getUserName())){
            queryWrapper = queryWrapper.like(User::getUserName, user.getUserName());
        }

        return userMapper.selectPage(page, queryWrapper);
    }
}
