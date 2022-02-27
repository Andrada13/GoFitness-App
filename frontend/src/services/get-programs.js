import axios from 'axios';
import authHeader from './auth-header';

const API_URL = 'http://localhost:8080/api/test/programs';
const API_URL_PROGRAM = 'http://localhost:8080/api/test';
const API_URL_NEW_PROGRAM = 'http://localhost:8080/api/auth';



class Program {

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
}

export default new Program();
