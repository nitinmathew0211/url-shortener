package com.nitin.urlshortener.service;

/**
 * @author nitinmathew - created on 25/03/2021
 **/
public interface UrlShortenerService {

  /**
   * Get original url from key
   *
   * @param key
   * @return
   * @throws Exception
   */
  String getOriginalUrl(String key) throws Exception;

  String createShortUrl(String customKey, String originalUrl) throws Exception;
}
