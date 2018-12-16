package org.ab.student.config;

import io.swagger.jaxrs.listing.ApiListingResource;
import io.swagger.jaxrs.listing.SwaggerSerializers;

import org.ab.student.resource.StudentResource;
import org.glassfish.jersey.server.ResourceConfig;


public class AppResourceConfig extends ResourceConfig {

    public AppResourceConfig() {
        packages(StudentResource.class.getPackage().getName());

        
        register(ApiListingResource.class);
        register(SwaggerSerializers.class);

        register(Hk2GuiceBridgeFeature.class);
    }
}
