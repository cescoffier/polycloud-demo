package org.acme.polyfight;

import org.eclipse.microprofile.rest.client.inject.RegisterRestClient;

import javax.ws.rs.GET;
import javax.ws.rs.Path;

@RegisterRestClient(configKey = "villains-service")
public interface VillainService {

    @GET
    @Path("/random")
    Fighter getRandomFighter();
}
