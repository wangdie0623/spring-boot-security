package com.demo.springboot.security.config;


import org.springframework.context.annotation.Bean;
import org.springframework.security.authentication.encoding.Md5PasswordEncoder;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.security.web.csrf.CsrfFilter;
import org.springframework.security.web.session.InvalidSessionStrategy;
import org.springframework.web.filter.CharacterEncodingFilter;


@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {


    /**
     * 初始化配置
     *
     * @param web
     * @throws Exception
     */
    @Override
    public void configure(WebSecurity web) throws Exception {
        web.ignoring().antMatchers("/css/**");//设置放行静态资源
        web.ignoring().antMatchers("/js/**");
        web.ignoring().antMatchers("/fonts/**");
        web.ignoring().antMatchers("/images/**");
        web.ignoring().antMatchers("/403.html");
    }

    /**
     * 用户认证配置
     *
     * @param auth
     * @throws Exception
     */
    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(createUserDetailService())//设置用户服务
                .passwordEncoder(new Md5PasswordEncoder())//设置请求密码加密规则
                .and();
    }

    /**
     * 安全规则配置
     *
     * @param http
     * @throws Exception
     */
    @Override
    protected void configure(HttpSecurity http) throws Exception {

        http
                /*  额外登录方式配置
                  .apply(qqLoginConfigure())
                  .and()*/

                .formLogin()//登录配置
                .loginPage("/index.jsp")//登录页面地址
                .loginProcessingUrl("/login")//登录验证地址
                .usernameParameter("username")//用户名key指定
                .passwordParameter("password")//密码key指定
                .successHandler(createLoginSuccessHandler())//登录成功钩子
                .failureHandler(createAuthenticationFailureHandler())//登录失败钩子
                .and()

                .sessionManagement()//会话配置
                .invalidSessionUrl("/index.jsp")//会话失效跳转地址
                .invalidSessionStrategy(simpleAjaxAndRedirectInvalidSessionStrategy())//会话失效钩子
                .and()

                .logout()//注销配置
                .logoutUrl("/logout")//注销地址
                .clearAuthentication(true)//注销后清空用户信息
                .invalidateHttpSession(true)//注销后清空会话
                .and()

                .csrf().disable()//禁用跨站伪造验证
                .authorizeRequests()//配置授权地址
                .antMatchers(//配置匹配列表
                        "/favicon.ico",
                        "/index.jsp",
                        "/login",
                        "/"
                )
                .permitAll()//配置以上列表全部决策通过 其实就是放行，当然也可以不放行denyAll
                .anyRequest()//配置其他未匹配的地址
                //指定决策方法 格式:@bean名称.决策方法名(认证对象,请求对象)
                //决策成功 true 该地址可访问 决策失败 false 该地址该用户无权访问403
                .access("@accessStrategyHandler.decision(authentication,request)")
                .and()

                //该配置主要针对 前端用frame标签无法正常加载的问题
                .headers()//配置响应头
                .frameOptions()//配置frame
                .disable()//禁用
                .and()

                .exceptionHandling()//异常钩子配置
                .accessDeniedHandler(securityAccessDeniedHandler())//已认证用户的403钩子
                .authenticationEntryPoint(authenticationEntryPoint());//其他异常钩子
//        添加中文过滤
        CharacterEncodingFilter filter = new CharacterEncodingFilter();
        filter.setEncoding("UTF-8");
        filter.setForceEncoding(true);
        http.addFilterBefore(filter, CsrfFilter.class);

    }

    @Bean//创建用户认证服务
    public UserDetailsService createUserDetailService() {
        return new SecurityUserDetailServiceImpl();
    }

    @Bean//创建认证成功钩子
    public AuthenticationSuccessHandler createLoginSuccessHandler() {
        return new LoginSuccessHandlerImpl();
    }

    @Bean//创建认证失败钩子
    public AuthenticationFailureHandler createAuthenticationFailureHandler() {
        return new LoginFailHandlerImpl();
    }

    @Bean//创建已认证无权限403钩子
    public AccessDeniedHandler securityAccessDeniedHandler() {
        Http403Handler http403Handler = new Http403Handler();
        http403Handler.setErrorPage("/403.html");
        return http403Handler;
    }

    @Bean//创建其他错误钩子
    public AuthenticationEntryPoint authenticationEntryPoint() {
        return new SecurityErrorHandler("/index.jsp");
    }

    @Bean//创建会话失效钩子
    public InvalidSessionStrategy simpleAjaxAndRedirectInvalidSessionStrategy() {
        return new SessionInvalidHandler();
    }


}
