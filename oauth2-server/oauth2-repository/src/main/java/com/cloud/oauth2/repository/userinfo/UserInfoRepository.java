package com.cloud.oauth2.repository.userinfo;

import com.cloud.oauth2.userinfo.entity.UserInfoDO;
import org.springframework.data.jpa.repository.JpaRepository;

/***
 * 用户信息
 */
public interface UserInfoRepository extends JpaRepository<UserInfoDO,Long> {


}
