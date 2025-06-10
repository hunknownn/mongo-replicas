package com.example.mongoreplicas

import com.mongodb.client.MongoClient
import com.mongodb.connection.ServerConnectionState
import org.slf4j.LoggerFactory
import org.springframework.beans.factory.annotation.Qualifier
import org.springframework.data.mongodb.core.MongoTemplate
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import java.util.UUID

@RestController
@RequestMapping("/api/mongo")
class TestController(
    @Qualifier(value = "mongoTemplate")
    private val mt: MongoTemplate,

    private val mc: MongoClient
) {
    private val logger = LoggerFactory.getLogger(this::class.java)

    @GetMapping("")
    fun write() {
        println("===========================")
        val serverDescriptions = mc.clusterDescription.serverDescriptions
        val primaryMongoNode = serverDescriptions.find { it.isPrimary }

        if (primaryMongoNode !== null) {
            logger.info("MongoDB operation executing on PRIMARY node: {}", primaryMongoNode.address)
        } else {
            val connectedServers = serverDescriptions
                .filter { it.state == ServerConnectionState.CONNECTED }
                .map { it.address }
                .joinToString(", ")

            logger.info("MongoDB connected servers: {}", connectedServers)
        }

        val user = User().apply {
            name = "${UUID.randomUUID()}"
        }
        mt.save(user)

        println("===========================")
    }
}