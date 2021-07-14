package com.urlShorteningService.service;

import java.nio.charset.StandardCharsets;
import java.time.LocalDateTime;

import javax.transaction.Transactional;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.google.common.hash.Hashing;
import com.urlShorteningService.model.Url;
import com.urlShorteningService.model.UrlDto;
import com.urlShorteningService.repository.UrlRepository;

@Component
public class UrlServiceImpl implements UrlService {

	private static final Logger logger = LoggerFactory.getLogger(UrlServiceImpl.class);
	@Autowired
	private UrlRepository urlRepository;

	@Override
	public Url generateShortLink(UrlDto urlDto) {

		if (StringUtils.isNotEmpty(urlDto.getUrl())) {
			String encodedUrl = encodeUrl(urlDto.getUrl());
			Url urlToPersist = new Url();
			urlToPersist.setCreationDate(LocalDateTime.now());
			urlToPersist.setOriginalUrl(urlDto.getUrl());
			urlToPersist.setShortLink(encodedUrl);
			urlToPersist
					.setExpirationDate(getExpirationDate(urlDto.getExpirationDate(), urlToPersist.getCreationDate()));
			Url urlToRet = persistShortLink(urlToPersist);

			if (urlToRet != null)
				return urlToRet;

			return null;
		}
		return null;
	}

	private LocalDateTime getExpirationDate(String expirationDate, LocalDateTime creationDate) {
		if (StringUtils.isBlank(expirationDate)) {
			return creationDate.plusSeconds(60);
		}
		LocalDateTime expirationDateToRet = LocalDateTime.parse(expirationDate);
		return expirationDateToRet;
	}

	private String encodeUrl(String url) {
		String encodedUrl = "";
		LocalDateTime time = LocalDateTime.now();
		encodedUrl = Hashing.murmur3_32().hashString(url.concat(time.toString()), StandardCharsets.UTF_8).toString();
		return encodedUrl;
	}

	@Override
	public Url persistShortLink(Url url) {
		Url urlToRet = urlRepository.save(url);
		return urlToRet;
	}

	@Override
	@Transactional
	public Url getEncodedUrl(String url) {
		Url urlToRet = urlRepository.findByShortLink(url);
		if (urlToRet != null) {
			int cnt = urlToRet.getCount();
			urlToRet.setCount(cnt++);
			urlRepository.setCountByShortLink(cnt, urlToRet.getShortLink());
		}
		return urlToRet;
	}

	@Override
	public void deleteShortLink(Url url) {

		urlRepository.delete(url);
	}

	@Override
	public Url getUrlCount(String url) {
		Url urlToRet = urlRepository.findByShortLink(url);
		return urlToRet;
	}
}
