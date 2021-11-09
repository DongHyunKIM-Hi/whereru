package com.wau.wau

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class WauApplication

fun main(args: Array<String>) {
    runApplication<WauApplication>(*args)
}
