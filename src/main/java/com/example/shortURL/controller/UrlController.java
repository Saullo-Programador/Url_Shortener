package com.example.shortURL.controller;

import com.example.shortURL.dto.UrlRequestDto;
import com.example.shortURL.dto.UrlResponseDto;
import com.example.shortURL.infrastructure.entity.Url;
import com.example.shortURL.infrastructure.mapper.UrlMapper;
import com.example.shortURL.service.UrlService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/")
public class UrlController {

    private final UrlService urlService;

    public UrlController(UrlService urlService) {
        this.urlService = urlService;
    }

    @GetMapping("/")
    public String hello() {
        return "Sejam Bem-Vindo ao seu Encurtador!";
    }

    @PostMapping("/shorten")
    public ResponseEntity<UrlResponseDto> shortenUrl(@RequestBody UrlRequestDto urlRequest) {
        String nameShortCode = urlRequest.getNameShortCode();
        if (nameShortCode == null || nameShortCode.isEmpty()) {
            nameShortCode = generateShortCode();
        }

        Url url = urlService.createShortUrl(
                urlRequest.getNameSite(),
                urlRequest.getOriginalUrl(),
                nameShortCode
        );

        return ResponseEntity.ok(UrlMapper.toDto(url));
    }

    @GetMapping("/list")
    public ResponseEntity<List<UrlResponseDto>> getAll() {
        List<Url> urls = urlService.urlList();
        if (urls.isEmpty()) {
            return ResponseEntity.noContent().build();
        }

        List<UrlResponseDto> response = urls.stream()
                .map(UrlMapper::toDto)
                .collect(Collectors.toList());

        return ResponseEntity.ok(response);
    }

    @GetMapping("/r/{code}")
    public ResponseEntity<Void> redirect(@PathVariable String code) {
        Url url = urlService.findByShortCodeOrThrow(code);
        return ResponseEntity.status(302)
                .location(URI.create(url.getOriginalUrl()))
                .build();
    }

    @GetMapping("/url/{code}")
    public ResponseEntity<UrlResponseDto> getUrlInfo(@PathVariable String code) {
        Url url = urlService.findByShortCodeOrThrow(code);
        return ResponseEntity.ok(UrlMapper.toDto(url));
    }

    @DeleteMapping("/delete/{code}")
    public ResponseEntity<Void> delete(@PathVariable String code) {
        urlService.deleteByCode(code);
        return ResponseEntity.noContent().build();
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
