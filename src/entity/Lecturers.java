package entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import org.apache.openjpa.persistence.jdbc.Unique;

@Entity
public class Lecturers {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	private String firstName;
	private String lastName;
	@Unique
	private int identityCard ;
	@Unique
	private String email;
	@Unique
	private String phone;
	@Unique
	@ManyToOne
	@JoinColumn(name = "user")
	private Users user;
	
	
	public Lecturers(){
		
	}
	
	public Lecturers( int id,String firstName,String lastName,int identityCard,String email, String phone){
		this.id=id;
		this.firstName=firstName;
		this.lastName=lastName;
		this.identityCard=identityCard;
		this.email=email;
		this.phone=phone;
	}
	
	
	public Lecturers(String firstName,String lastName,int identityCard,String email, String phone,Users user){
		this.firstName=firstName;
		this.lastName=lastName;
		this.identityCard=identityCard;
		this.email=email;
		this.phone=phone;
		this.user=user;
	}
	

	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getFirstName() {
		return firstName;
	}
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	public String getLastName() {
		return lastName;
	}
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	public int getIdentityCard() {
		return identityCard;
	}
	public void setIdentityCard(int identityCard) {
		this.identityCard = identityCard;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public Users getUser() {
		return user;
	}
	public void setUser(Users user) {
		this.user = user;
	}
}