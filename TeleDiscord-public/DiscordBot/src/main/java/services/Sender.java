package services;

import com.google.gson.Gson;
import utils.TextMessage;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

public class Sender {

    public Sender() {
    }

    //TODO: change hardocded endopint
    public void sendMessage(String author, String text) throws URISyntaxException, IOException, InterruptedException {
        //Build http message
        TextMessage textMessage = new TextMessage(author, text);
        Gson gson = new Gson();
        String jsonRequest = gson.toJson(textMessage);

        //Build the http post request
        HttpRequest postRequest = HttpRequest.newBuilder()
                .uri(new URI("http://localhost:6969/message"))
                .POST(HttpRequest.BodyPublishers.ofString(jsonRequest))
                .build();

        //Send the http post request
        HttpClient httpClient = HttpClient.newHttpClient();
        HttpResponse<String> postResponse = httpClient.send(postRequest, HttpResponse.BodyHandlers.ofString());
//        System.out.println(postResponse.body());
    }


}

