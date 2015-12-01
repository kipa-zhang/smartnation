package org.example.task;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class UpdateWeather {
	public String callWebAPI(String datasetName, String keyref) throws Exception {
		// Step 1: Construct URL
		String url = "http://www.nea.gov.sg/api/WebAPI/?dataset=" + datasetName +
				"&keyref=" + keyref;
		System.out.println("----------------------------------" + url);
		// Step 2: Call API Url
		URL obj = new URL(url);
		HttpURLConnection con = (HttpURLConnection)obj.openConnection();
		con.setRequestMethod("GET");
		int responseCode = con.getResponseCode();
		System.out.println("\nSending 'GET' request to URL : "+ url);
		System.out.println("Response Code : "+ responseCode);
		String response = readStream(con.getInputStream());
		// Step 3: Check the response status
		if(responseCode == 200) {
			// Step 3a: If response status == 200
			// print the received xml
			System.out.println("+++++++++++++++++++++++++++++" + response);
			return response;
		} else {
			// Step 3b: If response status != 200
			// print the error received from server
			System.out.println("Error in accessing API - " +
					readStream(con.getErrorStream()));
			return "";
		}
	}
	// Read the responded result
	private String readStream(InputStream inputStream) throws Exception {
		BufferedReader reader = new BufferedReader(
				new InputStreamReader(inputStream));
		String inputLine;
		StringBuffer response = new StringBuffer();
		while((inputLine = reader.readLine()) != null) {
			response.append(inputLine);
		}
		reader.close();
		return response.toString();
	}
}
