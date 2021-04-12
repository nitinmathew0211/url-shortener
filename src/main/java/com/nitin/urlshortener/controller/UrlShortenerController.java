package com.nitin.urlshortener.controller;

import static com.nitin.urlshortener.model.constant.Constants.SHORT_URL_PREFIX;

import java.net.URI;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.nitin.urlshortener.model.apipath.UrlApiPath;
import com.nitin.urlshortener.model.request.UrlShortenerRequest;
import com.nitin.urlshortener.service.UrlShortenerService;
import lombok.extern.slf4j.Slf4j;

/**
 * @author nitinmathew - created on 25/03/2021
 **/
@Slf4j
@RestController
@RequestMapping(UrlApiPath.BASE_PATH)
public class UrlShortenerController {

  @Autowired
  private UrlShortenerService urlShortenerService;

  @Autowired
  private ObjectMapper objectMapper;

  @GetMapping(value = UrlApiPath.REDIRECT)
  public ResponseEntity<String> redirect(@PathVariable("key") String key) throws JsonProcessingException {
    log.info("Invoking API for redirection with key {}", key);
    try {
      String originalUrl = urlShortenerService.getOriginalUrl(key);
      URI uri = new URI(originalUrl);
      return ResponseEntity.status(302).location(uri).body(objectMapper.writeValueAsString(Boolean.TRUE));
    } catch (Exception e) {
      //Read about ResponseEntityExceptionHandler for handling exceptions. Using these you wouldn't have to handle
      //them on the controller level and would make the code cleaner.
      log.error("Error while invoking API for redirection with key {} {}", key, e.getMessage(), e);
      return ResponseEntity.status(404).body(objectMapper.writeValueAsString(e.getMessage()));
    }
  }

  @PostMapping(value = UrlApiPath.CREATE_SHORT_URL, produces = MediaType.APPLICATION_JSON_VALUE, consumes =
      MediaType.APPLICATION_JSON_VALUE)
  public ResponseEntity<String> createShortUrl(@RequestBody UrlShortenerRequest urlShortenerRequest)
      throws JsonProcessingException {
    log.info("Invoking API to create short url with request {}", urlShortenerRequest);
    try {
      String key = urlShortenerService
          .createShortUrl(urlShortenerRequest.getCustomKey(), urlShortenerRequest.getOriginalUrl());
      return ResponseEntity.ok(objectMapper.writeValueAsString(SHORT_URL_PREFIX + key));
    } catch (Exception e) {
      log.error("Error while invoking API to create short url {}", e.getMessage(), e);
      return ResponseEntity.status(500).body(objectMapper.writeValueAsString(e.getMessage()));
    }
  }
}
