package com.urlShorteningService.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.urlShorteningService.model.Url;

@Repository
public interface UrlRepository extends JpaRepository<Url, Long> {
	public Url findByShortLink(String shortLink);

	@Modifying
	@Query("update Url u set u.count= ?1 where u.shortLink= ?2")
	public void setCountByShortLink(int count, String shortLink);
}
