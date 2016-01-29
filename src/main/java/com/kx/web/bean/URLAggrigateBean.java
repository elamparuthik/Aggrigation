/**
 * 
 */
/**
 * @author Prathap
 *
 */
package com.kx.web.bean;

public class URLAggrigateBean {
	
	private int aggrigateId;
	
	private String title;
	
	private String description;
	
	private String url;
	
	
	public void setAggrigateId(int aggrigateId) {
		this.aggrigateId = aggrigateId;
	}

	public int getAggrigateId() {
		return aggrigateId;
	}
	
	public void setTitle(String title) {
		this.title = title;
	}

	public String getTitle() {
		return title;
	}

	public void setDescription(String description) {
		this.description = description;
	}
	
	public String getDescription() {
		return description;
	}

	public void setUrl(String url) {
		this.url = url;
	}
	
	public String getUrl() {
		return url;
	}
	
}