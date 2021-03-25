package com.nitin.urlshortener.service.impl;

import java.util.Objects;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.nitin.urlshortener.feign.KeyGeneratorFeign;
import com.nitin.urlshortener.service.KeyGeneratorService;

/**
 * @author nitinmathew - created on 25/03/2021
 **/
@Service
public class KeyGeneratorServiceImpl implements KeyGeneratorService {

  @Autowired
  private KeyGeneratorFeign keyGeneratorFeign;

  @Override
  public boolean validateCustomKey(String customKey) {
    ResponseEntity<Boolean> responseEntity = keyGeneratorFeign.validateCustomKey(customKey);

    if (Objects.isNull(responseEntity.getBody())) return false;

    return responseEntity.getBody();
  }

  @Override
  public String getUnusedKey() {
    ResponseEntity<String> responseEntity = keyGeneratorFeign.getUnusedKeyAndMarkUsed();
    return responseEntity.getBody();
  }
}
