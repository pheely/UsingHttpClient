package org.lab02.url_builder;

import java.net.URI;

import org.apache.http.Header;
import org.apache.http.HeaderIterator;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
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
		HttpClient client = HttpClientBuilder.create().build();
		
		try {
			URI uri = new URIBuilder().setScheme("https")
									  .setHost("www.google.com")
									  .setPath("/search")
									  .addParameter("q", "Donald Trump")
									  .build();
			logger.info(uri.toString());
			HttpResponse response = client.execute(new HttpGet(uri));
			
			logger.info(response.getProtocolVersion().toString());
			logger.info(Integer.toString(response.getStatusLine().getStatusCode()));
			logger.info(response.getStatusLine().getReasonPhrase());
			logger.info(response.getStatusLine().toString());
			
			logger.info("Headers:");
				
			HeaderIterator it = response.headerIterator();
			while (it.hasNext()) {
				Header header = (Header) it.next();
				logger.info(header.toString());
				
				/*
				logger.info("Header: name = " + header.getName() + " value = " + header.getValue());
				HeaderElement[] elements = header.getElements();
				if (elements != null) {
					for (int index = 0; index < elements.length; index++) {
						logger.info("\tElement: name = " + elements[index].getName() + " value = " + elements[index].getValue());
						NameValuePair[] pairs = elements[index].getParameters();
						if (pairs != null) {
							for (int idx = 0; idx < pairs.length; idx++) {
								logger.info("\t\tNameValuePair: name = " + pairs[idx].getName() + " value = " + pairs[idx].getValue());
							}
						}
					}
				}
				*/
			}
			
			logger.info("Response Body:");
			HttpEntity entity = response.getEntity();
			if (entity != null) {
				logger.info(EntityUtils.toString(entity));
			}
		}
		catch (Exception ex) {
			ex.printStackTrace(System.err);
		}
	}
}
