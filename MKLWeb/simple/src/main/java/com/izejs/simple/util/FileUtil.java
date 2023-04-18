package com.izejs.simple.util;

import com.baomidou.mybatisplus.extension.api.R;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.time.LocalDateTime;
import java.util.UUID;

public class FileUtil {

    public R uploadFile(MultipartFile file) {
        if (file.isEmpty()) {
            return R.failed("上传失败!请不要上传空文件!");
        }
        String fileName = file.getOriginalFilename();
        // 设置文件保存路径
        String filePath = Thread.currentThread().getContextClassLoader().getResource("").getPath() + "uploadFile/";
        // 使用uuid做为文件夹名, 生成文件夹
        filePath = filePath + UUID.randomUUID() + "/";
        File dir = new File(filePath);
        dir.mkdirs();
        File dest = new File(filePath + fileName);
        try {
            file.transferTo(dest);
            return R.ok(null);
        } catch (IOException e) {
            return R.failed(e.getMessage());
        }
    }

    public void downloadFile(String filePath, String fileName, HttpServletRequest request, HttpServletResponse response) throws UnsupportedEncodingException {
        if (filePath != null) {
            //设置文件路径
            File file = new File(filePath + fileName);
            // 设置强制下载不打开
            response.setContentType("application/force-download");
            // 设置文件名
            response.addHeader("Content-Disposition", "attachment;fileName=" + new String(fileName.getBytes("UTF-8"), "ISO8859-1"));
            byte[] buffer = new byte[1024];
            FileInputStream fis = null;
            BufferedInputStream bis = null;
            try {
                // 将文件字节流写入输出流
                fis = new FileInputStream(file);
                bis = new BufferedInputStream(fis);
                OutputStream os = response.getOutputStream();
                int i = bis.read(buffer);
                while (i != -1) {
                    os.write(buffer, 0, i);
                    i = bis.read(buffer);
                }
            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                if (bis != null) {
                    try {
                        bis.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
                if (fis != null) {
                    try {
                        fis.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }
        }
    }
}
