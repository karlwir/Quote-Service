package se.kawi.quoteservice.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotNull;
import javax.xml.bind.annotation.XmlRootElement;

import org.hibernate.validator.constraints.NotEmpty;

@XmlRootElement
@Entity
public final class Quote extends AbstractEntity {

	@Column
	@NotEmpty
	private String content;
	
	@NotNull
	@ManyToOne
	private Author author;
	
	protected Quote(){}

	public Quote(String content, Author author) {
		this.content = content;
		this.author = author;
	}

	public String getContent() {
		return content;
	}
	
	public Author getAuthor() {
		return author;
	}
	
	public void setContent(String content) {
		this.content = content;
	}
	
	public void setAuthor(Author author) {
		this.author = author;
	}
}
