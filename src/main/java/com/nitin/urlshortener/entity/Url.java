package com.nitin.urlshortener.entity;

import java.util.Date;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import com.nitin.urlshortener.model.fieldname.UrlFieldNames;
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
@Document(collection = Url.COLLECTION_NAME)
public class Url {
  public static final String COLLECTION_NAME = "url";

  @Id
  @Field(value = UrlFieldNames.ID)
  private String id;

  // Indexed and unique
  @Field(value = UrlFieldNames.KEY)
  private String key;

  @Field(value = UrlFieldNames.ORIGINAL_URL)
  private String originalUrl;

  @Field(value = UrlFieldNames.CREATED_DATE)
  private Date createdDate;
}
