package backend.service;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import backend.models.Program;
import backend.models.ProgramTime;
import backend.models.User;
import backend.models.UsersPrograms;
import backend.models.UsersRoles;

@Component
public class DatabaseService {
    
    private EntityManagerFactory entityManagerFactory;

	public DatabaseService() {
		this.entityManagerFactory = Persistence.createEntityManagerFactory("databaseService");
	}

	@Transactional
	public List<User> getTrainers() throws Exception {

		List<User> trainers = new ArrayList<User>();
		EntityManager entityManager = entityManagerFactory.createEntityManager();

		try {
			trainers = entityManager
					.createQuery("SELECT t FROM User t JOIN FETCH t.roles c WHERE c.name = :role ",
                   User.class).setParameter("role", UsersRoles.ROLE_TRAINER)
					.getResultList();
		} finally {
			if (entityManager != null) {
				entityManager.close();
			}
		}

		return trainers;

	}

	
	@Transactional
	public List<String> getPrograms() throws Exception {

		List<String> programs = new ArrayList<String>();
		EntityManager entityManager = entityManagerFactory.createEntityManager();

		try {
			programs = entityManager
					.createQuery("SELECT p.name FROM Program p join p.trainer t where t.id=2 ",
                   String.class)
					.getResultList();
		} finally {
			if (entityManager != null) {
				entityManager.close();
			}
		}

		return programs;

	}

	@Transactional
	public List<String> getTrainersForCourseId(Long id) throws Exception {

		List<String> trainers = new ArrayList<String>();
		EntityManager entityManager = entityManagerFactory.createEntityManager();

		try {
			trainers = entityManager
					.createQuery("SELECT t.fullName FROM User t JOIN t.programs p WHERE p.id = :id ",
                   String.class).setParameter("id", id)
					.getResultList();
		} finally {
			if (entityManager != null) {
				entityManager.close();
			}
		}

		return trainers;

	}
	@Transactional
	public List<String> getTimeForCourseId(Long id) throws Exception {

		List<String> time = new ArrayList<String>();
		EntityManager entityManager = entityManagerFactory.createEntityManager();

		try {
			time = entityManager
					.createQuery("SELECT t.time_program FROM ProgramTime t JOIN t.program p WHERE p.id = :id ",
                   String.class).setParameter("id", id)
					.getResultList();
		} finally {
			if (entityManager != null) {
				entityManager.close();
			}
		}

		return time;

	}

	@Transactional
	public List<User> getTrainersNotByCourseId(Long id) throws Exception {

		List<User> trainers = new ArrayList<User>();
		EntityManager entityManager = entityManagerFactory.createEntityManager();

		try {
			trainers = entityManager
					//.createQuery("SELECT t FROM User t JOIN t.programs p WHERE  p.id != :id ",
					.createQuery("select t from User t JOIN FETCH t.roles c WHERE c.name = :role and not exists (select up from UsersPrograms up where t.id=up.trainerId and up.programsId=:id)",
                   User.class).setParameter("id", id).setParameter("role", UsersRoles.ROLE_TRAINER)
					.getResultList();
		} finally {
			if (entityManager != null) {
				entityManager.close();
			}
		}

		return trainers;

	}

	@Transactional
	public void insertTrainerToCourse(Long trainerId, Long courseId) throws Exception {

	
		EntityManager entityManager = entityManagerFactory.createEntityManager();

	

		try {
	
			entityManager.getTransaction().begin();
			UsersPrograms usersPrograms = new UsersPrograms();
			usersPrograms.setProgramsId(courseId);
			usersPrograms.setTrainerId(trainerId);
			entityManager.persist(usersPrograms);
			entityManager.getTransaction().commit();
			/*
			entityManager.createNativeQuery("INSERT INTO usersPrograms (trainer_id, programs_id) VALUES (?,?) ")
					.setParameter(1, usersPrograms.getTrainerId())
					.setParameter(2, usersPrograms.getProgramsId())
					.executeUpdate();

					*/
		} finally {
			if (entityManager != null) {
				entityManager.close();
			}
		}

		

	}

	@Transactional
	public void insertTimeCourse(String time, Program courseId) throws Exception {

	
		EntityManager entityManager = entityManagerFactory.createEntityManager();

	

		try {
	
			entityManager.getTransaction().begin();
			ProgramTime programTime = new ProgramTime();
			programTime.setTime_program(time);
			programTime.setProgram(courseId);
			entityManager.persist(programTime);
			entityManager.getTransaction().commit();
			/*
			entityManager.createNativeQuery("INSERT INTO usersPrograms (trainer_id, programs_id) VALUES (?,?) ")
					.setParameter(1, usersPrograms.getTrainerId())
					.setParameter(2, usersPrograms.getProgramsId())
					.executeUpdate();

					*/
		} finally {
			if (entityManager != null) {
				entityManager.close();
			}
		}
	}
}
