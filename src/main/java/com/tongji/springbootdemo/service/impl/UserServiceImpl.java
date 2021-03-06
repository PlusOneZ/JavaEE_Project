package com.tongji.springbootdemo.service.impl;

import com.tongji.springbootdemo.mapper.UserMapper;
import com.tongji.springbootdemo.model.User;
import com.tongji.springbootdemo.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import org.yaml.snakeyaml.external.biz.base64Coder.Base64Coder;

import java.io.IOException;
import java.sql.Timestamp;
import java.util.Base64;
import java.util.List;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserMapper userMapper;

    public List<User> findAll() {
        return userMapper.findAll();
    }

    public int addUser(String nickname, String email, String password, Timestamp registrationTime) {
        return userMapper.addUser(nickname, email, password, registrationTime);
    }

    public boolean updateImage(Integer userId, MultipartFile file) throws IOException {
        Base64.Encoder base64Coder = Base64.getEncoder();
        String base64Image = base64Coder.encodeToString(file.getBytes());
        System.out.println(base64Image);
        return userMapper.updateImage(userId, base64Image);
    }

    public boolean updateImageByString(Integer userId, String avatar) {
        return userMapper.updateImage(userId, avatar);
    }

    public User findById(Integer userId) {
        if (userMapper.findById(userId).isEmpty()) {
            return null;
        }
        return userMapper.findById(userId).get(0);
    }

    public User findByEmail(String email) {
        if (userMapper.findByEmail(email).isEmpty()) {
            return null;
        }
        return userMapper.findByEmail(email).get(0);
    }

    public int deleteUser(String email) {
        return userMapper.deleteUser(email);
    }
}
