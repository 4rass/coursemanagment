package entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

	@Entity
	public class CourseMembers {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;

	@ManyToOne
	@JoinColumn(name="student")
	private Students student ;

	@ManyToOne
	@JoinColumn(name="course")
	private Courses  course ;

	
	public CourseMembers(){
		
	}
	
	public CourseMembers(Students students, Courses courses) {
		this.student=students;
		this.course=courses;
	}

	public CourseMembers(int id,Students students, Courses courses) {
		this.id=id;
		this.student=students;
		this.course=courses;
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
}