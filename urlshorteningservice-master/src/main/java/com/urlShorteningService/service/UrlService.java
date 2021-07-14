package com.urlShorteningService.service;

import org.springframework.stereotype.Service;

import com.urlShorteningService.model.Url;
import com.urlShorteningService.model.UrlDto;

@Service
public interface UrlService
{
    public Url generateShortLink(UrlDto urlDto);
    public Url persistShortLink(Url url);
    public Url getEncodedUrl(String url);
    public  void  deleteShortLink(Url url);
    public Url getUrlCount(String url);

}
