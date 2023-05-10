package models;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.OneToMany;

import play.db.jpa.Model;

@Entity
public class Station extends Model
{
    public String name;
    @OneToMany(cascade = CascadeType.ALL)
    public List<Reading> readings = new ArrayList<Reading>();

    //constructor
    public Station(String name)
    {
        this.name = name;
    }

    //getters
    public String getName()
    {
        return name;
    }

    public List<Reading> getReadings()
    {
        return readings;
    }

    //setters
    public void setName(String name)
    {
        this.name = name;
    }

    public void setReadings(List<Reading> readings)
    {
        this.readings = readings;
    }

    //methods

    public Reading listLatestReading() {
        if (readings.size()>0) {
            Reading latestReading = readings.get(readings.size() - 1);
            return latestReading;
        }
        else {
            Reading latestReading = new Reading(0, 0, 0, 0, 0);
            return latestReading;
        }
    }




}
