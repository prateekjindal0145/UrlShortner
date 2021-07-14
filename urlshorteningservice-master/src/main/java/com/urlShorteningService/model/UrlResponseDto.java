package com.urlShorteningService.model;

import java.time.LocalDateTime;

public class UrlResponseDto {
	private String originalUrl;
	private String shortLink;
	private LocalDateTime expirationDate;
	private int count;

	public UrlResponseDto(String originalUrl, String shortLink, LocalDateTime expirationDate, int count) {
		this.originalUrl = originalUrl;
		this.shortLink = shortLink;
		this.expirationDate = expirationDate;
		this.count = count;
	}

	public UrlResponseDto() {
	}

	public String getOriginalUrl() {
		return originalUrl;
	}

	public void setOriginalUrl(String originalUrl) {
		this.originalUrl = originalUrl;
	}

	public String getShortLink() {
		return shortLink;
	}

	public void setShortLink(String shortLink) {
		this.shortLink = shortLink;
	}

	public LocalDateTime getExpirationDate() {
		return expirationDate;
	}

	public void setExpirationDate(LocalDateTime expirationDate) {
		this.expirationDate = expirationDate;
	}

	@Override
	public String toString() {
		return "UrlResponseDto{" + "originalUrl='" + originalUrl + '\'' + ", shortLink='" + shortLink + '\''
				+ ", expirationDate=" + expirationDate + '}';
	}

	public int getCount() {
		return count;
	}

	public void setCount(int count) {
		this.count = count;
	}
}
