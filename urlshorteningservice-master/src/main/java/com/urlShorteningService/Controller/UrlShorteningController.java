package com.urlShorteningService.Controller;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import com.urlShorteningService.model.Url;
import com.urlShorteningService.model.UrlDto;
import com.urlShorteningService.model.UrlErrorResponseDto;
import com.urlShorteningService.model.UrlResponseDto;
import com.urlShorteningService.service.UrlService;

@RestController
public class UrlShorteningController {
	@Autowired
	private UrlService urlService;

	@PostMapping("/generate")
	public ModelAndView generateShortLink(UrlDto urlDto) {
		ModelAndView mv = new ModelAndView("success");
		Url urlToRet = urlService.generateShortLink(urlDto);

		if (urlToRet != null) {
			UrlResponseDto urlResponseDto = new UrlResponseDto();
			urlResponseDto.setOriginalUrl(urlToRet.getOriginalUrl());
			urlResponseDto.setExpirationDate(urlToRet.getExpirationDate());
			urlResponseDto.setShortLink(urlToRet.getShortLink());
			mv.addObject("originalUrl", urlResponseDto.getOriginalUrl());
			mv.addObject("shortLink", urlResponseDto.getShortLink());
			mv.addObject("expirationDate", urlResponseDto.getExpirationDate());
			return mv;
		}

		UrlErrorResponseDto urlErrorResponseDto = new UrlErrorResponseDto();
		urlErrorResponseDto.setStatus("404");
		urlErrorResponseDto.setError("There was an error processing your request. please try again.");
		return mv;

	}

	@GetMapping("/{shortLink}")
	public ResponseEntity<?> redirectToOriginalUrl(@PathVariable String shortLink, HttpServletResponse response)
			throws IOException {

		if (StringUtils.isEmpty(shortLink)) {
			UrlErrorResponseDto urlErrorResponseDto = new UrlErrorResponseDto();
			urlErrorResponseDto.setError("Invalid Url");
			urlErrorResponseDto.setStatus("400");
			return new ResponseEntity<UrlErrorResponseDto>(urlErrorResponseDto, HttpStatus.OK);
		}
		Url urlToRet = urlService.getEncodedUrl(shortLink);

		if (urlToRet == null) {
			UrlErrorResponseDto urlErrorResponseDto = new UrlErrorResponseDto();
			urlErrorResponseDto.setError("Url does not exist or it might have expired!");
			urlErrorResponseDto.setStatus("400");
			return new ResponseEntity<UrlErrorResponseDto>(urlErrorResponseDto, HttpStatus.OK);
		}

		if (urlToRet.getExpirationDate().isBefore(LocalDateTime.now())) {
			urlService.deleteShortLink(urlToRet);
			UrlErrorResponseDto urlErrorResponseDto = new UrlErrorResponseDto();
			urlErrorResponseDto.setError("Url Expired. Please try generating a fresh one.");
			urlErrorResponseDto.setStatus("200");
			return new ResponseEntity<UrlErrorResponseDto>(urlErrorResponseDto, HttpStatus.OK);
		}

		response.sendRedirect(urlToRet.getOriginalUrl());
		return null;
	}

	@RequestMapping("/next")
	public ModelAndView next(Map<String, Object> model) {
		model.put("message", "You are in new page !!");
		ModelAndView mv = new ModelAndView("index");

		return mv;
	}

	@PostMapping("/loadCount")
	public ModelAndView loadCount(Map<String, Object> model) {
		model.put("message", "You are in new page !!");
		ModelAndView mv = new ModelAndView("countInput");

		return mv;
	}

	@PostMapping("/getUrlCount")
	public ModelAndView getUrlCount(UrlResponseDto responseDto) {
		ModelAndView mv = new ModelAndView("next");
		Url urlToRet = urlService.getUrlCount(responseDto.getShortLink());

		if (urlToRet != null) {
			UrlResponseDto urlResponseDto = new UrlResponseDto();
			urlResponseDto.setOriginalUrl(urlToRet.getOriginalUrl());
			//urlResponseDto.setExpirationDate(urlToRet.getExpirationDate());
			urlResponseDto.setCount(urlToRet.getCount());
			urlResponseDto.setShortLink(urlToRet.getShortLink());
			mv.addObject("originalUrl", urlResponseDto.getOriginalUrl());
			mv.addObject("shortLink", urlResponseDto.getShortLink());
			mv.addObject("count", urlResponseDto.getCount());
			return mv;
		}

		UrlErrorResponseDto urlErrorResponseDto = new UrlErrorResponseDto();
		urlErrorResponseDto.setStatus("404");
		urlErrorResponseDto.setError("There was an error processing your request. please try again.");
		return mv;

	}
}