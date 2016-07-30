# Pokemon Go REST API

[![Build Status](https://travis-ci.org/cristianrgreco/pokemon-go-rest-api.svg?branch=master)](https://travis-ci.org/cristianrgreco/pokemon-go-rest-api)

*CORS-enabled REST API for querying Pokemon GO.*

## Example

https://pokemon-go-rest-api.herokuapp.com/?latitude=51.497121994573&longitude=-0.12494802474976

```json
[
  {
    "id": 24761706,
    "uuid": "139:51.496090:-0.129621",
    "pokemonId": "139",
    "name": "Omastar",
    "latitude": 51.496089681245,
    "longitude": -0.12962138188485,
    "expirationTime": 1469903111
  },
  {
    "id": 24876424,
    "uuid": "096:51.498388:-0.130734",
    "pokemonId": "096",
    "name": "Drowzee",
    "latitude": 51.498387529159,
    "longitude": -0.13073406825121,
    "expirationTime": 1469903192
  },
  {
    "id": 26194007,
    "uuid": "041:51.496255:-0.128428",
    "pokemonId": "041",
    "name": "Zubat",
    "latitude": 51.496255207395,
    "longitude": -0.1284284197688,
    "expirationTime": 1469903628
  }
]
```

## Pokevision Maintenance

This API includes a healthcheck to determine whether Pokevision is currently down or in maintenance: https://pokemon-go-rest-api.herokuapp.com/admin/healthcheck

```json
{
  "deadlocks": {
    "healthy": true
  },
  "pokevision": {
    "healthy": true
  }
}
```
