package org.pt.pub.data.ws;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cache.concurrent.ConcurrentMapCacheManager;
import org.springframework.context.annotation.Bean;
import org.springframework.scheduling.annotation.EnableScheduling;
import twitter4j.Status;
import twitter4j.StatusUpdate;
import twitter4j.Twitter;
import twitter4j.TwitterFactory;
import twitter4j.auth.AccessToken;

@EnableScheduling
@SpringBootApplication
@EnableCaching
public class Application {

    public static void main(String[] args) throws Exception{
        SpringApplication.run(Application.class, args);
    	//ServiceDescription sd=new ServiceDescription();
    	//sd.getControllers();
    }

    @Bean
    public CacheManager cacheManager() {
        return new ConcurrentMapCacheManager("cache");
    }

}
