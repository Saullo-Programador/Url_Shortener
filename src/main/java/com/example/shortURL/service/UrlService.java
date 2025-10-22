package com.example.shortURL.service;

import com.example.shortURL.infrastructure.entity.Url;
import com.example.shortURL.infrastructure.repository.UrlRepository;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

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


    public Url createShortUrl(String nameSite, String originalUrl, String nameShortCode){
        Optional<Url> existing = urlRepository.findByNameShortCode(nameShortCode);
        if (existing.isPresent()) {
            return existing.get();
        }

        String baseUrl = configuredBaseUrl.isEmpty()
                ? "http://localhost:" + serverPort
                : configuredBaseUrl;

        String shortenUrl = baseUrl.endsWith("/")
                ? baseUrl + "r/" + nameShortCode
                : baseUrl + "/r/" + nameShortCode;



        Url urlNew = new Url();
        urlNew.setNameSite(nameSite);
        urlNew.setOriginalUrl(originalUrl);
        urlNew.setNameShortCode(nameShortCode);
        urlNew.setShortenUrl(shortenUrl);

        return urlRepository.save(urlNew);
    }

    public List<Url> urlList(){
        return urlRepository.findAll(Sort.by(Sort.Direction.ASC, "id"));
    }

    public Optional<Url> findByNameShortCode(String nameShortCode){
        return urlRepository.findByNameShortCode(nameShortCode);
    }

}
