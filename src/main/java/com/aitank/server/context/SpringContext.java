package com.aitank.server.context;

import com.aitank.server.component.ObjectMultiAction;
import com.aitank.server.config.RedisManager;
import com.aitank.server.config.ServerConfig;
import com.aitank.server.service.UserService;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;

import javax.annotation.Resource;
import java.util.Collection;

public class SpringContext implements ApplicationContextAware {

    private static ApplicationContext applicationContext = null;
    private static UserService userService;
    private static ServerConfig serverConfig;
    private static RedisManager redisManager;
    private static ObjectMultiAction objectMultiAction;


    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        SpringContext.applicationContext = applicationContext ;
    }
    public final static <T> T getBean(Class<T> clazz){
        return applicationContext.getBean(clazz);
    }

    public final static <T> Collection<T> getBeansOfType(Class<T> clazz) {
        return applicationContext.getBeansOfType(clazz).values();
    }

    public final static <T> T getBean(String name, Class<T> requiredType) {
        return applicationContext.getBean(name, requiredType);
    }
    public final static Object getBean(String name){
        return applicationContext.getBean(name);
    }

    @Resource
    public void setServerConfigs(ServerConfig serverConfigs) {
        SpringContext.serverConfig = serverConfigs;
    }

    public final static ServerConfig getServerConfigs() {
        return SpringContext.serverConfig;
    }

    @Resource
    public void setRedisManager(RedisManager redisManager){
        SpringContext.redisManager = redisManager;
    }
    public final static RedisManager getRedisManager(){
        return SpringContext.redisManager;
    }

    @Resource
    public void setObjectMultiAction(ObjectMultiAction objectMultiAction){
        SpringContext.objectMultiAction = objectMultiAction;
    }
    public final static ObjectMultiAction getObjectMultiAction(){
        return SpringContext.objectMultiAction;
    }

    @Resource
    public void setUserService(UserService userService){
        SpringContext.userService = userService;
    }
    public final static UserService getUserService(){
        return SpringContext.userService;
    }

}
