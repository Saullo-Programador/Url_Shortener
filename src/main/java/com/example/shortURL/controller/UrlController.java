package com.example.shortURL.controller;

import com.example.shortURL.infrastructure.entity.Url;
import com.example.shortURL.service.UrlService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.Optional;

@RestController
@RequestMapping("/api/url/")
public class UrlController {
    private final UrlService urlService;

    public UrlController(UrlService urlService) {
        this.urlService = urlService;
    }

    // Cria uma nova URL encurtada
    @PostMapping("/shorten")
    public ResponseEntity<Url> shortenUrl(
            @RequestParam String originalUrl,
            @RequestParam(required = false) String nameShortCode
    ){
        if (nameShortCode == null || nameShortCode.isEmpty()){
            nameShortCode = generateShortCode();
        }
        Url url = urlService.createShortUrl(originalUrl, nameShortCode);
        return ResponseEntity.ok(url);
    }

    //Redirecionamento
    @GetMapping("/{code}")
    public ResponseEntity<Void> redirect(@PathVariable String code){
        Optional<Url> urlOptional = urlService.findByNameShortCode(code);
        Url url = urlOptional.get();
        return ResponseEntity.status(302)
                .location(URI.create(url.getOriginalUrl()))
                .build();
    }

    //(Opcional) Retorna os dados de uma URL encurtada pelo código.
    @GetMapping("/url/{code}")
    public ResponseEntity<Url> getUrlInfo(@PathVariable String code) {
        return urlService.findByNameShortCode(code)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    private String generateShortCode() {
        String characters = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";
        StringBuilder code = new StringBuilder();
        for (int i = 0; i < 6; i++) {
            int index = (int) (Math.random() * characters.length());
            code.append(characters.charAt(index));
        }
        return code.toString();
    }
}
