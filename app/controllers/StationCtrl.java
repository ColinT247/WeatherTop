package controllers;

import models.Member;
import models.Station;
import models.Reading;
import play.Logger;
import play.mvc.Controller;

import java.time.Clock;
import java.time.Instant;

public class StationCtrl extends Controller {
  public static void index(Long id) {
    //if condiiton only shows stations if there is someone logged in! without this someone could bypass login.
    if (session.contains("logged_in_Memberid")) {
      Station station = Station.findById(id);
      Logger.info("Playlist id = " + id);
      render("station.html", station);
    } else {
      render("login.html");
    }
  }

  public static void deleteReading(Long id, Long readingid) {
    Station station = Station.findById(id);
    Reading reading = Reading.findById(readingid);
    Logger.info("Removing reading");
    station.readings.remove(reading);
    station.save();
    reading.delete();
    render("station.html", station);
  }

  public static void addReading(long id, int code, float temperature, int windSpeed, int windDirection, long pressure) {
    String timeStamp = utils.Conversions.timeStamp();
    if (code != 0) {
      Reading reading = new Reading(timeStamp, code, temperature, windSpeed, windDirection, pressure);
      Station station = Station.findById(id);
      station.readings.add(reading);
      station.save();
      Logger.info("Adding reading");
      redirect("/stations/" + id);
    } else {
      Logger.info("No reading added. No details");
      redirect("/stations/" + id);
    }
  }
}

