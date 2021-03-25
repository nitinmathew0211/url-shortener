package com.nitin.urlshortener.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.nitin.urlshortener.entity.Url;

/**
 * @author nitinmathew - created on 25/03/2021
 **/
@Repository
public interface UrlRepository extends MongoRepository<Url, String> {

  Url findByKey(String key);
}
