import axios from 'axios';
import authHeader from './auth-header';

const API_URL = 'http://localhost:8080/api/test/admin';
const API_URL_USER = 'http://localhost:8080/api/test';



class User {

  getTrainerByCourse(id) {
    return axios.get(`${API_URL_USER}/trainersByCourseId${id}`, { headers: authHeader() });

}

    getUsers() {
        return axios.get(API_URL,  { headers: authHeader() });
          
      }
      deleteUsersById(id) {
        return axios.delete(`${API_URL}/user/${id}`,{ headers: authHeader() });
      }
    getUser(id){
      return axios.get(`${API_URL}/user/${id}`, { headers: authHeader() });
      
    }
    updateUser(id,user) {
      return axios.put(`${API_URL_USER}/users/${id}`,user,  { headers: authHeader() });
  }

  getNumberOfUsers() {
    return axios.get(`${API_URL_USER}/usersNumber/`,  { headers: authHeader() });
}

getNumberOfBookings() {
  return axios.get(`${API_URL_USER}/bookings-number/`,  { headers: authHeader() });
}

getCoursesForUser(idCourse) {
  return axios.get(`${API_URL_USER}/bookings-course/${idCourse}`,  { headers: authHeader() });
    
}

getUsersWithBooking() {
  return axios.get(`${API_URL_USER}/bookings/`,  { headers: authHeader() });
    
}
    
    
}

export default new User();
