package com.hit.server;

import java.io.Serializable;
import java.util.Map;

import com.google.gson.Gson;

public class Request<T> implements Serializable {
	
	Map<String,String> requestHeaders;
	T requestBody;
	
	public Request(Map<String,String> headers,T body)
	{
		this.requestHeaders = headers;
		this.requestBody = body;
	}

	public Map<String, String> getHeaders() {
		return requestHeaders;
	}

	public void setHeaders(Map<String, String> headers) {
		this.requestHeaders = headers;
	}

	public T getBody() {
		return requestBody;
	}

	public void setBody(T body) {
		this.requestBody = body;
	}

	@Override
	public String toString() {
		return new Gson().toJson(this);
	}
	
	
}
