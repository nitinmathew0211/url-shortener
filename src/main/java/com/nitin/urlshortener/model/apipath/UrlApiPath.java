package com.nitin.urlshortener.model.apipath;

/**
 * @author nitinmathew - created on 26/03/2021
 **/
public interface UrlApiPath {

  String BASE_PATH = "/api/url";
  String REDIRECT = "/{key}";
  String CREATE_SHORT_URL = "/create-short-url";
}
