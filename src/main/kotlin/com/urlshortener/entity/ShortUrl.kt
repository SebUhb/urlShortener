package com.urlshortener.entity

import javax.persistence.Entity
import javax.persistence.Id

@Entity
class ShortUrl (@Id val shortUrl: String, val longUrl: String)