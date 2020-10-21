package com.blazdemsar.integration.dto;

import java.util.HashSet;
import java.util.Set;

import com.fasterxml.jackson.annotation.JsonBackReference;

public class Role {
    
    private Long roleId;
    
    private String roleName;
    
    @JsonBackReference
    private Set<User> users = new HashSet<User>();
    
    public Role() {
    	
    }
    
    public Role(String roleName) {
    	super();
    	this.roleName = roleName;
    }

	public Long getRoleId() {
		return roleId;
	}

	public void setRoleId(Long roleId) {
		this.roleId = roleId;
	}

	public String getRoleName() {
		return roleName;
	}

	public void setRoleName(String roleName) {
		this.roleName = roleName;
	}

	public Set<User> getUsers() {
		return users;
	}

	public void setUsers(Set<User> users) {
		this.users = users;
	}

	/*
	 * @Override public String toString() { return "Role [roleId=" + roleId +
	 * ", roleName=" + roleName + ", users=" + users + "]"; }
	 */
	
}
