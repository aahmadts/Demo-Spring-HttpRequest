package com.example.demo;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

@RestController
public class PersonController {

    @RequestMapping(path = "/app")
    public String getPerson(@RequestParam(name="url", defaultValue = "http://www.ferhengo.com") String uri) {
        return this.makeRequest(uri); //new Person("Mario");
    }

    private String makeRequest(String url) {
        HttpURLConnection.setFollowRedirects(true);
        try {
            URL request_url = new URL(url);
            HttpURLConnection http_conn = (HttpURLConnection)request_url.openConnection();
            http_conn.setInstanceFollowRedirects(true);

            BufferedReader bufferRead = new BufferedReader(new InputStreamReader(http_conn.getInputStream()));
            System.out.println(String.valueOf(bufferRead));
            String body = "";
            for (String line = bufferRead.readLine(); line != null; line = bufferRead.readLine()) {
                System.out.println(line);
                body = body + line;
            }

            bufferRead.close();
            return body;

        } catch (Exception error ){
            System.out.println(error.getMessage());;
        }

        return  null;
    }
}
