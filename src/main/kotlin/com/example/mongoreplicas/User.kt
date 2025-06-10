package com.example.mongoreplicas

import org.bson.types.ObjectId
import org.springframework.data.annotation.Id
import org.springframework.data.mongodb.core.mapping.Document

@Document(value = "testCollections")
open class User {
    @Id
    var id: String? = null
        private set

    open var name: String = ""
}