package com.seko0716.oauth2.social.demo.services

import com.seko0716.oauth2.social.demo.domains.DemoUser
import com.seko0716.oauth2.social.demo.repositores.DemoUserRepository
import com.seko0716.springboot.starter.oauth2.social.domains.User
import org.springframework.security.oauth2.provider.OAuth2Authentication
import org.springframework.stereotype.Service
import java.security.Principal

@Service
class DemoUserService(var demoUserRepository: DemoUserRepository) {
    fun getCurrentUserByPrincipal(principal: Principal?): DemoUser? {
        return if (principal != null) {
            demoUserRepository.findOneByAuthUser(getAuthUserByPrincipal(principal))
        } else null
    }

    private fun getAuthUserByPrincipal(principal: Principal) =
            (principal as OAuth2Authentication).userAuthentication.principal as User

}