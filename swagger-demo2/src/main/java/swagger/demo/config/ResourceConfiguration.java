package swagger.demo.config;

import io.swagger.v3.jaxrs2.integration.resources.OpenApiResource;
import org.glassfish.jersey.server.ResourceConfig;
import swagger.demo.rest.RestResource;
import swagger.demo.rest.RestResource2;

public class ResourceConfiguration extends ResourceConfig {
    public ResourceConfiguration() {
        registerClasses(RestResource.class, RestResource2.class);
        OpenApiResource openApiResource = new OpenApiResource();
        register(openApiResource);
    }
}
