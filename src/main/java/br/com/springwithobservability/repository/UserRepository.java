package br.com.springwithobservability.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.springwithobservability.user.User;

@Repository
public interface UserRepository extends JpaRepository<User, Long>{

}
