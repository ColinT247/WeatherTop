package controllers;

import models.Station;
import models.Reading;
import play.Logger;
import play.mvc.Controller;
import java.time.Clock;
import java.time.Instant;

public class StationCtrl extends Controller
{
    public static void index(Long id)
    {
        Station station = Station.findById(id);
        Logger.info ("Playlist id = " + id);
        render("station.html", station);
    }

    public static void deleteReading (Long id, Long readingid)
    {
        Station station = Station.findById(id);
        Reading reading = Reading.findById(readingid);
        Logger.info ("Removing reading");
        station.readings.remove(reading);
        station.save();
        reading.delete();
        render("station.html", station);
    }


    public static void addReading(long id, int code, float temperature, int windSpeed, int windDirection, long pressure)
    {
        String timeStamp = Station.timeStamp();
        Reading reading = new Reading(timeStamp, code, temperature, windSpeed, windDirection, pressure);
        Station station = Station.findById(id);
        station.readings.add(reading);
        station.save();
        redirect("/stations/"+id);

    }


}

