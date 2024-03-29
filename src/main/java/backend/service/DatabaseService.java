package backend.service;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import org.hibernate.annotations.QueryHints;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import backend.models.Bookings;
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
							User.class)
					.setParameter("role", UsersRoles.ROLE_TRAINER)
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
	public List<User> getTrainersForCourseId(Long id) throws Exception {

		List<User> trainers = new ArrayList<User>();
		EntityManager entityManager = entityManagerFactory.createEntityManager();

		try {

			trainers = entityManager
					.createQuery("select distinct t from User t JOIN FETCH t.roles c WHERE c.name = :role",
							User.class)
					.setParameter("role", UsersRoles.ROLE_TRAINER)
					.setHint(QueryHints.PASS_DISTINCT_THROUGH, false)
					.getResultList();

			trainers = entityManager
					.createQuery("SELECT distinct t FROM User  t JOIN t.programs p WHERE p.id = :id  ",
							User.class)
					.setParameter("id", id)
					.setHint(QueryHints.PASS_DISTINCT_THROUGH, false)
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
							String.class)
					.setParameter("id", id)
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
					.createQuery(
							"select t from User t JOIN FETCH t.roles c WHERE c.name = :role and not exists (select up from UsersPrograms up where t.id=up.trainerId and up.programsId=:id)",
							User.class)
					.setParameter("id", id).setParameter("role", UsersRoles.ROLE_TRAINER)
					.getResultList();
		} finally {
			if (entityManager != null) {
				entityManager.close();
			}
		}

		return trainers;

	}

	@Transactional
	public void removeTrainersFromCourse(Long idCourse, Long idTrainer) throws Exception {

		EntityManager entityManager = entityManagerFactory.createEntityManager();

		try {

			entityManager.getTransaction().begin();

			int isSuccessful = entityManager
					.createQuery("delete from UsersPrograms t where t.trainerId=:idTrainer and t.programsId=:idCourse")
					.setParameter("idTrainer", idTrainer)
					.setParameter("idCourse", idCourse)
					.executeUpdate();
			if (isSuccessful == 0) {
				System.out.println(" product modified concurrently");
			}

			entityManager.getTransaction().commit();

		} finally {
			if (entityManager != null) {
				entityManager.close();
			}
		}

	}

	@Transactional
	public void removeTimeFromProgram(String time, Long idCourse) throws Exception {

		EntityManager entityManager = entityManagerFactory.createEntityManager();

		Program program = entityManager.find(Program.class, idCourse);
		Long id = program.getId();

		try {

			entityManager.getTransaction().begin();

			entityManager
					.createQuery("delete from ProgramTime p where p.time_program=:time and p.program.id=:idCourse")
					.setParameter("time", time)
					.setParameter("idCourse", id)
					.executeUpdate();

			entityManager.getTransaction().commit();

		} finally {
			if (entityManager != null) {
				entityManager.close();
			}
		}

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
		
		} finally {
			if (entityManager != null) {
				entityManager.close();
			}
		}
	}

	@Transactional
	public void insertBooking(Long courseId, Long grupa, Long timeId, Long trainerId, Long userId) throws Exception {

		EntityManager entityManager = entityManagerFactory.createEntityManager();

		try {

			entityManager.getTransaction().begin();
			Bookings booking = new Bookings();
			booking.setGroup(grupa);
			booking.setCourseId(courseId);
			booking.setTimeId(timeId);
			booking.setTrainerId(trainerId);
			booking.setUserId(userId);
			booking.setPaid(false);
			booking.setCanceled(false);

			entityManager.persist(booking);
			entityManager.getTransaction().commit();
		
		} finally {
			if (entityManager != null) {
				entityManager.close();
			}
		}
	}

	@Transactional
	public List<String> getBookings() throws Exception {

		List<Long> userId = new ArrayList<>();
		List<String> bookingsUser = new ArrayList<>();
		List<String> b = new ArrayList<>();


		EntityManager entityManager = entityManagerFactory.createEntityManager();

	

		try {

			userId = entityManager
					.createQuery("SELECT distinct b.userid FROM Bookings b ",
							Long.class)
					.getResultList();



			for (Long booking : userId) {
				b = entityManager
						.createQuery("SELECT distinct u.username FROM User u where u.id=:idUser",
								String.class)
						.setParameter("idUser", booking)
						.getResultList();

				bookingsUser.addAll(b);
		

			}
		
		} finally {
			if (entityManager != null) {
				entityManager.close();
			}
		}

		return bookingsUser;

	}



	@Transactional
	public List<String> getBookingsCourse(Long userIdFromFrontend) throws Exception {


		List<Long> courseId = new ArrayList<>();
		List<String> nameCourseId = new ArrayList<>();
        List<String> finalListCourse = new ArrayList<>();
	
		EntityManager entityManager = entityManagerFactory.createEntityManager();


		try {

			courseId = entityManager
					.createQuery("SELECT distinct b.courseid FROM Bookings b where userid = :userId",
							Long.class)
							.setParameter("userId", userIdFromFrontend)
					.getResultList();

		for (Long course : courseId) {
			nameCourseId = entityManager
					.createQuery("SELECT distinct p.name FROM Program p where p.id = :courseId  ",
							String.class)
							.setParameter("courseId", course)
					.getResultList();

            finalListCourse.addAll(nameCourseId);

					}

		} finally {
			if (entityManager != null) {
				entityManager.close();
			}
		}

		return finalListCourse;

	}
}
