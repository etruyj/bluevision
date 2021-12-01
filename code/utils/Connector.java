package com.socialvagrancy.bluevision.utils;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;
import java.io.OutputStream;
import java.lang.StringBuilder;
import java.net.URL;
import java.net.HttpURLConnection;

import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSession;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;
import java.security.cert.X509Certificate;

public class Connector
{
	public Connector()
	{
		// Turn off SSL certification since the SSL certificate on
		// these libraries is always flagged.
		TrustManager[] trustAllCerts = new TrustManager[] { 
			new X509TrustManager() {
				public java.security.cert.X509Certificate[] getAcceptedIssuers() { return null; }
				public void checkClientTrusted(X509Certificate[] certs, String authType) {}
				public void checkServerTrusted(X509Certificate[] certs, String authType) {}
			}
		};

		// Install the all-trusting trust manager
		try
		{
			SSLContext sc = SSLContext.getInstance("SSL");
			sc.init(null, trustAllCerts, new java.security.SecureRandom());
			HttpsURLConnection.setDefaultSSLSocketFactory(sc.getSocketFactory());
		
			// Create all-trusting host name verifier
			HostnameVerifier allHostsValid = new HostnameVerifier() {
				public boolean verify(String hostname, SSLSession session) { return true; }
			};

			// Install the all-trustng host verifier
			HttpsURLConnection.setDefaultHostnameVerifier(allHostsValid);
		}
		catch(Exception e)
		{
			System.err.println(e.getMessage());
		}
	}

	public String GET(String httpRequest, String token)
	{
		// Query library with a header (authorization)
		// mostly just for logging in.

		StringBuilder response = new StringBuilder();
		
		// Open connection		
		try
		{
			URL url = new URL(httpRequest);
			HttpURLConnection cxn = (HttpURLConnection) url.openConnection();
		
			// Configuration the connection
			cxn.setRequestMethod("GET");
			cxn.setDoOutput(true);
			cxn.setRequestProperty("Content-Type", "application/json; utf-8");
			cxn.setRequestProperty("Accept", "application/json");
			cxn.setRequestProperty("Authorization", token);
	/*
			OutputStream output = cxn.getOutputStream();
			byte[] input = body.getBytes("utf-8");
			output.write(input, 0, input.length);
	*/		
			
			// Read response	
			BufferedReader br = new BufferedReader(new InputStreamReader(cxn.getInputStream(), "utf-8"));

			String responseLine = null;

			while((responseLine = br.readLine()) != null)
			{
				response.append(responseLine);
			}
		}
		catch(Exception e)
		{
			System.err.println(e.getMessage());
		}

		return response.toString();
	}
	
	public String POST(String httpRequest, String body)
	{
		// Query library without a header (authorization)
		// mostly just for logging in.

		StringBuilder response = new StringBuilder();
		
		// Open connection		
		try
		{
			URL url = new URL(httpRequest);
			HttpURLConnection cxn = (HttpURLConnection) url.openConnection();
		
			// Configuration the connection
			cxn.setRequestMethod("POST");
			cxn.setDoOutput(true);
			cxn.setRequestProperty("Content-Type", "application/json; utf-8");
			cxn.setRequestProperty("Accept", "application/json");
	
			OutputStream output = cxn.getOutputStream();
			byte[] input = body.getBytes("utf-8");
			output.write(input, 0, input.length);
			
			
			// Read response	
			BufferedReader br = new BufferedReader(new InputStreamReader(cxn.getInputStream(), "utf-8"));

			String responseLine = null;

			while((responseLine = br.readLine()) != null)
			{
				response.append(responseLine);
			}
		}
		catch(Exception e)
		{
			System.err.println(e.getMessage());
		}

		return response.toString();
	}
	
	public String POST(String httpRequest, String token, String body)
	{
		// Query library without a header (authorization)
		// mostly just for logging in.

		StringBuilder response = new StringBuilder();
		
		// Open connection		
		try
		{
			URL url = new URL(httpRequest);
			HttpURLConnection cxn = (HttpURLConnection) url.openConnection();
		
			// Configuration the connection
			cxn.setRequestMethod("POST");
			cxn.setDoOutput(true);
			cxn.setRequestProperty("Content-Type", "application/json; utf-8");
			cxn.setRequestProperty("Accept", "application/json");
			cxn.setRequestProperty("Authorization", token);

			OutputStream output = cxn.getOutputStream();
			byte[] input = body.getBytes("utf-8");
			output.write(input, 0, input.length);
			
			
			// Read response	
			BufferedReader br = new BufferedReader(new InputStreamReader(cxn.getInputStream(), "utf-8"));

			String responseLine = null;

			while((responseLine = br.readLine()) != null)
			{
				response.append(responseLine);
			}
		}
		catch(Exception e)
		{
			return e.getMessage();
		}

		return response.toString();
	}
	
}
