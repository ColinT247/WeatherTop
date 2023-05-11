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
    public double latitude;
    public double longitude;
    @OneToMany(cascade = CascadeType.ALL)
    public List<Reading> readings = new ArrayList<Reading>();

    //constructor
    public Station(String name, double latitude, double longitude)
    {
        this.name = name;
        this.latitude = latitude;
        this.longitude = longitude;
    }

    //getters
    public String getName()
    {
        return name;
    }

    public double getLatitude(){
        return latitude;
    }

    public double getLongitude(){
        return longitude;
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

    public void setLatitude(double latitude){
        this.latitude = latitude;
    }

    public void setLongitude(double longitude){
        this.longitude = longitude;
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

    public float maxTemp(){
        if(readings.size()>0){
            Reading reading = readings.get(0);
            for(int i= 0; i < getReadings().size(); i++) {
                if(readings.get(i).getTemperature() > reading.getTemperature()){
                    reading = readings.get(i);
                }
            }
            return reading.getTemperature();
        }
        else return 0;
    }

    public float minTemp(){
        if(readings.size()>0){
            Reading reading = readings.get(0);
            for(int i= 0; i < getReadings().size(); i++) {
                if(readings.get(i).getTemperature() < reading.getTemperature()){
                    reading = readings.get(i);
                }
            }
            return reading.getTemperature();
        }
        else return 0;
    }






}
