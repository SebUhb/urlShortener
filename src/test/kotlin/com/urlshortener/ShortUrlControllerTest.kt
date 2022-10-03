package com.urlshortener

import com.ninjasquad.springmockk.MockkBean
import com.urlshortener.entity.ShortUrl
import com.urlshortener.repository.ShortUrlRepository
import io.mockk.every
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.http.MediaType
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get
import org.springframework.test.web.servlet.result.MockMvcResultMatchers.*

@AutoConfigureMockMvc
@SpringBootTest
class ShortUrlControllerTest(@Autowired val mockMvc: MockMvc) {

    @MockkBean
    private lateinit var shortUrlRepository: ShortUrlRepository

    @Test
    fun `List all ShortUrl`() {
        val firstShortUrl = ShortUrl("1234567890", "ww.thisisatest.com")
        val secondShortUrl = ShortUrl("1123456789", "www.anothertest.com")

        every { shortUrlRepository.findAll() } returns listOf(firstShortUrl, secondShortUrl)
        mockMvc.perform(get("/api/shorturl/").accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isOk)
            .andExpect(content().contentType(MediaType.APPLICATION_JSON))
            .andExpect(jsonPath("\$.[0].shortUrl").value(firstShortUrl.shortUrl))
            .andExpect(jsonPath("\$.[0].longUrl").value(firstShortUrl.longUrl))
            .andExpect(jsonPath("\$.[1].shortUrl").value(secondShortUrl.shortUrl))
            .andExpect(jsonPath("\$.[1].longUrl").value(secondShortUrl.longUrl))
    }
}