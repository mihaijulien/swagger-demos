package swagger.demo3.server;

import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.servlet.DefaultServlet;
import org.eclipse.jetty.servlet.ServletContextHandler;
import org.eclipse.jetty.servlet.ServletHolder;
import org.glassfish.jersey.servlet.ServletContainer;

import java.net.URI;
import java.net.URISyntaxException;

public class Main {

    public static void main(String[] args) throws URISyntaxException {
        Server server = new Server(8080);

        ServletContextHandler servletContextHandler =
                new ServletContextHandler(ServletContextHandler.NO_SESSIONS);

        servletContextHandler.setContextPath("/*");
        server.setHandler(servletContextHandler);

        // hello servlet
        ServletHolder helloServlet = servletContextHandler.addServlet(ServletContainer.class, "/ping/*");
        helloServlet.setInitParameter("jersey.config.server.provider.packages",
                "swagger.demo3.hello");

        // bye servlet
        ServletHolder byeServlet = servletContextHandler.addServlet(ServletContainer.class, "/pong/*");
        byeServlet.setInitParameter("jersey.config.server.provider.packages",
                "swagger.demo3.bye");

        // Setup API resources
        ServletHolder apiServlet = servletContextHandler.addServlet(ServletContainer.class, "/api-docs/hello/*");
        apiServlet.setInitParameter("jersey.config.server.provider.packages", "io.swagger.v3.jaxrs2.integration.resources;" +
                "swagger.demo3.hello");
        apiServlet.setInitParameter("openApi.configuration.prettyPrint", "true");
        ServletHolder api2Servlet = servletContextHandler.addServlet(ServletContainer.class, "/api-docs/bye/*");
        api2Servlet.setInitParameter("jersey.config.server.provider.packages", "io.swagger.v3.jaxrs2.integration.resources;" +
                "swagger.demo3.bye");
        api2Servlet.setInitParameter("openApi.configuration.prettyPrint", "true");

        // Setup Swagger-UI static resources
        ClassLoader loader = Main.class.getClassLoader();
        URI uiURI = loader.getResource("web/ui").toURI();
        // Set up the swagger UI
        ServletHolder swaggerUIHolder = new ServletHolder(DefaultServlet.class);
        swaggerUIHolder.setInitParameter(
                "resourceBase",
                uiURI.toString());
        swaggerUIHolder.setInitParameter(
                "dirAllowed",
                "true");
        swaggerUIHolder.setInitParameter(
                "pathInfoOnly",
                "true");
        servletContextHandler.addServlet(swaggerUIHolder, "/ui/*");


        try {
            server.start();
            server.join();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            server.destroy();
        }
    }
}
