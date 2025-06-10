package com.example.mongoreplicas

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class MongoReplicasApplication

fun main(args: Array<String>) {
    runApplication<MongoReplicasApplication>(*args)
}
