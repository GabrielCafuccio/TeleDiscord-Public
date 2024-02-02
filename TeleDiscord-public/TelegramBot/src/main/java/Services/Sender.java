package Services;

import Utils.TextMessage;
import com.google.gson.Gson;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

//TODO: change hardcoded endpoint
public class Sender {
    private String userName;
    private String textMessage;

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public void setTextMessage(String textMessage) {
        this.textMessage = textMessage;
    }

    public void sendMessage() throws URISyntaxException, IOException, InterruptedException {
        //Build http message
        TextMessage textMessage = new TextMessage(this.textMessage, this.userName);
        Gson gson = new Gson();
        String jsonRequest = gson.toJson(textMessage);
//        System.out.println("Message to send: " + jsonRequest);

        //Build the http post request
        HttpRequest postRequest = HttpRequest.newBuilder()
                .uri(new URI("http://localhost:6970/message"))
                .POST(HttpRequest.BodyPublishers.ofString(jsonRequest))
                .build();

        //Send the http post request
        HttpClient httpClient = HttpClient.newHttpClient();
        HttpResponse<InputStream> postResponse = httpClient.send(postRequest, HttpResponse.BodyHandlers.ofInputStream());
//        System.out.println(postResponse.body());

    }

}
