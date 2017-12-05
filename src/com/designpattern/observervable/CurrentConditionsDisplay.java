package com.designpattern.observervable;

import java.util.Observable;
import java.util.Observer;

import com.designpattern.observer.DisplayElement;

public class CurrentConditionsDisplay implements Observer,DisplayElement{

	Observable observable;
	private float temperature;
	private float humidity;
	
	
	public CurrentConditionsDisplay(Observable observable) {
		this.observable = observable;
		observable.addObserver(this);
	}

	

	public void update(Observable o, Object arg) {
		if(o instanceof WeatherData){
			WeatherData weatherData = (WeatherData)o;
			this.humidity = weatherData.getHumidity();
			this.temperature = weatherData.getTemperature();
			display();
		}
	}
	
	public void display() {
		System.out.println("Current conditions: " + temperature 
			+ "F degrees and " + humidity + "% humidity");
	}

}
