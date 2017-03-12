package se.kawi.quoteservice.resource;

import javax.ws.rs.DefaultValue;
import javax.ws.rs.QueryParam;

class AuthorQueryBean extends PagingQueryBean {
	
	@QueryParam("firstname") @DefaultValue("") private String firstname;
	@QueryParam("lastname") @DefaultValue("") private String lastname;
	@QueryParam("namequery") @DefaultValue("") private String nameQuery;

	String getFirstname() {
		return firstname;
	}
	
	String getLastname() {
		return lastname;
	}
	
	public String getNameQuery() {
		return nameQuery;
	}

}
