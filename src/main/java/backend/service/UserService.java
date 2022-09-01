
package backend.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import backend.models.User;
import backend.repository.RoleRepository;
import backend.repository.UserRepository;

@Service
public class UserService {

	@Autowired
	UserRepository userRepository;
	@Autowired
	RoleRepository roleRepository;

	// method for update button on admin board
	public void updateUsers(long id, String fullName,String username, String email,String address, String phoneNumber) {
		User user = findById(id);

		user.setUsername(username);
		user.setEmail(email);
		user.setAddress(address);
		user.setFullName(fullName);
		user.setPhoneNumber(phoneNumber);
		userRepository.save(user);
		

	}

	public List<User> findAll() {
		List<User> users = new ArrayList<>();
		userRepository.findAll().forEach(users::add);
		return users;
	}

	public User findById(long id) {
		for (User user : findAll()) {
			if (user.getId() == id) {
				return user;
			}
		}

		return null;
	}

}
