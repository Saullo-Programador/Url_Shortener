package com.example.shortURL.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public class UrlResponseDto {
    private String id;
    private String nameSite;
    private String originalUrl;
    private String shortenUrl;
    private String nameShortCode;

    public UrlResponseDto(){}

    public UrlResponseDto(String id, String nameSite, String originalUrl, String shortenUrl, String nameShortCode){
        this.id = id;
        this.nameSite = nameSite;
        this.originalUrl = originalUrl;
        this.shortenUrl = shortenUrl;
        this.nameShortCode = nameShortCode;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getNameSite() {
        return nameSite;
    }

    public void setNameSite(String nameSite) {
        this.nameSite = nameSite;
    }

    public String getOriginalUrl() {
        return originalUrl;
    }

    public void setOriginalUrl(String originalUrl) {
        this.originalUrl = originalUrl;
    }

    public String getShortenUrl() {
        return shortenUrl;
    }

    public void setShortenUrl(String shortenUrl) {
        this.shortenUrl = shortenUrl;
    }

    public String getNameShortCode() {
        return nameShortCode;
    }

    public void setNameShortCode(String nameShortCode) {
        this.nameShortCode = nameShortCode;
    }
}
