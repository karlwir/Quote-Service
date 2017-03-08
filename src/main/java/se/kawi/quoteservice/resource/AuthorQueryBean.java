package se.kawi.quoteservice.resource;

import javax.ws.rs.DefaultValue;
import javax.ws.rs.QueryParam;

public class AuthorQueryBean extends PagingQueryBean {
	
	@QueryParam("firstname") @DefaultValue("") private String firstname;
	@QueryParam("lastname") @DefaultValue("") private String lastname;

	public String getFirstname() {
		return firstname;
	}
	
	public String getLastname() {
		return lastname;
	}

}
