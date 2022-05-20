package org.acme.polyfight;

import io.quarkus.panache.common.Sort;
import org.eclipse.microprofile.rest.client.inject.RestClient;

import javax.transaction.Transactional;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import java.util.List;
import java.util.Random;

@Path("/fight")
public class FightResource {

    @RestClient VillainService villains;
    @RestClient HeroService heroes;

    @GET
    @Path("/hello")
    public String hello() {
        return "congruence";
    }

    record FightResponse(Fighter hero, Fighter villain, String winner) {

    }

    @GET
    @Transactional
    public FightResponse fight() {
        Fighter v  = villains.getRandomFighter();
        Fighter h = heroes.getRandomFighter();

        Fight fight = fight(h, v);
        fight.persist();
        return new FightResponse(h, v, fight.winner);
    }

    @GET
    @Path("/fights")
    public List<Fight> getLast10Fights() {
        return Fight.findAll(Sort.descending("timestamp")).page(0, 10).list();
    }


    private Fight fight(Fighter hero, Fighter villain) {
        Fight fight = new Fight();
        fight.hero = hero.name();
        fight.villain = villain.name();
        fight.timestamp = System.currentTimeMillis();

        Random random = new Random();
        // Very complex logic...
        int h = random.nextInt(100);
        int v = random.nextInt(100);

        fight.winner = hero.level() + h > villain.level() + v ? hero.name() : villain.name();
        return fight;
    }
}
