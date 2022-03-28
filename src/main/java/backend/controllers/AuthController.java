package backend.controllers;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
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
import backend.models.Trainer;
import backend.models.User;
import backend.repository.MessageRepository;
import backend.repository.ProgramRepository;
import backend.repository.RoleRepository;
import backend.repository.TrainerRepository;
import backend.repository.UserRepository;
import backend.requests.AddProgramRequest;
import backend.requests.AddTrainerRequest;
import backend.requests.LoginRequest;
import backend.requests.ReceiveMessagesRequest;
import backend.requests.SignupRequest;
import backend.response.JwtResponse;
import backend.response.MessageResponse;
import backend.security.JwtUtils;
import backend.service.UserDetailsImpl;


@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/auth")
public class AuthController {
	@Autowired
	AuthenticationManager authenticationManager;

	@Autowired
	UserRepository userRepository;

	@Autowired
	TrainerRepository trainerRepository;

	@Autowired
	ProgramRepository programRepository;

	@Autowired
	RoleRepository roleRepository;

	@Autowired
	MessageRepository messageRepository;


	@Autowired
	PasswordEncoder encoder;

	@Autowired
	JwtUtils jwtUtils;


	@PostMapping("/signin")
	public ResponseEntity<?> authenticateUser(@Valid @RequestBody LoginRequest loginRequest) {

		Authentication authentication = authenticationManager.authenticate(
				new UsernamePasswordAuthenticationToken(loginRequest.getUsername(), loginRequest.getPassword()));

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
												 userDetails.getAddress()));
}

	@PostMapping("/signup")
	public ResponseEntity<?> registerUser(@Valid @RequestBody SignupRequest signUpRequest) {
		if (userRepository.existsByUsername(signUpRequest.getUsername())) {
			return ResponseEntity
					.badRequest()
					.body(new MessageResponse("Error: Username is already taken!"));
		}

		if (userRepository.existsByEmail(signUpRequest.getEmail())) {
			return ResponseEntity
					.badRequest()
					.body(new MessageResponse("Error: Email is already in use!"));
		}

		// Create new user's account
		User user = new User(signUpRequest.getFullName(),
		                     signUpRequest.getUsername(), 
							 signUpRequest.getEmail(),
							 encoder.encode(signUpRequest.getPassword()),
							 signUpRequest.getPhoneNumber(),
							 signUpRequest.getAddress());

		Set<String> strRoles = signUpRequest.getRole();
		Set<Role> roles = new HashSet<>();

		if (strRoles == null) {
			Role userRole = roleRepository.findByName(UsersRoles.ROLE_USER)
					.orElseThrow(() -> new RuntimeException("Error: Role is not found."));
			roles.add(userRole);
		} else {
			strRoles.forEach(role -> {
				switch (role) {
				case "admin":
					Role adminRole = roleRepository.findByName(UsersRoles.ROLE_ADMIN)
							.orElseThrow(() -> new RuntimeException("Error: Role is not found."));
					roles.add(adminRole);

					break;
				case "trainer":
					Role trainerRole = roleRepository.findByName(UsersRoles.ROLE_TRAINER)
							.orElseThrow(() -> new RuntimeException("Error: Role is not found."));
					roles.add(trainerRole);

					break;

				default:
					Role userRole = roleRepository.findByName(UsersRoles.ROLE_USER)
							.orElseThrow(() -> new RuntimeException("Error: Role is not found."));
					roles.add(userRole);
				}
			});
		}

		user.setRoles(roles);
		userRepository.save(user);

		return ResponseEntity.ok(new MessageResponse("Contul a fost creat cu succes!"));
	}

	@PostMapping("/trainer")
	public ResponseEntity<?> registerTrainer(@Valid @RequestBody AddTrainerRequest addTrainerRequest) {
		if (trainerRepository.existsByUsername(addTrainerRequest.getUsername())) {
			return ResponseEntity
					.badRequest()
					.body(new MessageResponse("Error: Name is already taken!"));
		}

		if (trainerRepository.existsByEmail(addTrainerRequest.getEmail())) {
			return ResponseEntity
					.badRequest()
					.body(new MessageResponse("Error: Email is already in use!"));
		}

		// Create new trainer's account
		Trainer trainer = new Trainer(addTrainerRequest.getFullName(),
			addTrainerRequest.getUsername(), 
							 addTrainerRequest.getEmail(),
							 addTrainerRequest.getDescription(),
							 encoder.encode(addTrainerRequest.getPassword()),
							 addTrainerRequest.getPhoneNumber(),
							 addTrainerRequest.getAddress());

	    Set<String> strRoles = addTrainerRequest.getRole();
		Set<Role> roles = new HashSet<>();

		if (strRoles == null) {
			Role userRole = roleRepository.findByName(UsersRoles.ROLE_TRAINER)
					.orElseThrow(() -> new RuntimeException("Error: Role is not found."));
			roles.add(userRole);
		} else {
			strRoles.forEach(role -> {
				switch (role) {

				default:
					Role trainerRole = roleRepository.findByName(UsersRoles.ROLE_TRAINER)
							.orElseThrow(() -> new RuntimeException("Error: Role is not found."));
					roles.add(trainerRole);
				}
			});
		}

		trainer.setRoles(roles);

		trainerRepository.save(trainer);

		return ResponseEntity.ok(new MessageResponse("Trainer add successfully!"));
	}

/*
	@PostMapping("/program")
	public ResponseEntity<?> registerProgram(@Valid @RequestBody AddProgramRequest addProgramRequest) {
		if (programRepository.existsByName(addProgramRequest.getName())) {
			return ResponseEntity
					.badRequest()
					.body(new MessageResponse("Error: Name is already taken!"));
		}

		// Create new program
		Program program = new Program(addProgramRequest.getName(), 
							 addProgramRequest.getDescription(),
							 addProgramRequest.getTrainerName(),
							 addProgramRequest.getPrice());


		programRepository.save(program);

		return ResponseEntity.ok(new MessageResponse("Program add successfully!"));
	}


	//request for contact messages and put them in database
	@PostMapping("/messages")
	public ResponseEntity<?> receiveMessages(@Valid @RequestBody ReceiveMessagesRequest messageRequest) {

		// Receive messages
		Messages message = new Messages(messageRequest.getName(),messageRequest.getEmail(),messageRequest.getSubject()
		, messageRequest.getMessage());


		messageRepository.save(message);

		return ResponseEntity.ok(new MessageResponse("Message send successfully!"));
	}
*/


}
