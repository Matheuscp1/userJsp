package userJsp.model;

import java.util.Objects;

public class PermissionUser {

	public PermissionUser() {};
	public PermissionUser(Long id, Long userId, Long permissionId) {
		super();
		this.id = id;
		this.userId = userId;
		this.permissionId = permissionId;
	}
	private Long id;
	private Long userId;
	private Long permissionId;
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public Long getUserId() {
		return userId;
	}
	public void setUserId(Long userId) {
		this.userId = userId;
	}
	public Long getPermissionId() {
		return permissionId;
	}
	public void setPermissionId(Long permissionId) {
		this.permissionId = permissionId;
	}
	@Override
	public int hashCode() {
		return Objects.hash(permissionId);
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		PermissionUser other = (PermissionUser) obj;
		return Objects.equals(permissionId, other.permissionId);
	}

	
}
