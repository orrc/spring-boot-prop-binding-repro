package com.example.demo

import org.springframework.boot.context.properties.ConfigurationProperties

@ConfigurationProperties("app.my-config")
data class SomeConfig(
    val first: ChildConfig,
    val second: ChildConfig,
) {
    data class ChildConfig(
        val someProperty: Int,
        val anotherProperty: Int,
    )
}
