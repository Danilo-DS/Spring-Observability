package br.com.springwithobservability.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.com.springwithobservability.exception.UserError;
import br.com.springwithobservability.repository.UserRepository;
import br.com.springwithobservability.user.User;
import io.micrometer.observation.annotation.Observed;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
@Observed(name = "userService", contextualName = "userService")
public class UserService {

	@Autowired
	private UserRepository userRepository;
	
	@Transactional(readOnly = true)
	public List<User> list(){
		log.info("[list] Get all users");
		return userRepository.findAll();
	}
	
	@Transactional(readOnly = true)
	public User find(Long id){
		
		log.info("[find] Get user by Id {}", id);
		Optional<User> user = userRepository.findById(id);
		
		return user.orElseThrow(() -> new UserError("Usuário Não Encontrado!"));
	}
	
	@Transactional
	public User save(User user) {
		return userRepository.save(user);
	}
	
	@Transactional
	public User update(User userUpdate, Long id) {
		
		User user = find(id);
		
		BeanUtils.copyProperties(userUpdate, user, "id");
		
		return userRepository.save(user);
	}
	
	@Transactional
	public void delete(Long id) {
		log.info("[delete] Delete user by Id {}", id);
		if(userRepository.existsById(id)) {
			log.info("[delete] User Deleted");
			userRepository.deleteById(id);
		}
		else {
			log.error("[delete] No possible delete user");
			throw new UserError("Unable to delete a non-existent user!");
		}
	}
}
