package entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import org.apache.openjpa.persistence.jdbc.Unique;

@Entity
public class Courses {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	@ManyToOne
	@JoinColumn(name="lecturer")
	private Lecturers lecturer;

	private String startDate;

	private String endDate;

	private String description;
	@Unique
	private String courseName;
	
	@ManyToOne
	@JoinColumn(name="courseSubject")
	private CourseSubject  courseSubject;
	@ManyToOne
	@JoinColumn(name="details")
	private CourseDetails details;
	
	private boolean isActive;
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public Lecturers getLecturer() {
		return lecturer;
	}
	public void setLecturer(Lecturers lecturer) {
		this.lecturer = lecturer;
	}
	public String getStartDate() {
		return startDate;
	}
	public void setStartDate(String startDate) {
		this.startDate = startDate;
	}
	public String getEndDate() {
		return endDate;
	}
	public void setEndDate(String endDate) {
		this.endDate = endDate;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public String getCourseName() {
		return courseName;
	}
	public void setCourseName(String courseName) {
		this.courseName = courseName;
	}
	public CourseSubject getCourseSubject() {
		return courseSubject;
	}
	public void setCourseSubject(CourseSubject courseSubject) {
		this.courseSubject = courseSubject;
	}
	public CourseDetails getDetails() {
		return details;
	}
	public void setDetails(CourseDetails details) {
		this.details = details;
	}
	public boolean isActive() {
		return isActive;
	}
	public void setActive(boolean isActive) {
		this.isActive = isActive;
	}
}