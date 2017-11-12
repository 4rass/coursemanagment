package entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity
public class Chat {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	
	@ManyToOne
	@JoinColumn(name="student")
	private Students student;
	
	@ManyToOne
	@JoinColumn(name="course")
	private Courses course;
	
	private String date;
	
	private String massage;
	
	public Chat( Students student,Courses course,String date,String massage){
		this.student=student;
		this.course=course;
		this.date=date;
		this.massage=massage;
	}
	
	public Chat(int id,Students student,Courses course,String date,String massage){
		this.id=id;
		this.student=student;
		this.course=course;
		this.date=date;
		this.massage=massage;
	}
	
	
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	
	public Students getStudent() {
		return student;
	}

	public void setStudent(Students student) {
		this.student = student;
	}

	public Courses getCourse() {
		return course;
	}

	public void setCourse(Courses course) {
		this.course = course;
	}

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}

	public String getMassage() {
		return massage;
	}

	public void setMassage(String massage) {
		this.massage = massage;
	}

}