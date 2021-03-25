package com.nitin.urlshortener.util;

import java.util.Date;

import org.apache.commons.lang3.StringUtils;

import com.nitin.urlshortener.entity.Url;
import com.nitin.urlshortener.model.constant.Constants;

/**
 * @author nitinmathew - created on 26/03/2021
 **/
public class CommonUtil {

  public static Url getUrl(String key, String originalUrl) {
    return Url.builder()
        .key(key)
        .originalUrl(originalUrl)
        .createdDate(new Date())
        .build();
  }

  public static void validateCustomKeyLength(String customKey) throws Exception {
    if (StringUtils.isNotBlank(customKey) && customKey.length() > Constants.CUSTOM_KEY_LENGTH) {
      throw new Exception(String.format("Custom key length must not be greater than %s", Constants.CUSTOM_KEY_LENGTH));
    }
  }

  public static void validateOriginalUrl(String originalUrl) {
    // Can be implemented
  }
}
