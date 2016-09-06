package org.pub.pt.data.ws;

import com.google.common.cache.CacheBuilder;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.CacheManager;
import org.springframework.cache.guava.GuavaCacheManager;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cache.interceptor.KeyGenerator;
import org.springframework.context.annotation.Bean;
import org.springframework.scheduling.annotation.EnableScheduling;

import java.lang.reflect.Method;
import java.util.concurrent.TimeUnit;

@EnableScheduling
@SpringBootApplication
@EnableCaching
public class Application {

    private static final int cacheSize = 10000;
    private static final int expirationInHours = 6;

    public static void main(String[] args) throws Exception{
        SpringApplication.run(Application.class, args);
    	//ServiceDescription sd=new ServiceDescription();
        //sd.getControllers();
    }

    @Bean
    public CacheManager cacheManager() {
        CacheBuilder cacheBuilder = CacheBuilder.newBuilder()
                .maximumSize(cacheSize)
                .expireAfterAccess(expirationInHours, TimeUnit.HOURS);
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
                sb.append(method.getClass().getName());
                for(Object obj : objects){
                    sb.append(obj.toString());
                }
                return sb.toString();
            }
        };
    }

}
