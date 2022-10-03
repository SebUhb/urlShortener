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

    fun getUrlWithShort(short: String): ShortUrl = shortUrlRepository.findByShortUrl(short).orElseThrow {
        UrlNotFoundException("Unable to find a stored url with the given short url: $short")
    }

    fun createShortUrl(longUrl: String): ShortUrl {
        val optExistingUrl = shortUrlRepository.findFirstByLongUrl(longUrl)

        // use existing entry or create a new one
        return if (optExistingUrl.isPresent) {
            optExistingUrl.get()
        } else {
            var short: String
            do {
                // use random number to avoid accidental recreation of a key and shorten the key
                short = createHash(longUrl + Random.nextInt(0, 10)).take(10)
                val optNewShort = shortUrlRepository.findByShortUrl(short)
            } while(optNewShort.isPresent)
            shortUrlRepository.save(ShortUrl(short, longUrl))
        }
    }

    fun createShortUrl(shortUrl: ShortUrl): ShortUrl {
        return if (shortUrl.shortUrl.isEmpty()) {
            createShortUrl(shortUrl.longUrl)
        } else {
            if (shortUrlRepository.findByShortUrl(shortUrl.shortUrl).isEmpty) {
                shortUrlRepository.save(shortUrl)
            } else {
                throw (ShortUrlAlreadyExistException("ShortUrl/identifier ${shortUrl.shortUrl} already exists"))
            }
        }
    }

    fun createHash(plainText: String): String {
        val bytes = MessageDigest
            .getInstance("MD5")
            .digest(plainText.toByteArray(Charsets.UTF_8))

        return DatatypeConverter.printHexBinary(bytes)
    }
}