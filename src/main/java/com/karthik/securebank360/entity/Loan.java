package com.karthik.securebank360.entity;

import java.time.LocalDateTime;

import jakarta.persistence.*;

@Entity
@Table(name="loans")
public class Loan {
	
	
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Id
	private Long id;
	
	@ManyToOne
	@JoinColumn(name="user_id",nullable=false)
	private User user;
	
	@Column(nullable=false)
	private String purpose;
	
	
	@Column(nullable=false,updatable=false)
	private LocalDateTime appliedAt=LocalDateTime.now();
	
	@Column(nullable=false)
	private Double amount;
	
	@Enumerated(EnumType.STRING)
	@Column(nullable=false)
	private LoanStatus status=LoanStatus.PENDING;
	
	private LocalDateTime approvedAt;

	public Long getId() {
		return id;
	}

	public User getUser() {
		return user;
	}

	public String getPurpose() {
		return purpose;
	}

	public LocalDateTime getAppliedAt() {
		return appliedAt;
	}

	public Double getAmount() {
		return amount;
	}

	public LoanStatus getStatus() {
		return status;
	}

	public LocalDateTime getApprovedAt() {
		return approvedAt;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public void setPurpose(String purpose) {
		this.purpose = purpose;
	}

	public void setAppliedAt(LocalDateTime appliedAt) {
		this.appliedAt = appliedAt;
	}

	public void setAmount(Double amount) {
		this.amount = amount;
	}

	public void setStatus(LoanStatus status) {
		this.status = status;
	}

	public void setApprovedAt(LocalDateTime approvedAt) {
		this.approvedAt = approvedAt;
	}
	
	
	
	

	
}
