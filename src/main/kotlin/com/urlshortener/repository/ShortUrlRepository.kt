package com.urlshortener.repository

import com.urlshortener.entity.ShortUrl
import org.springframework.data.repository.CrudRepository
import org.springframework.stereotype.Repository
import java.util.*

@Repository
interface ShortUrlRepository : CrudRepository<ShortUrl, String> {
    override fun findAll(): List<ShortUrl>

    override fun findById(id: String): Optional<ShortUrl>

    override fun <S : ShortUrl?> save(entity: S): S

    override fun deleteById(id: String)

    fun findByLongUrl(longUrl: String): Optional<ShortUrl>
}