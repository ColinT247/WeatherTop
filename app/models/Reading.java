package models;

import javax.persistence.Entity;

import play.db.jpa.Model;

import utils.Conversions;
import java.time.Clock;
import java.time.Instant;

@Entity
public class Reading extends Model
{
    public String timeStamp;
    public int code;
    public float temperature;
    public int windSpeed;
    public long pressure;
    public int windDirection;

    //constructor
    public Reading(String timeStamp, int code, float temperature, int windSpeed,int windDirection, long pressure)
    {
        this.timeStamp = timeStamp;
        this.code = code;
        this.temperature = temperature;
        this.windSpeed = windSpeed;
        this.pressure = pressure;
        this.windDirection = windDirection;
    }

    //getters
    public int getCode(){
        return code;
    }
    public float getTemperature(){
        return temperature;
    }
    public int getWindSpeed(){
        return windSpeed;
    }
    public long getPressure(){
        return pressure;
    }
    public int getWindDirection() {return windDirection;}

    //setters
    public void setCode(int code){
        this.code = code;
    }
    public void setTemperature(float temperature){
        this.temperature = temperature;
    }
    public void setWindSpeed(int windSpeed){
        this.windSpeed = windSpeed;
    }
    public void setPressure(long pressure){
        this.pressure = pressure;
    }
    public void setWindDirection(int windDirection){
        this.windDirection = windDirection;
    }

    //methods

    public float getTempInF()
    {
        return utils.Conversions.tempInF(this.temperature);
    }

    public String getWeatherState()
    {
        return utils.Conversions.weatherState(this.code);
    }

    public int getBeaufort()
    {
        return utils.Conversions.beaufort(this.windSpeed);
    }

    public String getWindDirectionCompass(){return utils.Conversions.windDirectionCompass(this.windDirection); }

    public double getfeelsLike(){
        return utils.Conversions.feelsLike(this.temperature, this.windSpeed);
    }

    public String getWeatherStateIcon(){return utils.Conversions.weatherStateIcon(this.code);}

}