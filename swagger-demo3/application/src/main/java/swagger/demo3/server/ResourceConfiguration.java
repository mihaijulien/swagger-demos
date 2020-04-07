package swagger.demo3.server;

import io.swagger.v3.jaxrs2.integration.resources.OpenApiResource;
import org.glassfish.jersey.server.ResourceConfig;
import swagger.demo3.hello.HelloEndpoint;
import swagger.demo3.bye.ByeEndpoint;

public class ResourceConfiguration extends ResourceConfig {

    public ResourceConfiguration(){
        register(HelloEndpoint.class);
        register(ByeEndpoint.class);
        OpenApiResource openApiResource = new OpenApiResource();
        register(openApiResource);
    }
}
