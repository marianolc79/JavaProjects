package perf;

import org.apache.solr.client.solrj.beans.Field;

public class MyBean {
	@Field("id")
	private Integer id;
	@Field("name")
	private String name;
	@Field("description")
	private String decription;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDecription() {
		return decription;
	}

	public void setDecription(String decription) {
		this.decription = decription;
	}

	public MyBean() {
	}
	
	public MyBean(Integer id, String name, String decription) {
		super();
		this.id = id;
		this.name = name;
		this.decription = decription;
	}

}
