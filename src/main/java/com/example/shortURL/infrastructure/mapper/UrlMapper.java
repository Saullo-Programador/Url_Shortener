package com.example.shortURL.infrastructure.mapper;


import com.example.shortURL.dto.UrlResponseDto;
import com.example.shortURL.infrastructure.entity.Url;

public class UrlMapper {

    public static UrlResponseDto toDto(Url url) {
        return new UrlResponseDto(
                url.getId(),
                url.getNameSite(),
                url.getOriginalUrl(),
                url.getShortenUrl(),
                url.getNameShortCode()
        );
    }
}