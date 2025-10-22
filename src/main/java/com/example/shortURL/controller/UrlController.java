package com.example.shortURL.controller;

import com.example.shortURL.dto.UrlRequestDto;
import com.example.shortURL.infrastructure.entity.Url;
import com.example.shortURL.service.UrlService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/")
public class UrlController {
    private final UrlService urlService;

    public UrlController(UrlService urlService) {
        this.urlService = urlService;
    }

    @GetMapping("/")
    public String Hello(){
        return "Sejam Bem-Vindo ao seu Encurtador";
    }

    // Cria uma nova URL encurtada
    @PostMapping("/shorten")
    public ResponseEntity<Url> shortenUrl(@RequestParam UrlRequestDto urlRequest){
        String nameShortCode = urlRequest.getNameShortCode();
        if (nameShortCode == null || nameShortCode.isEmpty()){
            nameShortCode = generateShortCode();
        }
        Url url = urlService.createShortUrl(
                urlRequest.getNameSite(),
                urlRequest.getOriginalUrl(),
                nameShortCode
        );
        return ResponseEntity.ok(url);
    }

    @GetMapping("/list")
    public ResponseEntity<List<Url>> getAll(){
        List<Url> urls = urlService.urlList();
        return urls.isEmpty()
                ? ResponseEntity.noContent().build()
                : ResponseEntity.ok(urls);
    }

    //Redirecionamento
    @GetMapping("/r/{code}")
    public ResponseEntity<Void> redirect(@PathVariable String code) {
        Optional<Url> urlOptional = urlService.findByNameShortCode(code);
        if (urlOptional.isEmpty()) {
            return ResponseEntity.notFound().build();
        }

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
