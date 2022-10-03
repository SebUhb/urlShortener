package com.urlshortener

import com.urlshortener.entity.ShortUrl
import com.urlshortener.repository.ShortUrlRepository
import com.urlshortener.service.ShortUrlService
import io.mockk.every
import io.mockk.mockk
import io.mockk.verify
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test
import java.util.*

class ShortUrlServiceTest {
    private val shortUrlRepository: ShortUrlRepository  = mockk()
    private val shortUrlService = ShortUrlService(shortUrlRepository)

    @Test
    fun `when get shortUrl with ID then return Short Url`() {
        val shortUrl = ShortUrl("1", "www.test.de")
        every { shortUrlRepository.findById("1") } returns Optional.of(shortUrl)

        val result = shortUrlService.getUrlWithShort("1")

        verify(exactly = 1) { shortUrlRepository.findById("1") }
        assertEquals(shortUrl, result)
    }
}