package userJsp.model;

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

	
}
