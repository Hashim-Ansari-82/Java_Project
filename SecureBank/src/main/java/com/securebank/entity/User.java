package com.securebank.entity;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import com.securebank.enums.RoleName;
import com.securebank.enums.UsersStatus;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.ElementCollection;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToMany;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class User {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;

	@Column(nullable = false)
	private String username;

	@Column(nullable = false)
	private String address;
	
	@Column(nullable = false, unique = true)
	private String email;

	@Column(nullable = false)
	private String password;

	@Column(nullable = false, unique = true)
	private String mobile;

	@Column(name = "is_enable")
	private boolean isEnabled;

	@Enumerated(EnumType.STRING)
	@Column(nullable = false)
	private UsersStatus status;

	@ElementCollection(fetch = FetchType.EAGER)
	@Enumerated(EnumType.STRING)
	@Column(nullable = false)
	private Set<RoleName> roles;

	@CreationTimestamp
	@Column(nullable = false, updatable = false)
	private LocalDate createdAt;

	@UpdateTimestamp
	private LocalDate updatedAt;

	// One User -> Many Accounts
	@Builder.Default
	@OneToMany(mappedBy = "user", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	private List<Account> accounts = new ArrayList<>();

	// One User -> Many Refresh Tokens
	@Builder.Default
	@OneToMany(mappedBy = "user", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	private List<RefreshToken> refreshTokens = new ArrayList<>();

	// One User -> Many Notifications
	@Builder.Default
	@OneToMany(mappedBy = "user", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	private List<Notification> notification = new ArrayList<>();
	
	@Builder.Default
	@OneToMany(cascade = CascadeType.ALL,mappedBy = "user")
	private List<KYC> kycs = new ArrayList<>();
}