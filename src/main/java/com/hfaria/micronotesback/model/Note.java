package com.hfaria.micronotesback.model;

import java.util.Date;
import javax.persistence.*;

@Entity
@Table(name="NOTES")
public class Note {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;
	private String title;
	@Lob
	private String text;
	private Date dateCreated;
	private Date dateEdited;

	/**
	 * @return the id
	 */
	public Long getId() {
		return id;
	}

	/**
	 * @param id the id to set
	 */
	public void setId(Long id) {
		this.id = id;
	}

	/**
	 * @return the title
	 */
	public String getTitle() {
		return title;
	}

	/**
	 * @param title the title to set
	 */
	public void setTitle(String title) {
		this.title = title;
	}

	/**
	 * @return the dateCreated
	 */
	public Date getDateCreated() {
		return dateCreated;
	}

	/**
	 * @param dateCreated the dateCreated to set
	 */
	public void setDateCreated(Date dateCreated) {
		this.dateCreated = dateCreated;
	}

	/**
	 * @return the dateEdited
	 */
	public Date getDateEdited() {
		return dateEdited;
	}

	/**
	 * @return the text
	 */
	public String getText() {
		return text;
	}

	/**
	 * @param text the text to set
	 */
	public void setText(String text) {
		this.text = text;
	}

	/**
	 * @param dateEdited the dateEdited to set
	 */
	public void setDateEdited(Date dateEdited) {
		this.dateEdited = dateEdited;
	}
	
}
