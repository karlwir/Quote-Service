package se.kawi.quoteservice.resource;

import javax.ws.rs.DefaultValue;
import javax.ws.rs.QueryParam;

public class PagingQueryBean {

	@QueryParam("size") @DefaultValue("25") private int size;
	@QueryParam("page") @DefaultValue("0") private int page;
	@QueryParam("sort") @DefaultValue("asc") private String sort;
	
	public int getSize() {
		return size;
	}
	public int getPage() {
		return page;
	}
	public String getSort() {
		return sort;
	}
	
}
