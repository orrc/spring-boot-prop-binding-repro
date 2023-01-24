package com.example.demo

import org.junit.jupiter.api.Test
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest
import org.springframework.boot.test.context.TestConfiguration
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Import

@TestConfiguration
class SomeTestConfig {
    @Bean
    fun someConfig() = SomeConfig(
        first = SomeConfig.ChildConfig(
            someProperty = 2,
        ),
    )
}

@WebMvcTest(Controller::class)
@Import(SomeTestConfig::class)
class ControllerTest {
    @Test
    fun doNothing() {
        // Fails to start on Spring Boot 3.0.2
    }
}
