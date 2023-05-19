package controllers;
import play.*;
import play.mvc.*;
import java.util.*;
import models.*;

public class Aboutout extends Controller {
  public static void index() {
    Logger.info("Rendering aboutout");
    render("aboutout.html");
  }
}
