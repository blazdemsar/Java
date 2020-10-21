package com.synergisticit.service;

import java.util.List;

import com.synergisticit.domain.Role;

public interface RoleService {
  public Role createRole(Role role);
  public List<Role> findAll();
}
