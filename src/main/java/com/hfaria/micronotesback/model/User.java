package com.hfaria.micronotesback.model;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Entity
@Table(name="USERS")
public class User {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;
	@NotBlank
	@NotNull
	private String firstName;
	@NotBlank
	@NotNull
	private String lastName;
	@NotBlank
	@NotNull
	@Column(unique=true)
	private String email;
	@NotBlank
	@NotNull
	private String encriptedPassword;
	@OneToMany(cascade = CascadeType.ALL,orphanRemoval = true)
	@JoinColumn(name="user_id")
	private List<Note> notes = new ArrayList<Note>();
	
	/**
	 * @return the firstName
	 */
	public String getFirstName() {
		return firstName;
	}
	/**
	 * @param firstName the firstName to set
	 */
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	/**
	 * @return the lastName
	 */
	public String getLastName() {
		return lastName;
	}
	/**
	 * @param lastName the lastName to set
	 */
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	/**
	 * @return the email
	 */
	public String getEmail() {
		return email;
	}
	/**
	 * @param email the email to set
	 */
	public void setEmail(String email) {
		this.email = email;
	}
	/**
	 * @param encriptedPassword the encriptedPassword to set
	 */
	public void setEncriptedPassword(String encriptedPassword) {
		this.encriptedPassword = encriptedPassword;
	}
	
	public String getPassword() {
		return encriptedPassword;
	}

	public String getFullName() {
		return new StringBuilder().append(firstName).append(" ").append(lastName).toString();
	}
	
}
