package br.com.springwithobservability.user;

import java.io.Serializable;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Entity(name = "Usuario")
@Data
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class User implements Serializable {
	private static final long serialVersionUID = 2962035648914727563L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE)
	@Column(name = "Codigo")
	private Long id;
	
	@Column(name = "Nome_Usuario", length = 55)
	private String nome;
	
	@Column(name = "Email_Usuario", length = 256)
	private String email;
	
	
}
