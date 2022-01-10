package com.vincent.swagger2.demo.config;


import com.github.benmanes.caffeine.cache.Caffeine;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;
import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cache.caffeine.CaffeineCache;
import org.springframework.cache.support.SimpleCacheManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

@Configuration
@EnableCaching
public class CacheConfig {

    public static final int DEFAULT_MAXSIZE = 100;
    public static final int DEFAULT_TTL = 5;

    /**
     * 定義 cache 名稱、超時時常(秒)，最大容量
     * 每個 cache 5 秒超時，最多緩存 100 條數據，需要修改可以在建構方法的參數中指定
     * 可用 properties 方式設定，或是在此設定
     */
    public enum Caches {
        // 取得今天日期
        getTime,
        // 取得當前 millis
        currentTimeMillis;

        // 最大數量
        private int maxSize = DEFAULT_MAXSIZE;
        // 超時時間(秒)
        private int ttl = DEFAULT_TTL;

        Caches() {

        }

        Caches(int ttl) {
            this.ttl = ttl;
            this.maxSize = maxSize;
        }

        public int getMaxSize() {
            return maxSize;
        }

        public int getTtl() {
            return ttl;
        }
    }

    @Bean
    @Primary
    public CacheManager caffeineCacheManager() {
        SimpleCacheManager cacheManager = new SimpleCacheManager();

        List<CaffeineCache> caches = new ArrayList<>();
        for (Caches c : Caches.values()) {
            caches.add(new CaffeineCache(c.name(),
                    Caffeine.newBuilder().recordStats()
                            .expireAfterWrite(c.getTtl(), TimeUnit.SECONDS)
                            .maximumSize(c.getMaxSize())
                            .build())
            );
        }

        cacheManager.setCaches(caches);
        return cacheManager;
    }
}
