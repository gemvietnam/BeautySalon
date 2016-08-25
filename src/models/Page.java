package models;

import java.sql.Timestamp;

public class Page {
	private int id;
	private Timestamp created;
	private String slug;
	private String title;
	private String content;
	private int isPublished;
	private String template;
	private String heading;
	private String subheading;
	
	
	public String getTemplate() {
		return template;
	}
	public void setTemplate(String template) {
		this.template = template;
	}
	public String getHeading() {
		return heading;
	}
	public void setHeading(String heading) {
		this.heading = heading;
	}
	public String getSubheading() {
		return subheading;
	}
	public void setSubheading(String subheading) {
		this.subheading = subheading;
	}
	public Timestamp getCreated() {
		return created;
	}
	public void setCreated(Timestamp created) {
		this.created = created;
	}
	public int getIsPublished() {
		return isPublished;
	}
	public void setIsPublished(int isPublished) {
		this.isPublished = isPublished;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getSlug() {
		return slug;
	}
	public void setSlug(String slug) {
		this.slug = slug;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	
	public boolean isPublished() {
		if (isPublished == 1) {
			return true;
		}
		else {
			return false;
		}
	}
	
}
