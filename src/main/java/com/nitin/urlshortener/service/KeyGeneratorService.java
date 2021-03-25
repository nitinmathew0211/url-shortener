package com.nitin.urlshortener.service;

/**
 * @author nitinmathew - created on 25/03/2021
 **/
public interface KeyGeneratorService {

  boolean validateCustomKey(String customKey);

  String getUnusedKey();
}
