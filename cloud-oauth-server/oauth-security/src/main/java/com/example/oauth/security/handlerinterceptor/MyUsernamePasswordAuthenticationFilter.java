package com.example.oauth.security.handlerinterceptor;
import com.example.oauth.server.common.exception.ErrorCodeEnum;
import com.example.oauth.server.common.restful.ResultUtil;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/***
 *  自定义 登录时认证
 */
@Slf4j
public class MyUsernamePasswordAuthenticationFilter extends UsernamePasswordAuthenticationFilter {

    public  MyUsernamePasswordAuthenticationFilter(AuthenticationManager authenticationManager){
       // this.setFilterProcessesUrl("/auth/v1/api/login/entry");  //这句代码很重要，设置登陆的url 要和 WebSecurityConfig 配置类中的.loginProcessingUrl("/auth/v1/api/login/entry") 一致，如果不配置则无法执行 重写的attemptAuthentication 方法里面而是执行了父类UsernamePasswordAuthenticationFilter的attemptAuthentication（）
        this.setAuthenticationManager(authenticationManager);
    }

    @Override
    public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response) throws AuthenticationException {
        //校验验证码
        String verifyCode = request.getParameter("verifyCode");
        if(!checkValidateCode(verifyCode)){
            ResultUtil.writeJavaScript(response,ErrorCodeEnum.FAIL,"验证码错误.");
            return null;
        }
        //设置获取 username 的属性字段   js传到后台接收数据的参数名
        this.setUsernameParameter("userAccount");
        //设置获取password 的属性字段  js传到后台接收数据的参数名
        this.setPasswordParameter("userPwd");
        String username = this.obtainUsername(request);
        String password = this.obtainPassword(request);
        if (username == null) {
            username = "";
        }

        if (password == null) {
            password = "";
        }

        username = username.trim();
        UsernamePasswordAuthenticationToken authRequest = new UsernamePasswordAuthenticationToken(username, password);
        this.setDetails(request, authRequest);
        return this.getAuthenticationManager().authenticate(authRequest);
       // return super.attemptAuthentication(request, response);
    }

    /**
     * 验证 验证码是否正确
     * @param verifyCode
     * @return
     */
    private boolean checkValidateCode(String verifyCode){
        if(StringUtils.isBlank(verifyCode) || !verifyCode.trim().equals("1234")){
          return false;
        }
        return true;
    }
}
