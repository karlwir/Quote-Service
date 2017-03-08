package se.kawi.quoteservice.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.xml.bind.annotation.XmlRootElement;

import org.hibernate.validator.constraints.NotEmpty;

@XmlRootElement
@Entity
public final class Quote extends AbstractEntity {

	@Column
	@NotEmpty
	private String content;
	
	protected Quote(){}

	public Quote(String content) {
		this.content = content;
	}

	public String getContent() {
		return content;
	}
	
	public void setContent(String content) {
		this.content = content;
	}
}
