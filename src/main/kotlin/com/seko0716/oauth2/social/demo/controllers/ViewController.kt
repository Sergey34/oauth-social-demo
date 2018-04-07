package com.seko0716.oauth2.social.demo.controllers

import com.seko0716.oauth2.social.demo.services.DemoUserService
import com.seko0716.springboot.starter.oauth2.social.domains.User
import org.springframework.security.access.prepost.PreAuthorize
import org.springframework.security.oauth2.provider.OAuth2Authentication
import org.springframework.security.oauth2.provider.authentication.OAuth2AuthenticationDetails
import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.GetMapping
import java.security.Principal
import javax.servlet.http.HttpServletResponse
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.ResponseBody
import javax.servlet.http.HttpServletRequest
import com.sun.corba.se.spi.presentation.rmi.StubAdapter.request
import org.springframework.security.core.Authentication
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler
import org.springframework.security.core.context.SecurityContextHolder




@Controller
class ViewController(var demoUserService: DemoUserService) {

    @GetMapping(path = ["/"])
    fun root(principal: Principal?, map: MutableMap<String, Any?>): String {
        map["user"] = demoUserService.getCurrentUserByPrincipal(principal)
        return "root"
    }

    @GetMapping(path = ["/account"])
    @PreAuthorize("hasRole('ROLE_USER')")
    fun account(principal: Principal?, map: MutableMap<String, Any?>): String {
        showPrincipal(principal)
        map["user"] = demoUserService.getCurrentUserByPrincipal(principal)
        return "account"
    }

    @GetMapping("/logout")
    fun logout(auth: Authentication?, request: HttpServletRequest, response: HttpServletResponse): String {
        if (auth != null) {
            SecurityContextLogoutHandler().logout(request, response, auth)
        }
        return "redirect:/"
    }


    private fun showPrincipal(principal: Principal?) {
        if (principal is OAuth2Authentication) {
            if (principal.details is OAuth2AuthenticationDetails) {
                println("details.sessionId: ${(principal.details as OAuth2AuthenticationDetails).sessionId}")
                println("details.token: ${(principal.details as OAuth2AuthenticationDetails).tokenValue}")
            }
            if (principal.userAuthentication.principal is User) {
                println("userAuthentication.principal: ${principal.userAuthentication.principal}")
            }
            println("principal.authorities: ${principal.authorities}")
        }
    }
}