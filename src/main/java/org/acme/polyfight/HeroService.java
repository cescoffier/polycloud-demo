package org.acme.polyfight;

import org.eclipse.microprofile.faulttolerance.Fallback;
import org.eclipse.microprofile.faulttolerance.Timeout;
import org.eclipse.microprofile.rest.client.inject.RegisterRestClient;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import java.time.temporal.ChronoUnit;

@RegisterRestClient(configKey = "heroes-service")
public interface HeroService {

    @GET
    @Path("/random")
    @Timeout(value = 3, unit = ChronoUnit.SECONDS)
    @Fallback(fallbackMethod = "getFallbackFighter")
    Fighter getRandomFighter();

    default Fighter getFallbackFighter() {
        return new Fighter("clement", "", 3);
    }
}
