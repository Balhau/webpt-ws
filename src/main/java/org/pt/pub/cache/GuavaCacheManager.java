package org.pt.pub.cache;

import org.springframework.cache.Cache;
import org.springframework.cache.CacheManager;

import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

/**
 * This will implement a cachemanager for GuavaCaches. The advantage of a guava cache is that it enable us to setup eviction policies
 * Created by vitorfernandes on 9/6/16.
 */
public class GuavaCacheManager implements CacheManager{

    ConcurrentMap<String,Cache> cacheMap = new ConcurrentHashMap<>(16);

    public GuavaCacheManager(String... cacheNames){
        setCaches(Arrays.asList(cacheNames));
    }

    private void setCaches(List<String> cacheNames){
        for(String cacheName : cacheNames){
            this.cacheMap.put(cacheName,createGuavaCache());
        }
    }

    @Override
    public Cache getCache(String name) {
        return cacheMap.get(name);
    }

    @Override
    public Collection<String> getCacheNames() {
        return this.cacheMap.keySet();
    }

    private Cache createGuavaCache(){
        return null;
    }
}
