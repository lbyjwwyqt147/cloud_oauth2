package com.example.oauth.server.service.acount.impl;


import com.example.oauth.server.domain.account.User;
import com.example.oauth.server.repository.account.UserRepository;
import com.example.oauth.server.service.acount.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    UserRepository userRepository;

    public User getUser(String name){
        return userRepository.findByName(name);
    }

    @Transactional
    public User saveUser(String name,Integer age, String address){
        User user = new User(name, age, address);
        return userRepository.save(user);
    }
}
