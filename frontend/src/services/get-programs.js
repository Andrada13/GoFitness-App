import axios from 'axios';
import authHeader from './auth-header';

const API_URL = 'http://localhost:8080/api/test/programs';
const API_URL_PROGRAM = 'http://localhost:8080/api/test';
const API_URL_NEW_PROGRAM = 'http://localhost:8080/api/auth';



class Program {
    
    getTrainerByCourse(id) {
        return axios.get(`${API_URL_PROGRAM}/trainersByCourseId/${id}`, { headers: authHeader() });
    
    }

    removeTrainerFromCourse(idCourse, idTrainer) {
        return axios.delete(`${API_URL_PROGRAM}/removeTrainersFromCourse/${idCourse}/${idTrainer}`, { headers: authHeader() });
    
    }

    getTrainerNotByCourse(id) {
        return axios.get(`${API_URL_PROGRAM}/trainersNotByCourseId/${id}`, { headers: authHeader() });
    
    }

    getPrograms() {
        return axios.get(API_URL, { headers: authHeader() });

    }
    deleteProgramsById(id) {
        return axios.delete(`${API_URL}/program/${id}`, { headers: authHeader() });
    }
    getProgram(id) {
        return axios.get(`${API_URL}/program/${id}`, { headers: authHeader() });

    }
    updateProgram(id, program) {
        return axios.put(`${API_URL_PROGRAM}/programs/${id}`, program, { headers: authHeader() });
    }

    getNumberOfPrograms() {
        return axios.get(`${API_URL_PROGRAM}/programsNumber/`,  { headers: authHeader() });
    }
        

    addProgram(program) {
        return axios.post(`${API_URL_NEW_PROGRAM}/program`, {
            name: program.name,
            description: program.description,
            trainerName: program.trainerName,
            price: program.price
        });
    }

    addTimeProgram(time,id){
        return axios.post(`${API_URL_PROGRAM}/time/${time}/${id}`, {
           
        });
    }

    insertBooking(courseId,grupa,time,trainer,user){
        return axios.post(`${API_URL_PROGRAM}/booking/${courseId}/${grupa}/${time}/${trainer}/${user}`, {
           
        });
    }


    removeTimeFromProgram(time, id){
        return axios.delete(`${API_URL_PROGRAM}/time/${time}/${id}`, {
          // time: time
        });
    }

    addTrainerIdToCourseId(trainerToCourseId,id) {
        return axios.post(`${API_URL_PROGRAM}/trainerIdToCourseId/${trainerToCourseId}/${id}`, {
           
        });
    }

    getTimeByCourseId(id){
        return axios.get(`${API_URL_PROGRAM}/time/${id}`, {
           
        });
    }

    getProgramsById(id){
        return axios.get(`${API_URL_PROGRAM}/program/${id}`, {
           
        });
    }
}

export default new Program();
