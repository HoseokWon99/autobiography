package com.autobiography.users.utils.cache;

import lombok.AllArgsConstructor;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Repository;

import java.util.concurrent.TimeUnit;

@AllArgsConstructor
@Repository
public class RedisRepository implements KeyValueRepository<String, Object> {
    private final RedisTemplate<String, Object> redisTemplate;

    @Override
    public void setData(String key, Object value, long timeout) {
        redisTemplate.opsForValue()
                .set(key, value, timeout, TimeUnit.MILLISECONDS);
    }

    @Override
    public Object getData(String key) {
        return redisTemplate.opsForValue().get(key);
    }

    @Override
    public void deleteData(String key){
        redisTemplate.delete(key);
    }

    @Override
    public boolean hasKey(String key) {
        return Boolean.TRUE.equals(redisTemplate.hasKey(key));
    }

}
