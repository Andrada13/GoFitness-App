package backend.controllers;

import java.util.ArrayList;

import java.util.List;

import java.util.stream.Collectors;

import javax.validation.Valid;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import backend.models.UsersRoles;
import backend.models.Messages;
import backend.models.Program;
import backend.models.Role;
import backend.models.User;
import backend.repository.MessageRepository;
import backend.repository.ProgramRepository;
import backend.repository.ProgramTimeRepository;
import backend.repository.RoleRepository;
import backend.repository.UserRepository;
import backend.requests.AddProgramRequest;

import backend.requests.LoginRequest;
import backend.requests.ReceiveMessagesRequest;
import backend.requests.SignupRequest;
import backend.response.JwtResponse;
import backend.response.MessageResponse;
import backend.security.JwtUtils;
import backend.service.UserDetailsImpl;

@JsonIgnoreProperties(ignoreUnknown = true) 
@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/auth")
public class AuthController {
	@Autowired
	AuthenticationManager authenticationManager;

	@Autowired
	UserRepository userRepository;

	

	@Autowired
	ProgramRepository programRepository;

	@Autowired
	RoleRepository roleRepository;



	@Autowired
	MessageRepository messageRepository;

	@Autowired
	ProgramTimeRepository programTimeRepository;

	@Autowired
	PasswordEncoder encoder;

	@Autowired
	JwtUtils jwtUtils;


	@PostMapping("/signin")
	public ResponseEntity<?> authenticateUser(@Valid @RequestBody LoginRequest loginRequest) {
/* 
		if (!userRepository.existsByUsername(loginRequest.getUsername())) {
			return ResponseEntity
					.badRequest()
					.body(new MessageResponse("Username-ul nu exista!"));
		}
 */
		//boolean p = encoder.matches(loginRequest.getPassword(),encoder.encode(loginRequest.getPassword()));
		

		if (!userRepository.existsByUsername(loginRequest.getUsername())) {
			return ResponseEntity
					.badRequest()
					.body(new MessageResponse("Username-ul nu exista!"));
		}
 
		//System.exit(0);

		Authentication authentication;
		try{
		 authentication = authenticationManager.authenticate(
				new UsernamePasswordAuthenticationToken(loginRequest.getUsername(), loginRequest.getPassword()));

		}catch(AuthenticationException e){
			return ResponseEntity
					.badRequest()
					.body(new MessageResponse("Parola incorecta!"));
		}


		SecurityContextHolder.getContext().setAuthentication(authentication);
		String jwt = jwtUtils.generateJwtToken(authentication);
		
		UserDetailsImpl userDetails = (UserDetailsImpl) authentication.getPrincipal();		
		List<String> roles = userDetails.getAuthorities().stream()
				.map(item -> item.getAuthority())
				.collect(Collectors.toList());

				
		
		return ResponseEntity.ok(new JwtResponse(jwt, 
												 userDetails.getId(), 
												 userDetails.getFullName(),
												 userDetails.getUsername(), 
												 userDetails.getEmail(),
												 roles,			
												 userDetails.getPhoneNumber(),
												 userDetails.getAddress(), userDetails.getPrograms()));
}

	@PostMapping("/signup")
	public ResponseEntity<?> registerUser(@Valid @RequestBody SignupRequest signUpRequest) {
		if (userRepository.existsByUsername(signUpRequest.getUsername())) {
			return ResponseEntity
					.badRequest()
					.body(new MessageResponse("Username-ul exista deja!"));
		}

		if (userRepository.existsByEmail(signUpRequest.getEmail())) {
			return ResponseEntity
					.badRequest()
					.body(new MessageResponse("Email-ul exista deja!"));
		}

		// Create new user's account
		User user = new User(signUpRequest.getFullName(),
		                     signUpRequest.getUsername(), 
							 signUpRequest.getEmail(),
							 encoder.encode(signUpRequest.getPassword()),
							 signUpRequest.getPhoneNumber(),
							 signUpRequest.getAddress()
							 );

		Integer roleId = signUpRequest.getRole();
		List<Role> roles = new ArrayList<>();
		//Role newRole= new Role();

		if (roleId == null) {
			Role userRole = roleRepository.findByName(UsersRoles.ROLE_USER)
					.orElseThrow(() -> new RuntimeException("Error: Role is not found."));
			roles.add(userRole);
		} else {
			
				Role newRole = roleRepository.findById(roleId);
			    roles.add(newRole);
				
			
		}

		user.setRoles(roles);
		userRepository.save(user);

		return ResponseEntity.ok(new MessageResponse("Contul a fost creat cu succes!"));
	}



	@PostMapping("/program")
	public ResponseEntity<?> registerProgram(@Valid @RequestBody AddProgramRequest addProgramRequest) throws JsonMappingException, JsonProcessingException {
		if (programRepository.existsByName(addProgramRequest.getName())) {
			return ResponseEntity
					.badRequest()
					.body(new MessageResponse("Numele cursului deja exista!"));
		}

		// Create new program
		Program program = new Program(addProgramRequest.getName(), 
							 addProgramRequest.getDescription(),
							 addProgramRequest.getPrice());


		programRepository.save(program);

		return ResponseEntity.ok(new MessageResponse("Curs adaugat cu succes!"));
	}


	//request for contact messages and put them in database
	@PostMapping("/messages")
	public ResponseEntity<?> receiveMessages(@Valid @RequestBody ReceiveMessagesRequest messageRequest) {

		// Receive messages
		Messages message = new Messages(messageRequest.getName(),messageRequest.getEmail(),messageRequest.getSubject()
		, messageRequest.getMessage());


		messageRepository.save(message);

		return ResponseEntity.ok(new MessageResponse("Mesajul a fost trimis cu succes! Multumim!"));
	}


}
