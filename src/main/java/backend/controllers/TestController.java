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
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import backend.models.Program;
import backend.models.ProgramTime;
import backend.models.Role;
import backend.models.User;
import backend.repository.BookingsRepository;
import backend.repository.ProgramRepository;
import backend.repository.ProgramTimeRepository;
import backend.repository.RoleRepository;
import backend.repository.UserRepository;
import backend.response.MessageResponse;
import backend.service.DatabaseService;
import backend.service.ProgramService;
import backend.service.UserService;

@CrossOrigin(origins ="*", maxAge = 3600)
@RestController
@RequestMapping(path = "/api/test", headers = "Accept=application/json")
public class TestController {

  private UserRepository userRepository;

  
  DatabaseService databaseService;

  @Autowired
  ProgramRepository programRepository;

  @Autowired
  ProgramTimeRepository programTimeRepository;

  @Autowired
  UserService userService;

  @Autowired
  ProgramService programService;

  @Autowired
  RoleRepository roleRepository;

  @Autowired
  BookingsRepository bookingsRepository;



  @Autowired
  public TestController(UserRepository userRepository,DatabaseService databaseService) {
    this.userRepository = userRepository;
    this.databaseService = databaseService;

  }


  @GetMapping("/roles")
  // @PreAuthorize is used to provide expression-based access control before
  // executing the method.
  @PreAuthorize("hasRole('ADMIN')")
  public List<Role> fetchRoles() {
    return roleRepository.findAll();
  }

  @PutMapping("users/{id}")
  @PreAuthorize("hasRole('ADMIN')")
  public void updateUser(@PathVariable long id, @RequestBody User user) {
    String fullName = user.getFullName();
    String name = user.getUsername();
    String email = user.getEmail();
    String address = user.getAddress();
    String phoneNumber = user.getPhoneNumber();
    userService.updateUsers(id, fullName,name, email,address,phoneNumber);

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

  @GetMapping("program/{id}")
  @PreAuthorize("hasRole('ADMIN')")
  public ResponseEntity<Program> getProgramById(@PathVariable("id") long id) {
    Optional<Program> program = programRepository.findById(id);

    if (program.isPresent()) {
      return new ResponseEntity<>(program.get(), HttpStatus.OK);
    } else {
      return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

  }

  

  @GetMapping("programs/program/{id}")
  @PreAuthorize("hasRole('ADMIN')")
  public ResponseEntity<Program> getProgramsById(@PathVariable("id") long id) {
    Optional<Program> programData = programRepository.findById(id);

    if (programData.isPresent()) {
      return new ResponseEntity<>(programData.get(), HttpStatus.OK);
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

 

  @DeleteMapping("programs/program/{id}")
  @PreAuthorize("hasRole('ADMIN')")
  public ResponseEntity<HttpStatus> deleteProgram(@PathVariable("id") long id) {
    try {
      Program program = programRepository.findById(id).get();
      program.remove();
      programRepository.delete(program);
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
  @PreAuthorize("hasRole('ADMIN')")
  public List<User> fetchUsers() {
    return userRepository.findAll();
  }

  @GetMapping("/timeprograms")
  public List<ProgramTime> fetchProgramTime() {
    return programTimeRepository.findAll();
  }

 

  
//get all trainers from database
@CrossOrigin
@GetMapping(path = "/trainersByCourseId/{id}")
@PreAuthorize("hasRole('ADMIN')")
public List<User> fetchTrainersForCourse( @PathVariable Long id) throws Exception {
  return databaseService.getTrainersForCourseId(id);
}

//get all trainers from database
@CrossOrigin
@DeleteMapping(path = "/removeTrainersFromCourse/{idCourse}/{idTrainer}")
@PreAuthorize("hasRole('ADMIN')")
public void removeTrainersFromCourse( @PathVariable Long idCourse, @PathVariable Long idTrainer) throws Exception {
   databaseService.removeTrainersFromCourse(idCourse, idTrainer);
}


@GetMapping(path = "/time/{id}")
@PreAuthorize("hasRole('ADMIN')")
public List<String> fetchTimeForCourse( @PathVariable Long id) throws Exception {
  return databaseService.getTimeForCourseId(id);
}


@DeleteMapping(path = "/time/{time}/{id}")
@PreAuthorize("hasRole('ADMIN')")
public void removeTimeFromProgram( @PathVariable String time, @PathVariable Long id) throws Exception {
  databaseService.removeTimeFromProgram(time, id);
}



@PostMapping("/trainerIdToCourseId/{trainerId}/{courseId}")
public ResponseEntity<?> trainerToCourse(@PathVariable Long trainerId, @PathVariable Long courseId) throws Exception {
  
  databaseService.insertTrainerToCourse(trainerId,courseId);

  return ResponseEntity.ok(new MessageResponse("S-a facut inregistrarea!"));
}

@PostMapping("/time/{time}/{courseId}")
public ResponseEntity<?> insertTimeForCourse(@PathVariable String time, @PathVariable Program courseId) throws Exception {
  
  databaseService.insertTimeCourse(time,courseId);

  return ResponseEntity.ok(new MessageResponse("Succes!"));
}


@PostMapping("/booking/{courseId}/{grupa}/{timeId}/{trainerId}/{userId}")
public void insertBooking(@PathVariable Long courseId, @PathVariable Long grupa
, @PathVariable Long timeId, @PathVariable Long trainerId, @PathVariable Long userId) throws Exception {

  databaseService.insertBooking(courseId,grupa,timeId,trainerId,userId);
}



//get all trainers from database
@CrossOrigin
@GetMapping(path = "/trainersNotByCourseId/{id}")
@PreAuthorize("hasRole('ADMIN')")
public List<User> fetchTrainersNotFromCourseId( @PathVariable Long id) throws Exception {
  return databaseService.getTrainersNotByCourseId(id);
}


  @GetMapping(path = "/trainers", produces="application/json")
 @PreAuthorize("hasRole('ADMIN')")
  public List<User> fetchTrainers() throws Exception {
    return databaseService.getTrainers();
  }

    @GetMapping(path="/programsForTrainers", produces="application/json")
    @PreAuthorize("hasRole('ADMIN')")
    public List<String> fetchProgramsForTrainers() throws Exception{
      return databaseService.getPrograms();
    }

     @GetMapping(path="/programs", produces="application/json")
     @PreAuthorize("hasRole('ADMIN')")
     public List<Program> fetchPrograms() throws Exception{
       return programRepository.findAll();
     }
  


  @PutMapping("programs/{id}")
  @PreAuthorize("hasRole('ADMIN')")
  public void updateProgram(@PathVariable long id, @RequestBody Program program) {
    String name = program.getName();
    String description = program.getDescription();
    String price = program.getPrice();
    programService.updatePrograms(id, name, description,price);
   

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

  
  @GetMapping("/programsNumber")
  @PreAuthorize("hasRole('ADMIN')")
  public Long getNumberOfPrograms() {
    return programRepository.count();
  }


  @GetMapping(path="/bookings", produces="application/json")
  @PreAuthorize("hasRole('ADMIN')")
  public String fetchBookings() throws Exception{
    String delim = ", ";
    return String.join(delim, databaseService.getBookings()); 

}


  @GetMapping(path="/bookings-number", produces="application/json")
  @PreAuthorize("hasRole('ADMIN')")
  public Long fetchNumberOfBookings() throws Exception{
  
    return bookingsRepository.count();

}


 @GetMapping(path="/bookings-course/{userId}", produces="application/json")
 @PreAuthorize("hasRole('ADMIN')")
 public String fetchBookingsCourse(@PathVariable Long userId) throws Exception{
  String delim = ", ";
 
  return String.join(delim, databaseService.getBookingsCourse(userId)); 
}


}