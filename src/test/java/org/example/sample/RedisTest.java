package org.example.sample;


import java.util.Random;
import org.assertj.core.api.Assertions;
import org.example.global.config.AppConfig;
import org.example.global.config.db.RedisDbConfig;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = {AppConfig.class, RedisDbConfig.class})
public class RedisTest {
    @Autowired
    RedisTemplate<String, Object> redisTemplate;

    @Test
    public void test() {
        ValueOperations<String, Object> ops = redisTemplate.opsForValue();
        String key = "aaa";
        String value = "aaa%d".formatted(new Random().nextInt(1000));
        ops.set(key, value);

        String valueFromDb = (String) ops.get(key);

        Assertions.assertThat(valueFromDb).isEqualTo(value);
    }
}
