# UrlShortener

Urlshortener is a kotlin REST API which takes long urls and converts it into a short form and vice versa.

## Introduction

This Url-shortener offers the opportunity to create hashed keys of an URL.

The key and the belonging URL is stored in an internal h2 database.

To query the full URL send the short key to the REST interface.

To support applications which wants to create or handle their own keys, this application offers the opportunity to send your own key.

## Endpoints
To enable this functionality, the user of the api has following operation:
- List all shortUrl URL mappings
- Get a full URL with key
- Create mapping with full URL in path variable
- Create mapping with short URL (optional) and full URL in body
- View documentation with /swagger-ui.html

## Contributing
Pull requests are welcome. For major changes, please open an issue first to discuss what you would like to change.

Please make sure to update tests as appropriate.

## License
[MIT](https://choosealicense.com/licenses/mit/)