package com.gmail.gm.jcant;

import java.io.Serializable;
import java.util.Date;

public class Human implements Cloneable, Serializable{

	private static final long serialVersionUID = 1L;
	
	private String name;
	private String surname;
	private Date birthday;
	private boolean male;
	private double weight;
	private double height;

	public Human() {
		super();
		// birthday = new JDate();
	}

	public Human(String name, String surname, Date birthday, boolean male, double weight, double height) {
		super();
		this.name = name;
		this.surname = surname;
		this.birthday = birthday;
		this.male = male;
		this.weight = weight;
		this.height = height;
	}

	public int getAge() {
		if (birthday != null) {
			return JDate.getDifferenceYears(birthday, new Date());
		} else {
			return -1;
		}
	}

	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append(name + " " + surname + System.lineSeparator());
		sb.append(((male) ? ("man") : ("woman")) + System.lineSeparator());
		sb.append("Age: " + getAge() + System.lineSeparator());
		sb.append("height=" + height + System.lineSeparator());
		sb.append("weight=" + weight + System.lineSeparator());
		sb.append("birthday: " + JDate.getDate(birthday) + System.lineSeparator());
		sb.append("------------------");
		return sb.toString();
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getSurname() {
		return surname;
	}

	public void setSurname(String surname) {
		this.surname = surname;
	}

	public Date getBirthday() {
		return birthday;
	}

	public void setBirthday(Date date) {
		this.birthday = date;
	}

	public boolean isMale() {
		return male;
	}

	public void setMale(boolean male) {
		this.male = male;
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

//	public void setDateFormat(String format) {
//		this.birthday.setFormat(format);
//	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((birthday == null) ? 0 : birthday.hashCode());
		long temp;
		temp = Double.doubleToLongBits(height);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		result = prime * result + (male ? 1231 : 1237);
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		result = prime * result + ((surname == null) ? 0 : surname.hashCode());
		temp = Double.doubleToLongBits(weight);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}
		if (obj == null) {
			return false;
		}
		if (getClass() != obj.getClass()) {
			return false;
		}

		Human other = (Human) obj;

		if ((birthday == null) || (other.birthday == null)) {
			if ((birthday != null) || (other.birthday != null)) {
				return false;
			}
		} else if (!birthday.equals(other.birthday)) {
			return false;
		}

		if (Double.doubleToLongBits(height) != Double.doubleToLongBits(other.height)) {
			return false;
		}

		if (male != other.male) {
			return false;
		}

		if (name == null) {
			if (other.name != null) {
				return false;
			}
		} else if (!name.equals(other.name))
			return false;

		if (surname == null) {
			if (other.surname != null) {
				return false;
			}
		} else if (!surname.equals(other.surname)) {
			return false;
		}

		if (Double.doubleToLongBits(weight) != Double.doubleToLongBits(other.weight)) {
			return false;
		}

		return true;
	}

	@Override
	public Human clone() {
		Human result = new Human();
		if (name != null) {
			result.name = new String(name);
		}
		if (surname != null) {
			result.surname = new String(surname);
		}
		if (birthday != null) {
			result.birthday = new Date(birthday.getTime());
		}
		result.setMale(male);
		result.setWeight(weight);
		result.setHeight(height);
		return result;
	}

}
