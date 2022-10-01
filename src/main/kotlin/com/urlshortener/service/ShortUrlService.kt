package com.urlshortener.service

import com.urlshortener.entity.ShortUrl
import com.urlshortener.exception.ShortUrlAlreadyExistException
import com.urlshortener.exception.UrlNotFoundException
import com.urlshortener.repository.ShortUrlRepository
import org.springframework.stereotype.Service
import java.security.MessageDigest
import javax.xml.bind.DatatypeConverter
import kotlin.random.Random

@Service
class ShortUrlService (val shortUrlRepository: ShortUrlRepository) {

    fun getAllUrls(): List<ShortUrl> = shortUrlRepository.findAll().toList()

    fun getUrlWithShort(short: String): ShortUrl = shortUrlRepository.findById(short).orElseThrow {
        UrlNotFoundException("Unable to find a stored url with the given short url: $short")
    }

    fun createShortUrl(longUrl: String): ShortUrl {
        val optExistingUrl = shortUrlRepository.findByLongUrl(longUrl)

        return if (optExistingUrl.isPresent) {
            optExistingUrl.get()
        } else {
            var short: String
            do {
                // avoid creating of the same hash again
                short = createHash(longUrl + Random.nextInt(0, 10))
                val optNewShort = shortUrlRepository.findById(short)
            } while(optNewShort.isPresent)
            shortUrlRepository.save(ShortUrl(short, longUrl))
        }
    }

    fun createShortUrl(shortUrl: ShortUrl): ShortUrl {
        return if (shortUrl.shortUrl.isNullOrEmpty()) {
            createShortUrl(shortUrl.longUrl)
        } else {
            try {
                shortUrlRepository.save(shortUrl)
            } catch(e: Exception) {
                throw (ShortUrlAlreadyExistException("ShortUrl/identifier $"))
            }
        }
    }

    fun createHash(plainText: String): String {
        val bytes = MessageDigest
            .getInstance("MD5")
            .digest(plainText.toByteArray())

        return DatatypeConverter.printHexBinary(bytes)
    }
}