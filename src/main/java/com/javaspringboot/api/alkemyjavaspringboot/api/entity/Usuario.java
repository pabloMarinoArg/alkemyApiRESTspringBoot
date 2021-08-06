package com.javaspringboot.api.alkemyjavaspringboot.api.entity;

import java.io.Serializable;
import java.util.List;

import javax.persistence.*;

@Entity
public class Usuario implements Serializable {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	@Column(unique = true)
	private String username;
	private String password;
	private Boolean enabled;
	@ManyToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	@JoinTable(name="user_roles",
	    			joinColumns = @JoinColumn(name="user_id"),
	    			inverseJoinColumns = @JoinColumn(name="role_id"),
	    			uniqueConstraints = {@UniqueConstraint(columnNames= {"user_id", "role_id"})})
	private List<Rol> rol;

	public Usuario() {

	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public Boolean getEnabled() {
		return enabled;
	}

	public void setEnabled(Boolean enabled) {
		this.enabled = enabled;
	}

	public List<Rol> getRol() {
		return rol;
	}

	public void setRol(List<Rol> rol) {
		this.rol = rol;
	}

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

}
