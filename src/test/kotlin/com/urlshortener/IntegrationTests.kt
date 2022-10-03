package com.urlshortener

import com.urlshortener.entity.ShortUrl
import org.assertj.core.api.Assertions.*
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.boot.test.web.client.TestRestTemplate
import org.springframework.boot.test.web.client.getForEntity
import org.springframework.boot.test.web.client.postForEntity
import org.springframework.http.HttpStatus

@SpringBootTest(
    classes = [UrlShortenerApplication::class],
    webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class IntegrationTests {

    @Autowired
    lateinit var restTemplate: TestRestTemplate

    @Test
    fun `When all entries are queried, status is OK`() {
        val entity = restTemplate.getForEntity<String>("/api/shorturl/")
        assertThat(entity.statusCode).isEqualTo(HttpStatus.OK)
    }

    @Test
    fun `When entry is posted, status should be OK and returned shortUrl`() {
        val entity = restTemplate.postForEntity<String>("/api/shorturl/", ShortUrl("1234", "www.test.de"))

        assertThat(entity).isNotNull
        assertThat(entity.statusCode).isEqualTo(HttpStatus.OK)
        assertThat(entity.body).contains("\"shortUrl\":\"1234\"")
        assertThat(entity.body).contains("\"longUrl\":\"www.test.de\"")
    }
}