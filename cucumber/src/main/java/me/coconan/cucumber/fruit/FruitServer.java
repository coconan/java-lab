package me.coconan.cucumber.fruit;

import com.sun.jersey.spi.container.servlet.ServletContainer;
import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.servlet.ServletContextHandler;
import org.eclipse.jetty.servlet.ServletHolder;

public class FruitServer {
    private final Server server;

    public FruitServer(int port) {
        ServletHolder sh = new ServletHolder(ServletContainer.class);
        sh.setInitParameter("com.sun.jersey.config.property.resourceConfigClass",
                "com.sun.jersey.api.core.PackagesResourceConfig");
        sh.setInitParameter("com.sun.jersey.config.property.packages", "me.coconan.cucumber.fruit");
        sh.setInitParameter("com.sun.jersey.api.json.POJOMappingFeature", "true");

        server = new Server(port);
        ServletContextHandler context =
                new ServletContextHandler(server, "/", ServletContextHandler.SESSIONS);
        context.addServlet(sh, "/");
    }

    public void start() throws Exception {
        server.start();
        System.out.println("Listening on " + server.getURI());
    }

    public void stop() throws Exception {
        server.stop();
        System.out.println("Server shutdown");
    }

    public static void main(String[] args) throws Exception {
        new FruitServer(9988).start();
    }
}
