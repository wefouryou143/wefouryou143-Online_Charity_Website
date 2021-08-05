package com.capgemini.service;

import java.util.List;

import com.capgemini.entity.Role;

public interface RoleService {
	public List<Role> findAll();

	public Role findById(int roleId);

	public Role saveRole(Role role);

	public void deleteById(int id);
}
