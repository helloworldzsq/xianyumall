package com.dz.config;

import ch.qos.logback.classic.spi.EventArgUtil;
import com.dz.pojo.User;
import com.dz.service.UserService;
import com.dz.service.impl.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import java.util.List;

@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {
    @Autowired
    private UserServiceImpl userService;
    //授权
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        //为了保护您的安全，localhost 将不允许 Firefox 显示嵌入了其他网站的页面。
        // 要查看此页面，请在新窗口中打开。
        // 同源头
        http.headers().frameOptions().sameOrigin();
        http.authorizeRequests().
                antMatchers("/").permitAll()   //任何人都可以访问首页
                .antMatchers("/level1/**").hasRole("vip1"); //vip1等级的可以访问level1
        //没有权限，默认回到登录页面
        http.formLogin().loginPage("/toLogin").loginProcessingUrl("/login").defaultSuccessUrl("/");
//        http.formLogin();
        //注销成功后 返回首页 而不是登录页面
//        http.logout();
        http.logout().logoutSuccessUrl("/");
//        实现记住我功能
//        http.rememberMe();
//        实现自定义的记住我功能 name="remember"
        http.rememberMe().rememberMeParameter("remember");
    }
    //认证
    @Override    //passwordEncoder(new BCryptPasswordEncoder()) 对密码进行加密，否则会报错
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        //下边的数据应该从数据库中获取
        List<User> list = userService.list();
        for (User user : list) {
            auth.inMemoryAuthentication().passwordEncoder(new BCryptPasswordEncoder())
                    .withUser(user.getUsername()).password(new
                    BCryptPasswordEncoder().encode(user.getPassword())).roles("vip1");
        }
//        auth.inMemoryAuthentication().passwordEncoder(new BCryptPasswordEncoder())
//                .withUser("dz").password(new BCryptPasswordEncoder().encode("123456")).roles("vip1","vip2","vip3")
//                .and()
//                .withUser("zsq").password(new BCryptPasswordEncoder().encode("123456")).roles("vip1");
    }
}
