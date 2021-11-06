package com.listou.listou.entity;

import java.io.Serializable;
import java.util.List;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;

import com.sun.istack.NotNull;

@Entity
public class UserListou implements Serializable{

	private static final long serialVersionUID = -3337253759714306080L;
	
	@Id
    @GeneratedValue(strategy = GenerationType.AUTO)
	private long id;
	@NotNull
	@Column(unique=true)
	private String username;
	@NotNull
	private String password;
	@NotNull
	private String name;
	@NotNull
	private boolean admin;
	@ElementCollection
	private List<Long> listasId;
	
	/**
	 * @return the id
	 */
	public long getId() {
		return id;
	}
	/**
	 * @param id the id to set
	 */
	public void setId(long id) {
		this.id = id;
	}
	/**
	 * @return the username
	 */
	public String getUsername() {
		return username;
	}
	/**
	 * @param username the username to set
	 */
	public void setUsername(String username) {
		this.username = username;
	}
	/**
	 * @return the password
	 */
	public String getPassword() {
		return password;
	}
	/**
	 * @param password the password to set
	 */
	public void setPassword(String password) {
		this.password = password;
	}
	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}
	/**
	 * @param name the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}
	/**
	 * @return the admin
	 */
	public boolean isAdmin() {
		return admin;
	}
	/**
	 * @param admin the admin to set
	 */
	public void setAdmin(boolean admin) {
		this.admin = admin;
	}
	/**
	 * @return the listasId
	 */
	public List<Long> getListasId() {
		return listasId;
	}
	/**
	 * @param listasId the listasId to set
	 */
	public void setListasId(List<Long> listasId) {
		this.listasId = listasId;
	} 	
}
