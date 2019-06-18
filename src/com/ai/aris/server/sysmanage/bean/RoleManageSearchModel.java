package com.ai.aris.server.sysmanage.bean;
/**
 * 
 * @author wuliangyong
 */
public class RoleManageSearchModel {

	private static final long serialVersionUID = 1L;

	private String createStart;

	private String createEnd;

	private String roleName;

	private String roleDes;

	private String roleId;
	
	private String roleIds;
	
	private String role2menuList;
	
	private String sysRole2menuList;
	
	private String isShow;
	
	private String sysType;//所属系统
	
	private SysMenuBean[] sysMenuBeans;
	
	private String operatorCode;
	private String menuIds; //菜单IDS
	
	private String orgId;

	private String menuName;

	private String dataType;

	public String getDataType() {
		return dataType;
	}
	public void setDataType(String dataType) {
		this.dataType = dataType;
	}
	public String getMenuName() {
		return menuName;
	}

	public void setMenuName(String menuName) {
		this.menuName = menuName;
	}

	public String getMenuIds() {
		return menuIds;
	}

	public void setMenuIds(String menuIds) {
		this.menuIds = menuIds;
	}

	public String getOperatorCode() {
		return operatorCode;
	}

	public void setOperatorCode(String operatorCode) {
		this.operatorCode = operatorCode;
	}

	public String getSysType() {
		return sysType;
	}

	public void setSysType(String sysType) {
		this.sysType = sysType;
	}

	public String getSysRole2menuList() {
		return sysRole2menuList;
	}

	public void setSysRole2menuList(String sysRole2menuList) {
		this.sysRole2menuList = sysRole2menuList;
	}

	public String getIsShow() {
		return isShow;
	}

	public void setIsShow(String isShow) {
		this.isShow = isShow;
	}

	public String getRoleIds() {
		return roleIds;
	}

	public void setRoleIds(String roleIds) {
		this.roleIds = roleIds;
	}

	public String getRole2menuList() {
		return role2menuList;
	}

	public void setRole2menuList(String role2menuList) {
		this.role2menuList = role2menuList;
	}

	public String getRoleId() {
		return roleId;
	}

	public void setRoleId(String roleId) {
		this.roleId = roleId;
	}

	public String getRoleDes() {
		return roleDes;
	}

	public void setRoleDes(String roleDes) {
		this.roleDes = roleDes;
	}

	public String getCreateEnd() {
		return createEnd;
	}

	public void setCreateEnd(String createEnd) {
		this.createEnd = createEnd;
	}

	public String getCreateStart() {
		return createStart;
	}

	public void setCreateStart(String createStart) {
		this.createStart = createStart;
	}

	public String getRoleName() {
		return roleName;
	}

	public void setRoleName(String roleName) {
		this.roleName = roleName;
	}


	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public SysMenuBean[] getSysMenuBeans() {
		return sysMenuBeans;
	}

	public void setSysMenuBeans(SysMenuBean[] sysMenuBeans) {
		this.sysMenuBeans = sysMenuBeans;
	}

	public String getOrgId() {
		return orgId;
	}

	public void setOrgId(String orgId) {
		this.orgId = orgId;
	}
	
	
    
}
