package com.blazdemsar.domain;

import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.springframework.format.annotation.DateTimeFormat;

@Entity
@Table(name="account")
public class Account {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(nullable=false)
	private Long accountId;
	
	@ManyToOne
	@JoinColumn (name="branchId")
	private Branch accountBranch;
	
	
	@Enumerated(EnumType.STRING)
	private AccountType accountType;
	
	private String accountHolder;
	
	@DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
	private LocalDate accountDateOpened;
	
	private double accountCurrentBalance;
	
	@ManyToOne
	@JoinColumn (name="customerId")
	private Customer accountCustomer;
	
	public Account() {
		
	}

	public Long getAccountId() {
		return accountId;
	}

	public void setAccountId(Long accountId) {
		this.accountId = accountId;
	}

	public Branch getAccountBranch() {
		return accountBranch;
	}

	public void setAccountBranch(Branch accountBranch) {
		this.accountBranch = accountBranch;
	}

	public AccountType getAccountType() {
		return accountType;
	}

	public void setAccountType(AccountType accountType) {
		this.accountType = accountType;
	}

	public String getAccountHolder() {
		return accountHolder;
	}

	public void setAccountHolder(String accountHolder) {
		this.accountHolder = accountHolder;
	}

	public LocalDate getAccountDateOpened() {
		return accountDateOpened;
	}

	public void setAccountDateOpened(LocalDate accountDateOpened) {
		this.accountDateOpened = accountDateOpened;
	}

	public double getAccountCurrentBalance() {
		return accountCurrentBalance;
	}

	public void setAccountCurrentBalance(double accountCurrentBalance) {
		this.accountCurrentBalance = accountCurrentBalance;
	}

	public Customer getAccountCustomer() {
		return accountCustomer;
	}

	public void setAccountCustomer(Customer accountCustomer) {
		this.accountCustomer = accountCustomer;
	}
	
	
	
}
