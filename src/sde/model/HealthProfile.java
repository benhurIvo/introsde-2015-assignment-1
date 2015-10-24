package sde.model;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;
import util.DoubleGenerator;

@XmlRootElement(name="healthprofile")
@XmlType(propOrder = { "lastupdate","weight", "height", "bmi" })
@XmlAccessorType(XmlAccessType.FIELD)
public class HealthProfile {
	private String lastupdate;
	private double weight; // in kg
	private double height; // in m
	private double bmi;

	public HealthProfile(double weight, double height, double bmi) {
		this.weight = weight;
		this.height = height;
		this.bmi = bmi;
	}

	public HealthProfile() {
		this.weight = 85.5;
		this.height = 1.72;
	}

	public String getLastupdate() {
		return lastupdate;
	}

	public void setLastupdate(String lastupdate) {
		this.lastupdate = lastupdate;
	}
	
	public double getWeight() {
		return weight;
	}

	public void setWeight(double weight) {
		this.weight = weight;
	}

	public double getHeight() {
		return height;
	}

	public void setHeight(double height) {
		this.height = height;
	}
	
	public String toString() {
		return "Height="+height+", Weight="+weight;
	}

	//@XmlElement(name="bmi")
	public double getBMI() {
		return DoubleGenerator.round(this.weight/(Math.pow(this.height, 2)),2);
	}
	
	public void setBMI(double bmi) {
		this.bmi = bmi;
	}
}
