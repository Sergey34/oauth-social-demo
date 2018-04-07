package com.seko0716.oauth2.social.demo.domains

import com.seko0716.springboot.starter.oauth2.social.domains.User
import org.bson.types.ObjectId

/**
 * otherEmail needed for reestablish account if social account bern deleted
 */

data class DemoUser(var id: ObjectId=ObjectId(), var authUser: User, var otherEmail: String? = null)