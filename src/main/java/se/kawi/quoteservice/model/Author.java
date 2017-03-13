package se.kawi.quoteservice.model;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.xml.bind.annotation.XmlRootElement;

import org.hibernate.validator.constraints.NotEmpty;

@XmlRootElement
@Entity
public class Author extends AbstractEntity {

	@Column
	@NotEmpty
	private String firstname;
	
	@Column
	@NotEmpty
	private String lastname;
	
	private String fullname;
	
	@OneToMany(mappedBy = "author")
	private List<Quote> quotes;
	
	protected Author(){};
	
	public Author(String firstname, String lastname) {
		this.firstname = firstname;
		this.lastname = lastname;
		this.fullname = firstname + " " + lastname;
	}
	
	public String getFirstname() {
		return firstname;
	}
	
	public String getLastname() {
		return lastname;
	}
	
	public String getFullname() {
		return fullname;
	}
	
	public void setFirstname(String firstname) {
		this.firstname = firstname;
		updateFullname();
	}
	
	public void setLastname(String lastname) {
		this.lastname = lastname;
		updateFullname();
	}
	
	private void updateFullname() {
		this.fullname = firstname + " " + lastname;
	}
}
