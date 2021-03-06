package com.seko0716.oauth2.social.demo.repositores

import com.seko0716.springboot.starter.oauth2.social.domains.User
import org.bson.types.ObjectId
import org.springframework.data.mongodb.repository.MongoRepository
import org.springframework.stereotype.Repository

@Repository
interface AuthUserMongoRepository : MongoRepository<User, ObjectId> {
    fun findOneBySocialAccountId(socialAccountId: String): User?

}