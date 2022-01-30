package backend.controllers;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import backend.models.Trainer;
import backend.models.User;
import backend.repository.RoleRepository;
import backend.repository.TrainerRepository;
import backend.repository.UserRepository;
import backend.service.TrainerService;
import backend.service.UserService;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/test")
public class TestController {

  private UserRepository userRepository;

  @Autowired
  TrainerRepository trainerRepository;

  @Autowired
  UserService userService;

  @Autowired
  TrainerService trainerService;

  @Autowired
  RoleRepository roleRepository;

  @Autowired
  public TestController(UserRepository userRepository) {
    this.userRepository = userRepository;
  }


  @PutMapping("users/{id}")
  @PreAuthorize("hasRole('ADMIN')")
  public void updateUser(@PathVariable long id, @RequestBody User user) {
    String name = user.getUsername();
    String email = user.getEmail();
    userService.updateUsers(id, name, email);

  }

  @GetMapping("admin/user/{id}")
  @PreAuthorize("hasRole('ADMIN')")
  public ResponseEntity<User> getUserById(@PathVariable("id") long id) {
    Optional<User> userData = userRepository.findById(id);

    if (userData.isPresent()) {
      return new ResponseEntity<>(userData.get(), HttpStatus.OK);
    } else {
      return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

  }

  @GetMapping("trainers/trainer/{id}")
  @PreAuthorize("hasRole('ADMIN')")
  public ResponseEntity<Trainer> getTrainerById(@PathVariable("id") long id) {
    Optional<Trainer> trainerData = trainerRepository.findById(id);

    if (trainerData.isPresent()) {
      return new ResponseEntity<>(trainerData.get(), HttpStatus.OK);
    } else {
      return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

  }


  // delete user by id
  @DeleteMapping("admin/user/{id}")
  @PreAuthorize("hasRole('ADMIN')")
  public ResponseEntity<HttpStatus> deleteUser(@PathVariable("id") long id) {
    try {
      userRepository.deleteById(id);
      return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    } catch (Exception e) {
      return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
    }
  }

  @DeleteMapping("trainers/trainer/{id}")
  @PreAuthorize("hasRole('ADMIN')")
  public ResponseEntity<HttpStatus> deleteTrainer(@PathVariable("id") long id) {
    try {
      trainerRepository.deleteById(id);
      return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    } catch (Exception e) {
      return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
    }
  }

  // get user by username
  @GetMapping("/admin/{username}")
  @PreAuthorize("hasRole('ADMIN')")
  public ResponseEntity<User> getUserByName(@PathVariable("username") String username) {
    Optional<User> userData = userRepository.findByUsername(username);

    if (userData.isPresent()) {
      return new ResponseEntity<>(userData.get(), HttpStatus.OK);
    } else {
      return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
  }

  // get all users from database
  @GetMapping("/admin")
  // @PreAuthorize is used to provide expression-based access control before
  // executing the method.
  @PreAuthorize("hasRole('ADMIN')")
  public List<User> fetchUsers() {
    return userRepository.findAll();
  }

  //get all trainers from database
  @GetMapping("/trainers")
  // @PreAuthorize is used to provide expression-based access control before
  // executing the method.
 @PreAuthorize("hasRole('ADMIN')")
  public List<Trainer> fetchTrainers() {
    return trainerRepository.findAll();
  }

  @PutMapping("trainers/{id}")
  @PreAuthorize("hasRole('ADMIN')")
  public void updateTrainer(@PathVariable long id, @RequestBody Trainer trainer) {
    String name = trainer.getName();
    String email = trainer.getEmail();
    String type =  trainer.getType();
    trainerService.updateTrainers(id, name, email,type);

  }


  @GetMapping("/all")
  public String allAccess() {
    return "Welcome.";
  }

  @GetMapping("/usersNumber")
  @PreAuthorize("hasRole('ADMIN')")
  public Long getNumberOfUsers() {
    return userRepository.count();
  }

}