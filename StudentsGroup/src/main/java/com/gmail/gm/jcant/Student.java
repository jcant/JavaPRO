package com.gmail.gm.jcant;

import java.io.Serializable;
import java.util.Date;

public class Student extends Human implements Cloneable, Serializable {

	private static final long serialVersionUID = 2L;

	static SortBy sortArrayBy = SortBy.SURNAME;

	private String institutionName;
	private Date dateIn;
	private double averageScore;

	private Group group = null;

	public Student() {
		super();
	}

	public Student(String name, String surname, Date birthday, boolean male, double weight, double height,
			String institutionName, Date dateIn, double avarageScore) {
		super(name, surname, birthday, male, weight, height);
		this.institutionName = institutionName;
		this.dateIn = dateIn;
		this.averageScore = avarageScore;
	}

	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append(String.format("%-8.8s %-10.10s", getName(), getSurname() + ","));
		sb.append(String.format("  %-8.8s", ((isMale()) ? ("man") : ("woman") + ",")));
		sb.append(String.format("Age: %-6.6s", getAge() + ","));
		sb.append(String.format("Inst: %-14.14s", institutionName + ","));
		sb.append(String.format("AvgScore: %2.2f \t", averageScore));
		sb.append("Course: " + getCourse() + " ");
		return sb.toString();
	}

	public String getInstitutionName() {
		return institutionName;
	}

	public void setInstitutionName(String institutionName) {
		this.institutionName = institutionName;
	}

	public int getCourse() {
		if (dateIn != null) {
			return 1 + JDate.getDifferenceYears(dateIn, new Date());
		} else {
			return -1;
		}
	}

	public Date getDateIn() {
		return dateIn;
	}

	public void setDateIn(Date dateIn) {
		this.dateIn = dateIn;
	}

	public double getAvarageScore() {
		return averageScore;
	}

	public void setAvarageScore(double avarageScore) {
		this.averageScore = avarageScore;
	}

	public Group getGroup() {
		return group;
	}

	public void setGroup(Group group) {
		this.group = group;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		long temp;
		temp = Double.doubleToLongBits(averageScore);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		result = prime * result + ((dateIn == null) ? 0 : dateIn.hashCode());
		result = prime * result + ((institutionName == null) ? 0 : institutionName.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (!super.equals(obj))
			return false;
		if (getClass() != obj.getClass())
			return false;
		Student other = (Student) obj;
		if (Double.doubleToLongBits(averageScore) != Double.doubleToLongBits(other.averageScore))
			return false;
		if (dateIn == null) {
			if (other.dateIn != null)
				return false;
		} else if (!dateIn.equals(other.dateIn))
			return false;
		if (institutionName == null) {
			if (other.institutionName != null)
				return false;
		} else if (!institutionName.equals(other.institutionName))
			return false;
		return true;
	}

	@Override
	public Student clone() {
		Student result = new Student();

		// from Human:
		if (getName() != null) {
			result.setName(new String(getName()));
		}
		if (getSurname() != null) {
			result.setSurname(new String(getSurname()));
		}
		if (getBirthday() != null) {
			result.setBirthday(new Date(getBirthday().getTime()));
		}
		result.setMale(isMale());
		result.setWeight(getWeight());
		result.setHeight(getHeight());
		// end from Human

		if (institutionName != null) {
			result.setInstitutionName(new String(institutionName));
		}

		if (dateIn != null) {
			result.dateIn = new Date(dateIn.getTime());
		}

		result.setAvarageScore(averageScore);

		return result;
	}

	// -- sorting --

	public static void setSortArrayBy(SortBy sort) {
		sortArrayBy = sort;
	}

	public enum SortBy {
		NAME(true) {
			@Override
			public int compare(Student student1, Student student2) {
				if (isAnyNull(student1, student2) != NO_NULL) {
					return getDirection() * isAnyNull(student1, student2);
				}
				return getDirection() * student1.getName().compareTo(student2.getName());
			}
		},
		SURNAME(true) {
			@Override
			public int compare(Student student1, Student student2) {
				if (isAnyNull(student1, student2) != NO_NULL) {
					return getDirection() * isAnyNull(student1, student2);
				}
				return getDirection() * student1.getSurname().compareTo(student2.getSurname());
			}
		},
		AGE(true) {
			@Override
			public int compare(Student student1, Student student2) {
				if (isAnyNull(student1, student2) != NO_NULL) {
					return getDirection() * isAnyNull(student1, student2);
				}
				return getDirection() * (student1.getAge() - student2.getAge());
			}
		},
		WEIGHT(true) {
			@Override
			public int compare(Student student1, Student student2) {
				if (isAnyNull(student1, student2) != NO_NULL) {
					return getDirection() * isAnyNull(student1, student2);
				}
				return getDirection() * ((int) ((student1.getWeight() - student2.getWeight())
						/ Math.abs(student1.getWeight() - student2.getWeight())));
			}
		},
		HEIGHT(true) {
			@Override
			public int compare(Student student1, Student student2) {
				if (isAnyNull(student1, student2) != NO_NULL) {
					return getDirection() * isAnyNull(student1, student2);
				}
				return getDirection() * ((int) ((student1.getHeight() - student2.getHeight())
						/ Math.abs(student1.getHeight() - student2.getHeight())));
			}
		},
		INSTITUTE(true) {
			@Override
			public int compare(Student student1, Student student2) {
				if (isAnyNull(student1, student2) != NO_NULL) {
					return getDirection() * isAnyNull(student1, student2);
				}
				return getDirection() * student1.getInstitutionName().compareTo(student2.getInstitutionName());
			}
		},
		COURSE(true) {
			@Override
			public int compare(Student student1, Student student2) {
				if (isAnyNull(student1, student2) != NO_NULL) {
					return getDirection() * isAnyNull(student1, student2);
				}
				return getDirection() * (student1.getCourse() - student2.getCourse());
			}
		},
		AVGSCORE(true) {
			@Override
			public int compare(Student student1, Student student2) {
				if (isAnyNull(student1, student2) != NO_NULL) {
					return getDirection() * isAnyNull(student1, student2);
				}
				return getDirection() * ((int) ((student1.getAvarageScore() - student2.getAvarageScore())
						/ Math.abs(student1.getAvarageScore() - student2.getAvarageScore())));
			}
		};

		public final int NO_NULL = 17;
		private int direction;

		private SortBy(boolean ascending) {
			this.direction = (ascending) ? (1) : (-1);
		}

		public boolean isAscending() {
			return (direction > 0) ? (true) : (false);
		}

		public SortBy setAscending(boolean ascending) {
			this.direction = (ascending) ? (1) : (-1);
			return this;
		}

		public int getDirection() {
			return direction;
		}

		public void setDirection(int direction) {
			this.direction = direction;
		}

		public int isAnyNull(Student st1, Student st2) {
			if (st1 == null && st2 == null) {
				return 0;
			}
			if (st1 == null && st2 != null) {
				return 1;
			}
			if (st1 != null && st2 == null) {
				return -1;
			}
			return NO_NULL;
		}

		public abstract int compare(Student student1, Student student2);
	}

}
