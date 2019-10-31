package org.pub.pt.data.ws;

import com.github.benmanes.caffeine.cache.Caffeine;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cache.caffeine.CaffeineCacheManager;
import org.springframework.cache.interceptor.KeyGenerator;
import org.springframework.context.annotation.Bean;
import org.springframework.scheduling.annotation.EnableScheduling;

import java.lang.reflect.Method;
import java.util.concurrent.TimeUnit;

@EnableScheduling
@SpringBootApplication
@EnableCaching
public class Application {

    public static final int CAFFEINE_CACHE_SIZE = 10000;
    public static final int CAFFEINE_EXPIRATION_MINUTES = 6;
    public static final int CAFFEINE_INITIAL_CAPACITY=100;

    public static void main(String[] args) throws Exception{
        SpringApplication.run(Application.class, args);
    	//ServiceDescription sd=new ServiceDescription();
        //sd.getControllers();
    }

    @Bean
    public CacheManager cacheManager() {
        Caffeine caffeine = Caffeine.newBuilder()
                .initialCapacity(CAFFEINE_INITIAL_CAPACITY)
                .maximumSize(CAFFEINE_CACHE_SIZE)
                .expireAfterAccess(CAFFEINE_EXPIRATION_MINUTES, TimeUnit.MINUTES)
                .weakKeys()
                .recordStats();

        CaffeineCacheManager caffeineCacheManager= new CaffeineCacheManager("cache");
        caffeineCacheManager.setCaffeine(caffeine);
        return caffeineCacheManager;
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
