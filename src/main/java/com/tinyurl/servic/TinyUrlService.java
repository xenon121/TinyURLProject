package com.tinyurl.servic;

import javax.servlet.http.HttpServletResponse;

import com.tinyurl.exception.IdDoesNotExistsException;
import com.tinyurl.exception.TinyUrlAlreadyExistException;

public interface TinyUrlService {

	Object createTinyUrl(String inputUrl) throws TinyUrlAlreadyExistException, Exception;

	void redirectToFullUrl(String tinyUrl, HttpServletResponse httpServletResponse) throws IdDoesNotExistsException, Exception;

	Object tinyUrlDetailsById(String tinyUrl) throws IdDoesNotExistsException, Exception;

}
