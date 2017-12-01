package org.lab04.post;

import java.util.ArrayList;
import java.util.List;

import org.apache.http.Consts;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;

public class Main {
	public static void main(String[] args) {	
		String uri = "http://localhost:8080/registration";

		List<NameValuePair> formparams = new ArrayList<NameValuePair>();
		formparams.add(new BasicNameValuePair("name", "philip"));
		formparams.add(new BasicNameValuePair("password", "password"));
		formparams.add(new BasicNameValuePair("mailId", "abcdef@abcdef.com"));
		UrlEncodedFormEntity requestBody = new UrlEncodedFormEntity(formparams, Consts.UTF_8);

		HttpPost httppost = new HttpPost(uri);
		httppost.setEntity(requestBody);

		HttpClient httpclient = HttpClientBuilder.create().build();
		try {
			HttpResponse response = httpclient.execute(httppost);
	
			HttpEntity responseBody = response.getEntity();
	
			if (responseBody != null) {
				System.out.println(EntityUtils.toString(responseBody));
			}
		}
		catch (Exception ex) {
			ex.getStackTrace();
		}
	}
}