package com.morandev.repoconpostgresql.model;

import javax.persistence.*;

@Entity
@Table( name = "flights" )
public class Flight {
    @Id
    @GeneratedValue( strategy = GenerationType.AUTO )
    private long id;
    private boolean arrived;
    private String dateandtime;
    private String route;

    public Flight() {}

    public long getId() {
        return id;
    }

    public String getDateandtime() {
        return dateandtime;
    }

    public String getRoute() {
        return route;
    }

    public boolean getArrived() {
        return arrived;
    }

    public void setArrived(boolean arrived) {
        this.arrived = arrived;
    }

    public void setRoute(String route) {
        this.route = route;
    }

    public void setDateandtime(String dateandtime) {
        this.dateandtime = dateandtime;
    }

    @Override
    public String toString() {
        return "Flight{" +
                "id=" + id +
                ", arrived=" + arrived +
                ", dateandtime='" + dateandtime + '\'' +
                ", route='" + route + '\'' +
                '}';
    }
}
