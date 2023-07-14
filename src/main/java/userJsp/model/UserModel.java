package userJsp.model;

import java.util.List;
import java.util.Objects;

import userJsp.model.UserModel;

public class UserModel {
	 UserModel() {}
	 
	public UserModel(String name, String userName, String email, String password, String cpf, String responsibleUser,
			Boolean status, List<Object> permissions) {
		super();
		this.name = name;
		this.userName = userName;
		this.email = email;
		this.password = password;
		this.cpf = cpf;
		this.responsibleUser = responsibleUser;
		this.status = status;
		this.permissions = permissions;
	}

	private Long id;
	private String name;
	private String userName;
	private String email;
	private String password;
	private String cpf;
	private  String responsibleUser;
	private Boolean status;
	private List<Object> permissions;
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getCpf() {
		return cpf;
	}
	public void setCpf(String cpf) {
		this.cpf = cpf;
	}
	public String getResponsibleUser() {
		return responsibleUser;
	}
	public void setResponsibleUser(String responsibleUser) {
		this.responsibleUser = responsibleUser;
	}
	public Boolean getStatus() {
		return status;
	}
	public void setStatus(Boolean status) {
		this.status = status;
	}
	public List<Object> getPermissions() {
		return permissions;
	}
	public void setPermissions(List<Object> permissions) {
		this.permissions = permissions;
	}
	@Override
	public int hashCode() {
		return Objects.hash(email, name, userName);
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		UserModel other = (UserModel) obj;
		return Objects.equals(email, other.email) && Objects.equals(name, other.name)
				&& Objects.equals(userName, other.userName);
	}

	
	
}
