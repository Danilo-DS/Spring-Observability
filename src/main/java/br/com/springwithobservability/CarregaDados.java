package br.com.springwithobservability;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

import br.com.springwithobservability.repository.UserRepository;
import br.com.springwithobservability.user.User;

@Component
public class CarregaDados implements ApplicationRunner{
	
	@Autowired
	private UserRepository userRepository;
	
	@Override
	public void run(ApplicationArguments args) throws Exception {
		if(userRepository.findAll().isEmpty()) {
			userRepository.save(new User(null, "Usuario 1", "usuarioone@gmail.com"));
			userRepository.save(new User(null, "Usuario 2", "usuariotwo@gmail.com"));
			userRepository.save(new User(null, "Usuario 3", "usuariotree@gmail.com"));
			userRepository.save(new User(null, "Usuario 4", "usuariofor@gmail.com"));
		}
	}

}
