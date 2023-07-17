package userJsp.model;

import java.util.List;
import java.util.Objects;

import userJsp.model.UserModel;

public class UserModel {
	 public UserModel() {}
	 
	public UserModel(String name, String userName, String email, String password, String cpf, Long supervisorId,
			Boolean status, List<PermissionUser> permissions, String supervisorName) {
		super();
		this.name = name;
		this.userName = userName;
		this.email = email;
		this.password = password;
		this.cpf = cpf;
		this.supervisorId = supervisorId;
		this.status = status;
		this.permissions = permissions;
		this.supervisorName = supervisorName;
	}

	private Long id;
	private String name;
	private String userName;
	private String email;
	private String password;
	private String cpf;
	private Long supervisorId;
	private String supervisorName;
	private Boolean status;
	private List<PermissionUser> permissions;
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
	public Long getsupervisorId() {
		return supervisorId;
	}
	public void setsupervisorId(Long supervisorId) {
		this.supervisorId = supervisorId;
	}
	public Boolean getStatus() {
		return status;
	}
	public void setStatus(Boolean status) {
		this.status = status;
	}
	public List<PermissionUser> getPermissions() {
		return permissions;
	}
	public void setPermissions(List<PermissionUser> permissions) {
		this.permissions = permissions;
	}


	public String getSupervisorName() {
		return supervisorName;
	}

	public void setSupervisorName(String supervisorName) {
		this.supervisorName = supervisorName;
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
