import axios from 'axios';
import authHeader from './auth-header';

const API_URL = 'http://localhost:8080/api/test/timeprograms';



class TimePrograms {

    getTimePrograms() {
        return axios.get(API_URL, { headers: authHeader() });

    }
            

}

export default new TimePrograms();
