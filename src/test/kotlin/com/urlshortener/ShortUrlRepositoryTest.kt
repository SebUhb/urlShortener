package com.urlshortener

import com.urlshortener.entity.ShortUrl
import com.urlshortener.repository.ShortUrlRepository
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager

@DataJpaTest
class ShortUrlRepositoryTest {
    @Autowired
    lateinit var entityManager: TestEntityManager

    @Autowired
    lateinit var shortUrlRepository: ShortUrlRepository

    @Test
    fun `When findAll then return all ShortUrl`() {
        val firstShortUrl = ShortUrl("1234567890", "ww.thisisatest.com")
        val secondShortUrl = ShortUrl("1123456789", "www.anothertest.com")
        entityManager.persist(firstShortUrl)
        entityManager.persist(secondShortUrl)
        entityManager.flush()

        val found = shortUrlRepository.findAll()
        assertThat(found[0]).isEqualTo(firstShortUrl)
        assertThat(found[1]).isEqualTo(secondShortUrl)
    }

    @Test
    fun `When findById then return a ShortUrl`() {
        val shortUrl = ShortUrl("1234567890", "www.thisisatest.com")
        entityManager.persist(shortUrl)
        entityManager.flush()

        val found = shortUrlRepository.findById(shortUrl.shortUrl).get()
        assertThat(found).isEqualTo(shortUrl)
    }

    @Test
    fun `When findByLongUrl then return a ShortUrl`() {
        val shortUrl = ShortUrl("1234567890", "www.thisisatest.com")
        entityManager.persist(shortUrl)
        entityManager.flush()

        val found = shortUrlRepository.findByLongUrl(shortUrl.longUrl).get()
        assertThat(found).isEqualTo(shortUrl)
    }
}