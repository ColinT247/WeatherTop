package controllers;

import models.Station;
import play.Logger;
import play.mvc.Controller;
import models.Member;

import java.util.List;

public class Accounts extends Controller {
  public static void signup() {
    render("signup.html");
  }

  public static void update() {
    if (session.contains("logged_in_Memberid")) {
      Member member = Accounts.getLoggedInMember();
      render("update.html", member);
    } else {
      render("login.html");
    }
  }

  public static void login() {
    render("login.html");
  }

  public static void register(String firstname, String lastname, String email, String password) {
    Logger.info("Registering new user " + email);
    Member member = new Member(firstname, lastname, email, password);
    member.save();
    redirect("/login");
  }

  public static void authenticate(String email, String password) {
    Logger.info("Attempting to authenticate with " + email + ":" + password);

    Member member = Member.findByEmail(email);
    if ((member != null) && (member.checkPassword(password) == true)) {
      Logger.info("Authentication successful");
      session.put("logged_in_Memberid", member.id);
      redirect("/dashboard");
    } else {
      Logger.info("Authentication failed");
      redirect("/login");
    }
  }

  public static void logout() {
    session.clear();
    redirect("/");
  }

  public static Member getLoggedInMember() {
    Member member = null;
    if (session.contains("logged_in_Memberid")) {
      String memberId = session.get("logged_in_Memberid");
      member = Member.findById(Long.parseLong(memberId));
    } else {
      login();
    }
    return member;
  }

  public static void updateDetails(String newFirstname, String newLastname, String newEmail, String oldPassword, String newPassword, String confirmNewPassword) {
    Member member = getLoggedInMember();
    String details = "";
    if (member.checkPassword(oldPassword)) {
      //if some details were updated. ie: the updateMember method returns true
      if (member.updateMember(newFirstname, newLastname, newEmail, newPassword, confirmNewPassword)) {
        Logger.info("Account details updated: Firstname " + newFirstname + "  Lastname " + newLastname + " Email " + newEmail + " Password " + newPassword);
        member.save();
        render("success.html", member);
      }
      //if update member method returns false AND the new passwords don't match
      else if ((!member.updateMember(newFirstname, newLastname, newEmail, newPassword, confirmNewPassword)) && (!newPassword.equals(confirmNewPassword))) {
        Logger.info("New passwords did match. No details updated");
        member.save();
        details = "New passwords did not match. No details updated";
        noSuccess(details);
      }
    }
    //password not entered or incorrect - no details will be changed
    else {
      Logger.info("Password was incorrect - no details changed");
      member.save();
      session.clear();
      details = "Incorrect password. No details were updated. You must login again";
      noSuccess(details);
    }
  }

  public static void noSuccess(String details) {
    render("nosuccess.html", details);
  }
}
