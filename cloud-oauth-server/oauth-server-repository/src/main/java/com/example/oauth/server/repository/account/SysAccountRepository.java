package com.example.oauth.server.repository.account;

import com.example.oauth.server.domain.account.entity.SysAccount;
import com.example.oauth.server.domain.account.vo.UserInfoVO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

/***
 * 账户  Repository
 */
public interface SysAccountRepository  extends JpaRepository<SysAccount,Long> {

    /**
     * 根据账户获取账户信息
     * @param userAccount 账户
     * @return
     */
    SysAccount findByUserAccount(String userAccount);

    /**
     * 根据 账户和密码获取账户信息
     * @param userAccount  账户
     * @param userPwd  密码
     * @return
     */
    SysAccount findByUserAccountAndUserPwd(String userAccount,String userPwd);

    /**
     * 根据 账户和注册邮箱获取账户信息
     * @param userAccount  账户
     * @param userEmail   注册邮箱
     * @return
     */
    SysAccount findByUserAccountAndUserEmail(String userAccount,String userEmail);

    /**
     *  根据 roleId 获取 角色人员 （人员角色表：sys_user_role 内链接查询  ）
     * @param roleId
     * @param pageable
     * @return
     */
    @Query(value = "SELECT a.user_email as userEmail,a.id as  id,u.user_name as  userName FROM (sys_account a inner join sys_user_role r  on a.id = r.user_id ) inner join user_info u on  a.id = u.account_id where r.role_id = :roleId ",
            countQuery = "SELECT count(*)  FROM (sys_account a inner join sys_user_role r  on a.id = r.user_id )  inner join user_info u on  a.id = u.account_id where r.role_id = :roleId",
            nativeQuery = true)
    Page<UserInfoResultSets> findPageByRoleId(@Param("roleId") Long roleId, Pageable pageable);

    /**
     *  根据 roleId 获取 角色未授权的人员 (人员角色表：sys_user_role  )
     * @param roleId
     * @param pageable
     * @return
     */
    @Query(value = "SELECT a.user_email as userEmail,a.id as  id,u.user_name as  userName FROM sys_account a  inner join user_info u on  a.id = u.account_id WHERE not exists (select user_id FROM sys_user_role r  WHERE a.id = r.user_id AND r.role_id = :roleId)",
            countQuery = "SELECT count(*)  FROM sys_account a  inner join user_info u on  a.id = u.account_id WHERE not exists (select user_id FROM sys_user_role r  WHERE a.id = r.user_id AND r.role_id = :roleId)",
            nativeQuery = true)
    Page<UserInfoResultSets> findPageByRoleIdEliminate(@Param("roleId") Long roleId, Pageable pageable);
}
