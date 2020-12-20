package com.douglas.userapi.model;

import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.Email;

import org.hibernate.validator.constraints.br.CPF;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;


@Entity
@Table(name = "user_entity")
@ApiModel(description = "User Entity")
public class User {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(nullable = false)
	@ApiModelProperty(required = true)
	private String name;
	
	private String gender;
	
	@Email(message = "E-mail format is invalid")
	private String email;
	
	@Column(nullable = false)
	private LocalDate birthDate;
	
	private String naturality;
	
	private String nationality;
	
	@Column(unique = true, nullable = false)
	@CPF(message = "CPF format invalid")
	@ApiModelProperty(required = true)
	private String cpf;
	
	@Column(nullable = false)
	private LocalDate createDate;
	
	private LocalDate updateDate;
	
	public User() {}

	public User(Long id, String name, String gender, @Email(message = "E-mail format is invalid") String email,
			LocalDate birthDate, String naturality, String nationality, @CPF(message = "CPF format invalid") String cpf) {
		super();
		this.id = id;
		this.name = name;
		this.gender = gender;
		this.email = email;
		this.birthDate = birthDate;
		this.naturality = naturality;
		this.nationality = nationality;
		this.cpf = cpf;
	}



	public Long getId() {
		return id;
	}

	public String getName() {
		return name;
	}

	public String getGender() {
		return gender;
	}

	public String getEmail() {
		return email;
	}

	public LocalDate getBirthDate() {
		return birthDate;
	}

	public String getNaturality() {
		return naturality;
	}

	public String getNationality() {
		return nationality;
	}

	public String getCpf() {
		return cpf;
	}

	public LocalDate getCreateDate() {
		return createDate;
	}

	public LocalDate getUpdateDate() {
		return updateDate;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public void setBirthDate(LocalDate birthDate) {
		this.birthDate = birthDate;
	}

	public void setNaturality(String naturality) {
		this.naturality = naturality;
	}

	public void setNationality(String nationality) {
		this.nationality = nationality;
	}
	
	public void setCpf(String cpf) {
		this.cpf = cpf;
	}

	public void setCreateDate(LocalDate createDate) {
		this.createDate = createDate;
	}

	public void setUpdateDate(LocalDate updateDate) {
		this.updateDate = updateDate;
	}
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		User other = (User) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}

}
