package com.manchesterseals.baseball;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;

@SpringBootTest
@TestPropertySource(properties = {
    "spring.data.mongodb.uri=mongodb://localhost:27017/test_manchester_seals"
})
class BaseballStatsApplicationTests {

    @Test
    void contextLoads() {
        // This test will pass if the application context loads successfully
    }
}
