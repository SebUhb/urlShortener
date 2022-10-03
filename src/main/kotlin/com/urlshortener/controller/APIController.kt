package com.urlshortener.controller

import com.urlshortener.entity.ShortUrl
import com.urlshortener.service.ShortUrlService
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/api/shorturl")
class ApiController (val shortUrlService: ShortUrlService) {

    @GetMapping("/")
    fun findAll(): List<ShortUrl> = shortUrlService.getAllUrls()

    @GetMapping("/{short}")
    fun findOne(@PathVariable short: String): ShortUrl = shortUrlService.getUrlWithShort(short)

    @PostMapping("/{longUrl}")
    fun storeUrl(@PathVariable longUrl: String): ShortUrl = shortUrlService.createShortUrl(longUrl)

    @PostMapping("/")
    fun storeUrl(@RequestBody shortUrl: ShortUrl): ShortUrl = shortUrlService.createShortUrl(shortUrl)

}