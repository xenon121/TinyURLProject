package com.tinyurl.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tinyurl.entity.TinyUrlLinkDo;
import com.tinyurl.exception.TinyUrlAlreadyExistException;
import com.tinyurl.repository.TinyUrlRepository;
import com.tinyurl.servic.TinyUrlService;

import net.bytebuddy.utility.RandomString;

@Service
public class TinyUrlServiceImpl implements TinyUrlService {

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
		
		return tinyUrlRepository.save(tinyUrlLinkDo);
	}

}
