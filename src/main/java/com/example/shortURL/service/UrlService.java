package com.example.shortURL.service;

import com.example.shortURL.infrastructure.entity.Url;
import com.example.shortURL.infrastructure.repository.UrlRepository;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UrlService {

    private final UrlRepository urlRepository;

    @Value("${app.base.url:}")
    private String configuredBaseUrl; // Pode vir vazio se não for configurado

    @Value("${server.port:8080}")
    private String serverPort; // Fallback para 8080

    public UrlService(UrlRepository urlRepository) {
        this.urlRepository = urlRepository;
    }


    public Url createShortUrl(String originalUrl, String nameShortCode){
        Optional<Url> existing = urlRepository.findByNameShortCode(nameShortCode);
        if (existing.isPresent()) {
            return existing.get();
        }

        String baseUrl = configuredBaseUrl.isEmpty()
                ? "http://localhost:" + serverPort
                : configuredBaseUrl;

        String shortenUrl = baseUrl + "/r/" + nameShortCode;


        Url urlNew = new Url();
        urlNew.setOriginalUrl(originalUrl);
        urlNew.setNameShortCode(nameShortCode);
        urlNew.setShortenUrl(shortenUrl);

        return urlRepository.save(urlNew);
    }

    public Optional<Url> findByNameShortCode(String nameShortCode){
        return urlRepository.findByNameShortCode(nameShortCode);
    }

}
