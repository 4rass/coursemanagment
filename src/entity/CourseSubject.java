package entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import org.apache.openjpa.persistence.jdbc.Unique;

@Entity
public class CourseSubject {


	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	@Unique
	private String subject;
	
	
	public CourseSubject(){
	}

	public CourseSubject(String subject){
		this.subject=subject;
	}

	
	public CourseSubject(int id,String subject){
		this.id=id;
		this.subject=subject;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getSubject() {
		return subject;
	}

	public void setSubject(String subject) {
		this.subject = subject;
	}

}
