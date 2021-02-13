package com.tinyurl.service.impl;

import java.util.Optional;

import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.tinyurl.Response.CreateTinyUrlResponse;
import com.tinyurl.entity.TinyUrlLinkDo;
import com.tinyurl.exception.IdDoesNotExistsException;
import com.tinyurl.exception.TinyUrlAlreadyExistException;
import com.tinyurl.repository.TinyUrlRepository;
import com.tinyurl.servic.TinyUrlService;

import net.bytebuddy.utility.RandomString;

@Service
public class TinyUrlServiceImpl implements TinyUrlService {

	@Value(value = "${tiny.url}")
	private String tinyUrl;
	
	@Autowired
	private TinyUrlRepository tinyUrlRepository;
	
	@Override
	public Object createTinyUrl(String inputUrl) throws Exception {
		
		TinyUrlLinkDo tinyUrlLinkDo = tinyUrlRepository.findByFullUrl(inputUrl);
		
		if(tinyUrlLinkDo != null) {
			throw new TinyUrlAlreadyExistException();
		}
		
		String ranString = RandomString.make(7);
		
		tinyUrlLinkDo = new TinyUrlLinkDo();
		
		tinyUrlLinkDo.setTinyUrlId(ranString);
		tinyUrlLinkDo.setFullUrl(inputUrl);
		tinyUrlLinkDo.setAccessCount(0);
		
		tinyUrlLinkDo = tinyUrlRepository.save(tinyUrlLinkDo);
		
		CreateTinyUrlResponse createTinyUrlResponse = new CreateTinyUrlResponse();
		
		createTinyUrlResponse.setTinyUrl(tinyUrl+"/"+tinyUrlLinkDo.getTinyUrlId());
		
		return createTinyUrlResponse;
	}

	@Override
	public void redirectToFullUrl(String tinyUrl, HttpServletResponse httpServletResponse) throws IdDoesNotExistsException, Exception {
		
		Optional<TinyUrlLinkDo> tinyUrlLinkDo = tinyUrlRepository.findById(tinyUrl);
		
		if(!tinyUrlLinkDo.isPresent()) {
			throw new IdDoesNotExistsException();
		}
		
		if(tinyUrlLinkDo.get().getAccessCount() == null) {
			tinyUrlLinkDo.get().setAccessCount(1);
			
		}else {
			tinyUrlLinkDo.get().setAccessCount(tinyUrlLinkDo.get().getAccessCount() + 1);
		}
		
		tinyUrlRepository.save(tinyUrlLinkDo.get());
		
		httpServletResponse.setHeader("Location", tinyUrlLinkDo.get().getFullUrl());
		httpServletResponse.setStatus(302);
		
	}

	@Override
	public Object tinyUrlDetailsById(String tinyUrl) throws IdDoesNotExistsException, Exception {
		
		Optional<TinyUrlLinkDo> tinyUrlLinkDo = tinyUrlRepository.findById(tinyUrl);
		
		if(!tinyUrlLinkDo.isPresent()) {
			throw new IdDoesNotExistsException();
		}
	
		return tinyUrlLinkDo;
	}

}
