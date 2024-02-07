package org.example.sample;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;

@Slf4j
public class LoggerTest {
    @Test
    public void test() {
        log.debug("asd");
    }
}
