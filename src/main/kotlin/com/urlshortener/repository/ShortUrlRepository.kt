package com.urlshortener.repository

import com.urlshortener.entity.ShortUrl
import org.springframework.data.repository.CrudRepository
import org.springframework.stereotype.Repository
import java.util.*

@Repository
interface ShortUrlRepository : CrudRepository<ShortUrl, String> {
    override fun findAll(): List<ShortUrl>

    fun findByShortUrl(shortUrl: String): Optional<ShortUrl>

    override fun <S : ShortUrl> save(entity: S): S

    fun findFirstByLongUrl(longUrl: String): Optional<ShortUrl>
}