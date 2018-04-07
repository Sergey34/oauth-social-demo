package com.seko0716.oauth2.social.demo.repositores

import com.seko0716.oauth2.social.demo.domains.DemoUser
import com.seko0716.springboot.starter.oauth2.social.domains.User
import com.seko0716.springboot.starter.oauth2.social.repository.IUserStorage

class DemoUserStorage(private var userRepository: AuthUserMongoRepository, private var demoUserRepository: DemoUserRepository) : IUserStorage {

    override fun save(entity: User): User {
        val authUser = userRepository.save(entity)
        demoUserRepository.save(DemoUser(authUser = authUser))
        return authUser
    }

    override fun findOneBySocialAccountId(socialAccountId: String): User? {
        return userRepository.findOneBySocialAccountId(socialAccountId)
    }
}

