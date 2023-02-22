package me.coconan.cucumber.fruit.hooks;

import com.sun.net.httpserver.HttpServer;
import io.cucumber.java.After;
import io.cucumber.java.Before;
import me.coconan.cucumber.fruit.App;

public class ServerHooks {
    public static final int PORT = 8807;
    private HttpServer server;

    @Before
    public void setServer() throws Exception {
        server = App.startServer(PORT);
    }

    @After
    public void stopServer() {
        server.stop(0);
    }
}
