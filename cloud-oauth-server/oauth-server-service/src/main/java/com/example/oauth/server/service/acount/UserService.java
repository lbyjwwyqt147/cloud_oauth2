package com.example.oauth.server.service.acount;


import com.example.oauth.server.domain.account.User;

public interface UserService {

     User getUser(String name);

     User saveUser(String name, Integer age, String address);
}
