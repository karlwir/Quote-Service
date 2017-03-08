package se.kawi.quoteservice.resource;

import javax.ws.rs.DefaultValue;
import javax.ws.rs.QueryParam;

public class QuoteQueryBean extends PagingQueryBean {
	@QueryParam("content") @DefaultValue("") private String content;
	
	public String getContent() {
		return content;
	}
}
