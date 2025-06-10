package com.example.mongoreplicas

import com.mongodb.ConnectionString
import com.mongodb.MongoClientSettings
import com.mongodb.client.MongoClient
import com.mongodb.client.MongoClients
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.data.mongodb.core.MongoTemplate

@Configuration
class MongoConfig {

    @Bean
    fun mongoClient(): MongoClient {
        val connectionString = ConnectionString("mongodb://mongo1:27017/mydatabase?replicaSet=rs0")
//        val connectionString = ConnectionString("mongodb://mongo1:27017,mongo2:27017,mongo3:27017/mydatabase?replicaSet=rs0")
        val settings = MongoClientSettings.builder()
            .applyConnectionString(connectionString)
            .build()
        return MongoClients.create(settings)
    }

    @Bean(value = ["mongoTemplate"])
    fun mongoTemplate(mongoClient: MongoClient) = MongoTemplate(mongoClient, "mydatabase")
}