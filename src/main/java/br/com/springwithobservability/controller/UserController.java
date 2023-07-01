package br.com.springwithobservability.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.springwithobservability.service.UserService;
import br.com.springwithobservability.user.User;
import io.micrometer.observation.annotation.Observed;

@RestController
@RequestMapping(path = "/users")
@Observed(name = "userController", contextualName = "userController")
public class UserController {
	
	@Autowired
	private UserService service;
	
	@GetMapping
	public ResponseEntity<List<User>> listAll(){
		return ResponseEntity.ok(service.list());
	}
	
	@PostMapping
	public ResponseEntity<User> save(@RequestBody User user){
		return ResponseEntity.status(HttpStatus.CREATED).body(service.save(user));
	}
	
	@GetMapping(path = "/{id}")
	public ResponseEntity<User> findById(@PathVariable Long id){			
		return ResponseEntity.ok(service.find(id));
	}
	
	@PutMapping(path = "/{id}")
	public ResponseEntity<User> update(@RequestBody User user, @PathVariable Long id){
		return ResponseEntity.status(HttpStatus.CREATED).body(service.update(user, id));
	}
	
	@DeleteMapping(path = "/{id}")
	public ResponseEntity<?> delete(@PathVariable Long id){
		service.delete(id);
		return ResponseEntity.noContent().build();
	}
}
