package com.tinyurl.repository;

import org.springframework.data.repository.CrudRepository;

import com.tinyurl.entity.TinyUrlLinkDo;

public interface TinyUrlRepository extends CrudRepository<TinyUrlLinkDo, String> {

	TinyUrlLinkDo findByFullUrl(String inputUrl);
}
