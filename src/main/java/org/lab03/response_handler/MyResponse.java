package org.lab03.response_handler;

import java.util.List;

public class MyResponse {
	String statusLine;
	int statusCode;
	String reasonPhrase;
	String body;
	List<String> headers;
	
	public String toString() {
		StringBuffer sb = new StringBuffer();
		sb.append("\tStatus: " + statusLine).append("\n")
		  .append("\tStatus Code: " + Integer.toString(statusCode)).append("\n")
		  .append("\tReason Phrase: " + reasonPhrase).append("\n")
		  .append("\tHeaders:\n");
		if (headers != null) {
			for (String header:headers) {
				sb.append("\t\t"+ header).append("\n");
			}
		}
		sb.append("\tBody:\n")
		  .append("\t\t" + body);
		
		return sb.toString();
	}
	
	public String getStatusLine() {
		return statusLine;
	}
	public void setStatusLine(String statusLine) {
		this.statusLine = statusLine;
	}
	public int getStatusCode() {
		return statusCode;
	}
	public void setStatusCode(int statusCode) {
		this.statusCode = statusCode;
	}
	public String getReasonPhrase() {
		return reasonPhrase;
	}
	public void setReasonPhrase(String reasonPhrase) {
		this.reasonPhrase = reasonPhrase;
	}
	public String getBody() {
		return body;
	}
	public void setBody(String body) {
		this.body = body;
	}

	public List<String> getHeaders() {
		return headers;
	}

	public void setHeaders(List<String> headers) {
		this.headers = headers;
	}
}