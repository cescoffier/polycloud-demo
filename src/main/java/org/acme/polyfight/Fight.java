package org.acme.polyfight;

import io.quarkus.hibernate.orm.panache.PanacheEntity;

import javax.persistence.Entity;

@Entity
public class Fight extends PanacheEntity {

    public String hero;
    public String villain;
    public String winner;
    public long timestamp;
}
