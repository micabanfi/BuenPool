package root.Ride;

import root.User.Person;
import root.User.Vehicle;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;

public class Ride {
    private Route route;
    private Vehicle vehicle;
    private Person driver;
    private Permissions permissions;
    private Date date;

    public Ride(Route route, Vehicle vehicle, Person driver, Permissions permissions, Date date){
        this.route=route;
        this.vehicle=vehicle;
        this.driver=driver;
        this.permissions = permissions;
        this.date=date;
    }

    public Route getRoute(){
        return route;
    }

    public Person getDriver(){
        return driver;
    }

    public Permissions getPermissions(){
        return permissions;
    }

    public Vehicle getVehicle(){
        return vehicle;
    }

}
