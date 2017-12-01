package org.lab01.get_status_code;

import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.HttpClientBuilder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class Execution implements CommandLineRunner {
	Logger logger = LoggerFactory.getLogger(getClass());
	
	@Override
	public void run(String... args) throws Exception {
		String SAMPLE_URL = "https://www.google.com";
		HttpClient client = HttpClientBuilder.create().build();
		try {
			HttpResponse response = client.execute(new HttpGet(SAMPLE_URL));
			
			logger.info(response.getProtocolVersion().toString());
			logger.info(Integer.toString(response.getStatusLine().getStatusCode()));
			logger.info(response.getStatusLine().getReasonPhrase());
			logger.info(response.getStatusLine().toString());
		}
		catch (Exception ex) {
			ex.printStackTrace(System.err);
		}
	}

}
