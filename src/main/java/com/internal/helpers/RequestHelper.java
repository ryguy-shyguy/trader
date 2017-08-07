package com.internal.helpers;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.HttpClientBuilder;

public class RequestHelper {

    public static void baseRequester() throws IOException {
        BufferedReader responseBody = null;
        HttpClient client = HttpClientBuilder.create().build();

        try {
            //Define a HttpGet request
            HttpGet request = new HttpGet("https://api.tradier.com/v1/user/profile");

            //Set Http Headers
            request.addHeader("Accept" , "application/xml");
            request.addHeader("Authorization", "Bearer YOUR_ACCESS_TOKEN");

            //Invoke the service
            HttpResponse response = client.execute(request);

            //Verify if the response is valid
            int statusCode = response.getStatusLine().getStatusCode();
            if(statusCode!=200) {
                throw new RuntimeException("Failed with HTTP error code : " + statusCode);
            } else {
                //If valid, get the response
                responseBody = new BufferedReader (new InputStreamReader(response.getEntity().getContent()));
                String line = "";
                while ((line = responseBody.readLine()) != null) {
                    System.out.println(line);
                }
            }
        }
        catch(Exception e) {
            e.printStackTrace();
        } finally {
            if(responseBody!=null)
                responseBody.close();
        }
    }
}
