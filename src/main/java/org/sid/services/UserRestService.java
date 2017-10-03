package org.sid.services;

import java.util.List;

import org.sid.dao.RoleRepository;
import org.sid.dao.UserRepository;
import org.sid.entities.Role;
import org.sid.entities.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Secured(value= {"ROLE_ADMIN"})
public class UserRestService {
	@Autowired
	private UserRepository userRepository;
	@Autowired
	private RoleRepository roleRepository;
	
	@RequestMapping(value="/addUser", method=RequestMethod.POST)
	public User save(@ RequestBody User u) {
		return userRepository.save(u);
	}
	
	@RequestMapping(value="/findUsers", method=RequestMethod.GET)
	public List<User> findAll(){
		return userRepository.findAll();
	}
	
	@RequestMapping(value="/addRole", method=RequestMethod.POST)
	public Role saveRole(Role r) {
		return roleRepository.save(r);
	}
	
	@RequestMapping(value="/findRoles", method=RequestMethod.GET)
	public List<Role> findRoles(){
		return roleRepository.findAll();
	}
	
	@RequestMapping(value="/addRoleToUser", method=RequestMethod.POST)
	public User addRoleToUser(String username, String role) {
		User u=userRepository.findByUsername(username);
		Role r=roleRepository.findByRole(role);
		u.getRoles().add(r);
		userRepository.save(u);
		return u;
	}
}
