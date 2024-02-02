package events;

import utils.HttpMessageHandler;
import com.sun.net.httpserver.HttpServer;


import java.io.IOException;
import java.net.InetSocketAddress;

public class HttpMessageReceiverEventListener {
    private int port;
    public HttpMessageReceiverEventListener(int port) {
        this.port = port;
    }

    public void start() throws IOException {
        HttpServer server = HttpServer.create(new InetSocketAddress(port), 0);

        server.createContext("/message", new HttpMessageHandler());

        server.setExecutor(null);
        server.start();
    }
}
