package Services;

import Utils.HttpMessageHandler;
import com.sun.net.httpserver.HttpServer;


import java.io.IOException;
import java.net.InetSocketAddress;

public class Receiver {
    private int port;
    public Receiver(int port) {
        this.port = port;
    }

    public void start() throws IOException {
        HttpServer server = HttpServer.create(new InetSocketAddress(port), 0);

        server.createContext("/message", new HttpMessageHandler());

        server.setExecutor(null);
        server.start();
    }
}
