package com.nitin.urlshortener.model.request;

import java.io.Serializable;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author nitinmathew - created on 25/03/2021
 **/
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UrlShortenerRequest implements Serializable {
  private static final long serialVersionUID = 9085171559576251292L;

  private String originalUrl;
  private String customKey;
}
