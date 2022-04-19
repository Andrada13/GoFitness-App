import axios from 'axios';
import authHeader from './auth-header';

const API_URL = 'http://localhost:8080/api/test/trainers';
const API_URL_TRAINER = 'http://localhost:8080/api/test';
const API_URL_NEW_TRAINER = 'http://localhost:8080/api/auth';



class Trainer {

    getTrainers() {
        return axios.get(API_URL, { headers: authHeader() });

    }
    deleteTrainersById(id) {
        return axios.delete(`${API_URL}/trainer/${id}`, { headers: authHeader() });
    }
    getTrainerByCourse(id) {
        return axios.get(`${API_URL_TRAINER}/trainersByCourseId/${id}`, { headers: authHeader() });

    }
    updateTrainer(id, trainer) {
        return axios.put(`${API_URL_TRAINER}/trainers/${id}`, trainer, { headers: authHeader() });
    }

    getNumberOfTrainers() {
        return axios.get(`${API_URL_TRAINER}/trainersNumber/`,  { headers: authHeader() });
    }
        

    addTrainer(trainer) {
        return axios.post(`${API_URL_NEW_TRAINER}/trainer`, {
            name: trainer.name,
            email: trainer.email,
            type: trainer.type
        });
    }




}

export default new Trainer();
