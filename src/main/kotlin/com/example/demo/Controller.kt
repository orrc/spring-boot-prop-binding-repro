package com.example.demo

import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RestController

@RestController
class Controller(
    private val config: SomeConfig,
) {
    @GetMapping("/")
    fun getConfig(): ResponseEntity<SomeConfig> = ResponseEntity.ok(config)
}
