package com.kethu.myapplication;

import java.util.List;

public class ListItem{
	private int dt;
	private Temp temp;
	private int deg;
	private List<WeatherItem> weather;
	private double humidity;
	private double pressure;
	private int clouds;
	private double speed;

	public void setDt(int dt){
		this.dt = dt;
	}

	public int getDt(){
		return dt;
	}

	public void setTemp(Temp temp){
		this.temp = temp;
	}

	public Temp getTemp(){
		return temp;
	}

	public void setDeg(int deg){
		this.deg = deg;
	}

	public int getDeg(){
		return deg;
	}

	public void setWeather(List<WeatherItem> weather){
		this.weather = weather;
	}

	public List<WeatherItem> getWeather(){
		return weather;
	}

	public void setHumidity(double humidity){
		this.humidity = humidity;
	}

	public double getHumidity(){
		return humidity;
	}

	public void setPressure(double pressure){
		this.pressure = pressure;
	}

	public double getPressure(){
		return pressure;
	}

	public void setClouds(int clouds){
		this.clouds = clouds;
	}

	public int getClouds(){
		return clouds;
	}

	public void setSpeed(double speed){
		this.speed = speed;
	}

	public double getSpeed(){
		return speed;
	}

	@Override
 	public String toString(){
		return 
			"ListItem{" + 
			"dt = '" + dt + '\'' + 
			",temp = '" + temp + '\'' + 
			",deg = '" + deg + '\'' + 
			",weather = '" + weather + '\'' + 
			",humidity = '" + humidity + '\'' + 
			",pressure = '" + pressure + '\'' + 
			",clouds = '" + clouds + '\'' + 
			",speed = '" + speed + '\'' + 
			"}";
		}
}