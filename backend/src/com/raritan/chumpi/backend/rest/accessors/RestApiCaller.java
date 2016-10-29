package com.raritan.chumpi.backend.rest.accessors;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

public class RestApiCaller {
	
	public String call(String apiUrl) {
		System.out.println(apiUrl);
		URL url = null;
		try {
			url = new URL(apiUrl);
		} catch (MalformedURLException e) {
			e.printStackTrace();
		}
		HttpURLConnection conn;
		BufferedReader br;
		StringBuilder sb = new StringBuilder();
		try {
			conn = (HttpURLConnection) url.openConnection();
			conn.setRequestMethod("GET");
			conn.setRequestProperty("Accept", "application/json");

			if (conn.getResponseCode() != 200) {
				throw new RuntimeException("Failed : HTTP error code : "
						+ conn.getResponseCode());
			}

			br = new BufferedReader(new InputStreamReader(
					(conn.getInputStream())));

			String output;
			while ((output = br.readLine()) != null) {
				sb.append(output);
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		return sb.toString();
	}

}
