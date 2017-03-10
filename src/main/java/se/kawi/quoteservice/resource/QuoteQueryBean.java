package se.kawi.quoteservice.resource;

import javax.ws.rs.DefaultValue;
import javax.ws.rs.QueryParam;

public class QuoteQueryBean extends PagingQueryBean {
	@QueryParam("content") @DefaultValue("") private String content;
	@QueryParam("author") @DefaultValue("-1") private String authorId;
	
	public String getContent() {
		return content;
	}
	
	public String getAuthorId() {
		return authorId;
	}
}
