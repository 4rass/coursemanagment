package entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import org.apache.openjpa.persistence.jdbc.Unique;

@Entity
public class Days {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	@Unique
	@ManyToOne
	@JoinColumn(name="course")
	private Courses course;
	@Unique
	private String date;
	@Unique
	private String agenda;

	private String startTime;

	private String endTime ;

	
	public Days(){
		
	}
	
	
	public Days( String date, String agenda, String startTime, String endTime,Courses courses) {
		this.date=date;
		this.agenda=agenda;
		this.startTime=startTime;
		this.endTime=endTime;
		this.course=courses;
		
	}

	public Days(int id, String date, String agenda, String startTime, String endTime, Courses course) {
		this.id=id;
		this.date=date;
		this.agenda=agenda;
		this.startTime=startTime;
		this.endTime=endTime;
		this.course=course;
		
	}


	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Courses getCourse() {
		return course;
	}

	public void setCourse(Courses course) {
		this.course = course;
	}

	public String getDay() {
		return date;
	}

	public void setDay(String date) {
		this.date = date;
	}

	public String getAgenda() {
		return agenda;
	}

	public void setAgenda(String agenda) {
		this.agenda = agenda;
	}

	public String getStartTime() {
		return startTime;
	}

	public void setStartTime(String startTime) {
		this.startTime = startTime;
	}

	public String getEndTime() {
		return endTime;
	}

	public void setEndTime(String endTime) {
		this.endTime = endTime;
	}
	
}