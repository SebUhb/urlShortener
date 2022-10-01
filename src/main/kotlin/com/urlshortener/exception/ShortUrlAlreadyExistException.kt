package com.urlshortener.exception

class ShortUrlAlreadyExistException (override val message:String) : Exception(message)