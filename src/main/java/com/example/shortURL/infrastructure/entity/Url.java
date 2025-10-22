package com.example.shortURL.infrastructure.entity;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@Document(collection = "urls")
public class Url {
    @Id
    private String id;

    @NotBlank
    private String nameSite;

    @NotBlank
    private String originalUrl;

    @NotBlank
    private String shortenUrl;

    @NotBlank
    @Size(min = 3, message = "O Name should have at least 3 characters")
    private String nameShortCode;

    public Url(String nameSite, String originalUrl, String nameShortCode, String shortenUrl) {
        this.nameSite = nameSite;
        this.originalUrl = originalUrl;
        this.nameShortCode = nameShortCode;
        this.shortenUrl = shortenUrl;
    }

    public Url() {

    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getNameSite() {return nameSite; }

    public void  setNameSite(String nameSite) {this.nameSite = nameSite; }

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

    public String getShortenUrl() {
        return shortenUrl;
    }

    public void setShortenUrl(String shortenUrl) {
        this.shortenUrl = shortenUrl;
    }
}
