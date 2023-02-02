package com.example.controller

import com.example.constants.allUsers
import com.example.model.User
import io.micronaut.http.HttpRequest
import io.micronaut.http.client.HttpClient
import io.micronaut.http.client.annotation.Client
import io.micronaut.test.extensions.junit5.annotation.MicronautTest
import jakarta.inject.Inject
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import com.fasterxml.jackson.databind.ObjectMapper as ObjectMapper

@MicronautTest
class UserControllerTest {

    @Inject
    @field:Client("/")
    lateinit var client: HttpClient

    @BeforeEach
    fun clearUserData() {
        allUsers.clear()
    }

    @Test
    fun `should register valid user`(objectMapper: ObjectMapper) {
        val user = User("Satyam", "Bal", "9876543210", "amaans@a1234567890a1123456789090a1.ai", "sat_yam")
        val request = HttpRequest.POST(
            "/user/register", objectMapper.writeValueAsString(user)
        )

        val response = client.toBlocking().retrieve(request)

        assertEquals(user, allUsers["sat_yam"])
        assertEquals(1, allUsers.size)
        assertEquals("""["User added successfully"]""", response)
    }
}
