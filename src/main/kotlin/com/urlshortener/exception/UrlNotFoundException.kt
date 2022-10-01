package com.urlshortener.exception

class UrlNotFoundException (override val message:String) : Exception(message)