package com.hit.server;

import java.util.Map;

public class Response<T> {

	Map<String,String> headers;
	T body;
	
	public Response(Map<String,String> headers, T body)
	{
		this.headers = headers;
		this.body = body;
	}

	public Map<String, String> getHeaders() {
		return headers;
	}

	public void setHeaders(Map<String, String> headers) {
		this.headers = headers;
	}

	public T getBody() {
		return body;
	}

	public void setBody(T body) {
		this.body = body;
	}

	@Override
	public String toString() {
		String s = headers.toString() + " | " + body.toString();
		return s;
	}
	
	
	
}
