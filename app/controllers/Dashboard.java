package controllers;

import java.util.List;

import models.Reading;
import models.Station;
import play.Logger;
import play.mvc.Controller;
import models.Member;

public class Dashboard extends Controller {
  public static void index() {
    Logger.info("Rendering Dasboard");
    Member member = Accounts.getLoggedInMember();
    List<Station> stations = member.stations;
    render("dashboard.html", stations, member);
  }

  public static void deleteStation(Long id) {
    Member member = Accounts.getLoggedInMember();
    Station station = Station.findById(id);
    member.stations.remove(station);
    member.save();
    station.delete();
    Logger.info("Removing Station: " + station.name);
    redirect("/dashboard");
  }

  public static void addStation(String name, double latitude, double longitude) {
    Member member = Accounts.getLoggedInMember();
    if(!name.isEmpty()) {
      Station station = new Station(name, latitude, longitude);
      Logger.info("Adding new Station called: " + name);
      member.stations.add(station);
      member.save();
      redirect("/dashboard");
    }
    else{
      Logger.info("Didn't add station. No name was entered");
      redirect("/dashboard");
    }
  }

}

