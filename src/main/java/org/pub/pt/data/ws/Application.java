package org.pub.pt.data.ws;

import com.google.common.cache.CacheBuilder;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.CacheManager;
import org.springframework.cache.guava.GuavaCacheManager;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cache.interceptor.KeyGenerator;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

import java.lang.reflect.Method;
import java.util.concurrent.TimeUnit;

@EnableScheduling
@SpringBootApplication
@EnableCaching
public class Application {

    public static final int GUAVA_CACHE_SIZE = 10000;
    public static final int GUAVA_EXPIRATION_HOURS = 6;

    public static void main(String[] args) throws Exception{
        SpringApplication.run(Application.class, args);
    	//ServiceDescription sd=new ServiceDescription();
        //sd.getControllers();
    }

    @Bean
    public CacheManager cacheManager() {
        CacheBuilder cacheBuilder = CacheBuilder.newBuilder()
                .maximumSize(Application.GUAVA_CACHE_SIZE)
                .expireAfterAccess(Application.GUAVA_EXPIRATION_HOURS, TimeUnit.HOURS);
        GuavaCacheManager cacheManager = new GuavaCacheManager("cache");
        cacheManager.setCacheBuilder(cacheBuilder);
        return cacheManager;
    }

    @Bean
    public KeyGenerator keyGenerator(){
        return new KeyGenerator() {
            @Override
            public Object generate(Object o, Method method, Object... objects) {
                StringBuilder sb=new StringBuilder();
                sb.append(o.getClass().getName());
                sb.append(method.getName());
                for(Object obj : objects){
                    sb.append(obj.toString());
                }
                return sb.toString();
            }
        };
    }

}
