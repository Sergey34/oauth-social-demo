package com.seko0716.oauth2.social.demo

import com.seko0716.oauth2.social.demo.repositores.AuthUserMongoRepository
import com.seko0716.oauth2.social.demo.repositores.DemoUserRepository
import com.seko0716.oauth2.social.demo.repositores.DemoUserStorage
import com.seko0716.springboot.starter.oauth2.social.SpringSecurityOauth2VkGoogleAutoConfiguration
import com.seko0716.springboot.starter.oauth2.social.repository.IUserStorage
import org.springframework.boot.SpringApplication
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.ComponentScan
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories

@ComponentScan(basePackages = ["com.seko0716.oauth2.social.demo"])
@EnableMongoRepositories(basePackages = [
    "com.seko0716.oauth2.social.demo.repositores",
    "com.seko0716.springboot.starter.oauth2.social.repository"//fixed bug in my starter
])
@SpringBootApplication
class Application : SpringSecurityOauth2VkGoogleAutoConfiguration() {
    @Bean
    fun userStorage(userMongoRepository: AuthUserMongoRepository, demoUserRepository: DemoUserRepository): IUserStorage {
        return DemoUserStorage(userRepository = userMongoRepository, demoUserRepository = demoUserRepository)
    }

}

fun main(args: Array<String>) {
    SpringApplication.run(Application::class.java, *args)
}