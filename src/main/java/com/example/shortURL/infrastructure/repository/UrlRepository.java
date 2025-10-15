package com.example.shortURL.infrastructure.repository;

import com.example.shortURL.infrastructure.entity.Url;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Optional;

public interface UrlRepository extends MongoRepository<Url, Long> {
    Optional<Url> findByNameShortCode(String nameShortCode);
}
