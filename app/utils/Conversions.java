package utils;

import java.time.Instant;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.time.format.FormatStyle;
import java.util.Locale;

public class Conversions {

  public static float tempInF(float temp) {
    return temp * 9 / 5 + 32;
  }

  public static String weatherState(int code) {
    String weatherState = " ";
    switch (code) {
      case 100:
        weatherState = "Clear";
        break;
      case 200:
        weatherState = "Partial Clouds";
        break;
      case 300:
        weatherState = "Cloudy";
        break;
      case 400:
        weatherState = "Light Showers";
        break;
      case 500:
        weatherState = "Heavy Showers";
        break;
      case 600:
        weatherState = "Rain";
        break;
      case 700:
        weatherState = "Snow";
        break;
      case 800:
        weatherState = "Thunder";
        break;
      default:
        weatherState = "Unknown";
        break;
    }
    return weatherState;
  }

  public static String weatherStateIcon(int code) {
    String weatherStateIconCode = " ";
    switch (code) {
      case 100:
        weatherStateIconCode = "fas fa-3x fa-regular fa-sun";
        break;
      case 200:
        weatherStateIconCode = "fas fa-3x fa-regular fa-cloud-sun";
        break;
      case 300:
        weatherStateIconCode = "fas fa-3x fa-regular fa-cloud";
        break;
      case 400:
        weatherStateIconCode = "fas fa-3x fa-sharp fa-solid fa-cloud-rain";
        break;
      case 500:
        weatherStateIconCode = "fas fa-3x fa-sharp fa-solid fa-cloud-rain";
        break;
      case 600:
        weatherStateIconCode = "fas fa-3x fa-solid fa-cloud-showers-heavy";
        break;
      case 700:
        weatherStateIconCode = "fas fa-3x fa-solid fa-snowflake";
        break;
      case 800:
        weatherStateIconCode = "fas fa-3x fa-regular fa-cloud-bolt";
        break;
      default:
        weatherStateIconCode = "fas fa-3x fa-regular fa-temperature-half";
        break;
    }

    return weatherStateIconCode;
  }

  public static int beaufort(int windSpeed) {
    int beaufort = 0;
    if (windSpeed == 1) {
      beaufort = 0;
    } else if (windSpeed > 1 && windSpeed < 6) {
      beaufort = 1;
    } else if (windSpeed > 5 && windSpeed < 12) {
      beaufort = 2;
    } else if (windSpeed > 11 && windSpeed < 20) {
      beaufort = 3;
    } else if (windSpeed > 19 && windSpeed < 29) {
      beaufort = 4;
    } else if (windSpeed > 28 && windSpeed < 39) {
      beaufort = 5;
    } else if (windSpeed > 38 && windSpeed < 50) {
      beaufort = 6;
    } else if (windSpeed > 49 && windSpeed < 62) {
      beaufort = 7;
    } else if (windSpeed > 61 && windSpeed < 75) {
      beaufort = 8;
    } else if (windSpeed > 74 && windSpeed < 89) {
      beaufort = 9;
    } else if (windSpeed > 88 && windSpeed < 103) {
      beaufort = 10;
    } else if (windSpeed > 102 && windSpeed < 118) {
      beaufort = 11;
    }
    return beaufort;
  }

  public static String windDirectionCompass(int windDirection) {
    if (windDirection > 348.75 && windDirection < 361) {
      return "North";
    }
    if (windDirection > 11.24 && windDirection < 33.76) {
      return "North North  East";
    }
    if (windDirection > 33.75 && windDirection < 56.26) {
      return "North East";
    }
    if (windDirection > 56.25 && windDirection < 78.76) {
      return "East North East";
    }
    if (windDirection > 78.75 && windDirection < 101.26) {
      return "East";
    }
    if (windDirection > 101.25 && windDirection < 123.76) {
      return "East South East";
    }
    if (windDirection > 123.75 && windDirection < 146.26) {
      return "South East";
    }
    if (windDirection > 146.25 && windDirection < 168.76) {
      return "South South East";
    }
    if (windDirection > 168.75 && windDirection < 191.26) {
      return "South";
    }
    if (windDirection > 191.25 && windDirection < 213.76) {
      return "South South West";
    }
    if (windDirection > 213.75 && windDirection < 236.36) {
      return "South West";
    }
    if (windDirection > 236.35 && windDirection < 258.76) {
      return "West South West";
    }
    if (windDirection > 258.75 && windDirection < 281.26) {
      return "West";
    }
    if (windDirection > 281.25 && windDirection < 303.76) {
      return "West North West";
    }
    if (windDirection > 303.75 && windDirection < 326.26) {
      return "North West";
    }
    if (windDirection > 326.75 && windDirection < 348.76) {
      return "North North West";
    } else return " ";
  }

  //should there be an if statement in feelsLike calculation?
  //sometimes feelsLike value should be same as actual temp value, rather than altered.
  //based on temp and windspeed being below and above certain values respectively?  also requires humidity I think
  public static double feelsLike(float temperature, int windSpeed) {
    double feelsLike = 0.0;
    feelsLike = 13.12 + 0.6215 * temperature - 11.37 * (Math.pow(windSpeed, 0.16)) + 0.3965 * temperature * (Math.pow(windSpeed, 0.16));
    feelsLike = Math.round(feelsLike * 10.0) / 10.0;
    return feelsLike;
  }

  //https://java2blog.com/format-instant-to-string-java/
  public static String timeStamp() {

    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss").withLocale(Locale.UK).withZone(ZoneId.systemDefault());
    Instant instant = Instant.now();
    String instantStr = formatter.format(instant);
    return instantStr;
  }

}
