/**
 * 
 */
package ftn.diplomski.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ftn.diplomski.entity.security.Role;
import ftn.diplomski.repository.RoleDao;

/**
 * @author Boki on Jan 12, 2018
 *
 */
@Service
public class RoleServiceImpl implements RoleService {

	@Autowired
	private RoleDao roleDao;
	
	@Override
	public String getRole(int roleId) {
		Role role = roleDao.findOne(roleId);
		return role.getName();
	}

	@Override
	public List<Role> findAll() {
		return roleDao.findAll();
	}

	@Override
	public Role findByName(String rolena) {
		return roleDao.findByName(rolena);
	}

}
