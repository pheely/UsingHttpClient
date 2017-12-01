package org.lab03.response_handler;

import java.io.IOException;
import java.net.URI;
import java.util.ArrayList;

import org.apache.http.HeaderIterator;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.ResponseHandler;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.utils.URIBuilder;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.util.EntityUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class Execution implements CommandLineRunner {
	Logger logger = LoggerFactory.getLogger(getClass());
	
	@Override
	public void run(String... args) throws Exception {
		ResponseHandler<MyResponse> responseHandler = new ResponseHandler<MyResponse>() {
			@Override
			public MyResponse handleResponse(final HttpResponse response) throws ClientProtocolException, IOException {
				MyResponse res = new MyResponse();
				
				int status = response.getStatusLine().getStatusCode();
				res.setStatusCode(status);
				res.setStatusLine(response.getStatusLine().toString());
				res.setReasonPhrase(response.getStatusLine().getReasonPhrase());
				
				ArrayList<String> headers = new ArrayList<String>();
				HeaderIterator it = response.headerIterator(); 
				while (it.hasNext()) {
					headers.add(it.nextHeader().toString());
				}
				res.setHeaders(headers);
				
				if (status >= HttpStatus.SC_OK && status < HttpStatus.SC_MULTIPLE_CHOICES) {
					HttpEntity entity = response.getEntity();
					if (entity != null) {
						res.setBody(EntityUtils.toString(entity));
					}
					return res;
				}
				else {
					throw new ClientProtocolException("Unexpected response status: " + status);
				}
			}
		};

		
		HttpClient client = HttpClientBuilder.create().build();
		try {
			URI uri = new URIBuilder().setScheme("https")
									  .setHost("www.google.com")
									  .setPath("/search")
									  .addParameter("q", "Donald Trump")
									  .build();
			logger.info("Uri = " + uri);

			logger.info("Response: ");
			MyResponse response = client.execute(new HttpGet(uri), responseHandler);
			logger.info(response.toString());
		}
		catch (Exception ex) {
			ex.printStackTrace(System.err);
		}
	}
}
