package com.wyz.coffee.config;

import com.wyz.coffee.config.filter.ShiroLoginFilter;
import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.authc.credential.HashedCredentialsMatcher;
import org.apache.shiro.mgt.SecurityManager;
import org.apache.shiro.spring.security.interceptor.AuthorizationAttributeSourceAdvisor;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.servlet.Filter;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

@Configuration
@Slf4j
public class ShiroConfig {

//    @Value("${redis.host}")
//    private String host;
//    @Value("${redis.timeout}")
//    private int timeout;


    /**
     * shiro缓存管理器;
     * 需要添加到securityManager中
     * @return
     */
//    @Bean
//    public RedisCacheManager cacheManager(){
//        RedisCacheManager redisCacheManager = new RedisCacheManager();
//        FastJsonRedisSerializer fastJsonRedisSerializer = new FastJsonRedisSerializer();
//        MyRedisSerializer myRedisSerializer = new MyRedisSerializer();
//        redisCacheManager.setRedisManager(redisManager());
//        //redis中针对不同用户缓存
//        redisCacheManager.setPrincipalIdFieldName("username");
//        //用户权限信息缓存时间
//        redisCacheManager.setExpire(200000);
//        //redisCacheManager.setKeySerializer(myRedisSerializer);
//        //redisCacheManager.setValueSerializer(myRedisSerializer);
//        return redisCacheManager;
//    }

//    public RedisManager redisManager(){
//        RedisManager redisManager = new RedisManager();
//        redisManager.setHost(host);
//        //redisManager.setTimeout(timeout);
//        return redisManager;
//    }

//    public SessionDAO sessionDAO() {
//        RedisSessionDAO redisSessionDAO = new RedisSessionDAO();
//        FastJsonRedisSerializer fastJsonRedisSerializer = new FastJsonRedisSerializer();
//        //redisSessionDAO.setKeySerializer(fastJsonRedisSerializer);
//        //redisSessionDAO.setValueSerializer(fastJsonRedisSerializer);
//        redisSessionDAO.setRedisManager(redisManager());
//        redisSessionDAO.setExpire(12000);
//        return redisSessionDAO;
//    }

//    public DefaultWebSessionManager sessionManager() {
//        DefaultWebSessionManager sessionManager = new DefaultWebSessionManager();
//        sessionManager.setSessionDAO(sessionDAO());
//        sessionManager.setCacheManager(cacheManager());
//        return sessionManager;
//    }

    @Bean
    public SecurityManager securityManager() {
        DefaultWebSecurityManager securityManager = new DefaultWebSecurityManager();
        securityManager.setRealm(userRealm());
//        securityManager.setSessionManager(sessionManager());
//        securityManager.setCacheManager(cacheManager());
        return securityManager;
    }

    @Bean
    public UserRealm userRealm() {
        UserRealm userRealm = new UserRealm();
        userRealm.setCredentialsMatcher(hashedCredentialsMatcher());
        //userRealm.setCachingEnabled(true);
        //启用身份验证缓存，即缓存AuthenticationInfo信息，默认false
       // userRealm.setAuthenticationCachingEnabled(true);
        //缓存AuthenticationInfo信息的缓存名称 在ehcache-shiro.xml中有对应缓存的配置
        //userRealm.setAuthenticationCacheName("authenticationCache");
        //启用授权缓存，即缓存AuthorizationInfo信息，默认false
        //userRealm.setAuthorizationCachingEnabled(true);
        //缓存AuthorizationInfo信息的缓存名称  在ehcache-shiro.xml中有对应缓存的配置
        //userRealm.setAuthorizationCacheName("authorizationCache");
        return userRealm;
    }

    @Bean
    public ShiroFilterFactoryBean shiroFilterFactoryBean(SecurityManager securityManager) {
        ShiroFilterFactoryBean shiroFilterFactoryBean = new ShiroFilterFactoryBean();
        Map<String, Filter> myFilter = new HashMap<>();
        myFilter.put("user", new ShiroLoginFilter());
        shiroFilterFactoryBean.setSecurityManager(securityManager);
        Map<String, String> filterMap = new LinkedHashMap<>();
        filterMap.put("/login", "anon");
        filterMap.put("/register", "anon");
//        filterMap.put("/**", "user");
        shiroFilterFactoryBean.setFilterChainDefinitionMap(filterMap);
        shiroFilterFactoryBean.setFilters(myFilter);
        return shiroFilterFactoryBean;
    }

    // 加入注解的使用，不加入这个注解不生效
    @Bean
    public AuthorizationAttributeSourceAdvisor authorizationAttributeSourceAdvisor(SecurityManager securityManager) {
        AuthorizationAttributeSourceAdvisor authorizationAttributeSourceAdvisor = new AuthorizationAttributeSourceAdvisor();
        authorizationAttributeSourceAdvisor.setSecurityManager(securityManager);
        return authorizationAttributeSourceAdvisor;
    }

    @Bean
    public HashedCredentialsMatcher hashedCredentialsMatcher() {
        HashedCredentialsMatcher hashedCredentialsMatcher = new HashedCredentialsMatcher();
        hashedCredentialsMatcher.setHashAlgorithmName("MD5");//散列算法:MD2、MD5、SHA-1、SHA-256、SHA-384、SHA-512等。
        hashedCredentialsMatcher.setHashIterations(2);//散列的次数，默认1次， 设置两次相当于 md5(md5(""));
        return hashedCredentialsMatcher;
    }


}