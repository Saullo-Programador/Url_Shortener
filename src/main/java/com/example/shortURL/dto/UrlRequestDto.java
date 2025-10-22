package com.example.shortURL.dto;


public class UrlRequestDto {
    private String nameSite;
    private String originalUrl;
    private String nameShortCode;

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

    public String getNameShortCode() {
        return nameShortCode;
    }

    public void setNameShortCode(String nameShortCode) {
        this.nameShortCode = nameShortCode;
    }
}

