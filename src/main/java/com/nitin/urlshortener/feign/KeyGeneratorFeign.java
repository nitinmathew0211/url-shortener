package com.nitin.urlshortener.feign;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * @author nitinmathew - created on 25/03/2021
 **/
@FeignClient(value = "keyGeneratorFeign", url = "${key.generator.feign.endpoint}")
public interface KeyGeneratorFeign {

  @PutMapping(value = "/api/key/get-unused-key", produces = MediaType.APPLICATION_JSON_VALUE)
  ResponseEntity<String> getUnusedKeyAndMarkUsed();

  @PutMapping(value = "/api/key/validate-custom-key", produces = MediaType.APPLICATION_JSON_VALUE)
  ResponseEntity<Boolean> validateCustomKey(@RequestParam("customKey") String customKey);
}
