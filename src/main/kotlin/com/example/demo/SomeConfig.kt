package com.example.demo

import org.springframework.boot.context.properties.ConfigurationProperties

@ConfigurationProperties("app.my-config")
data class SomeConfig(
    val first: ChildConfig,
) {
    data class ChildConfig(
        val someProperty: Int,
    )
}
