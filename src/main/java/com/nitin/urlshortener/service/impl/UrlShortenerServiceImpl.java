package com.nitin.urlshortener.service.impl;

import java.util.Objects;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nitin.urlshortener.entity.Url;
import com.nitin.urlshortener.repository.UrlRepository;
import com.nitin.urlshortener.service.KeyGeneratorService;
import com.nitin.urlshortener.service.UrlShortenerService;
import com.nitin.urlshortener.util.CommonUtil;

/**
 * @author nitinmathew - created on 25/03/2021
 **/
@Service
public class UrlShortenerServiceImpl implements UrlShortenerService {

  @Autowired
  private KeyGeneratorService keyGeneratorService;

  @Autowired
  private UrlRepository urlRepository;

  @Override
  public String getOriginalUrl(String key) throws Exception {
    Url url = urlRepository.findByKey(key);

    if (Objects.isNull(url)) {
      throw new Exception("Url Not Found");
    }

    return url.getOriginalUrl();
  }

  @Override
  public String createShortUrl(String customKey, String originalUrl) throws Exception {
    CommonUtil.validateCustomKeyLength(customKey);
    CommonUtil.validateOriginalUrl(originalUrl);

    if (StringUtils.isNotBlank(customKey)) {
      return createShortUrlWithCustomKey(customKey, originalUrl);
    }

    return createShortUrlWithGeneratedKey(originalUrl);
  }

  private String createShortUrlWithCustomKey(String customKey, String originalUrl) throws Exception {
    boolean isValid = keyGeneratorService.validateCustomKey(customKey);

    if (!isValid) {
      throw new Exception("Custom key already taken");
    }

    // Why is util holding the responsibility to build the model?
    //Could have built the url here itself.
    urlRepository.save(CommonUtil.getUrl(customKey, originalUrl));

    return customKey;
  }

  private String createShortUrlWithGeneratedKey(String originalUrl) throws Exception {
    String key = keyGeneratorService.getUnusedKey();

    if (StringUtils.isBlank(key)) {
      throw new Exception("Unused key not present");
    }

    urlRepository.save(CommonUtil.getUrl(key, originalUrl));

    return key;
  }
}
