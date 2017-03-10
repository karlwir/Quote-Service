package se.kawi.quoteservice.model;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.xml.bind.annotation.*;

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
	
	@OneToMany(mappedBy = "author")
	private List<Quote> quotes;
	
	protected Author(){};
	
	public Author(String firstname, String lastname) {
		this.firstname = firstname;
		this.lastname = lastname;
	}
	
	public String getFirstname() {
		return firstname;
	}
	
	public String getLastname() {
		return lastname;
	}
	
	public void setFirstname(String firstname) {
		this.firstname = firstname;
	}
	
	public void setLastname(String lastname) {
		this.lastname = lastname;
	}
}
