package com.example.oauth.server.repository.account;

import com.example.oauth.server.domain.account.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;


public interface UserRepository extends JpaRepository<User, Long> {
    User findByName(String name);

    @Query("select u from User u where u.name=:name")
    User findUser(@Param("name") String name);
}
