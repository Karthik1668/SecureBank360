package com.karthik.securebank360.entity;

import java.time.LocalDateTime;

import jakarta.persistence.*;
@Entity
@Table(name="users")
public class User {

	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Id
	private Long id;
	@Column(nullable=false)
	private String fullName;
	@Column(nullable=false,unique=true)
	private String phone;
	@Column(nullable=false)
	private String password;
	@Column(nullable=false,unique=true)
	private String email;
	
	@Enumerated(EnumType.STRING)
	@Column(nullable=false)
	private Role role;
	
	
	@Column(nullable=false)
	private boolean kycVerified=false;
	
	@Column(nullable=false,updatable=false)
	private LocalDateTime createdAt =LocalDateTime.now();

	public Long getId() {
		return id;
	}

	public String getFullName() {
		return fullName;
	}

	
	
	public String getEmail() {
		return email;
	}

	public Role getRole() {
		return role;
	}

	public boolean isKycVerified() {
		return kycVerified;
	}

	public LocalDateTime getCreatedAt() {
		return createdAt;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public void setFullName(String fullName) {
		this.fullName = fullName;
	}

	

	public String getPhone() {
		return phone;
	}

	public String getPassword() {
		return password;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public void setRole(Role role) {
		this.role = role;
	}

	public void setKycVerified(boolean kycVerified) {
		this.kycVerified = kycVerified;
	}

	public void setCreatedAt(LocalDateTime createdAt) {
		this.createdAt = createdAt;
	}

	public User orElseThrow(Object object) {
		// TODO Auto-generated method stub
		return null;
	}
	
	
	
	

}
