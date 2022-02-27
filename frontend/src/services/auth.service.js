import axios from 'axios';

const API_URL = 'http://localhost:8080/api/auth/';

class AuthService {
  login(user) {
    return axios
      .post(API_URL + 'signin', {
        username: user.username,
        password: user.password
      })
      .then(response => {
        if (response.data.accessToken) {
          localStorage.setItem('user', JSON.stringify(response.data));
        }

        return response.data;
      });
  }

  logout() {
    localStorage.removeItem('user');
  }

  register(user) {
    return axios.post(API_URL + 'signup', {
      username: user.username,
      email: user.email,
      password: user.password
    });
  }

  addTrainer(trainer) {
    return axios.post(API_URL +'trainer', {
        name: trainer.name,
        email: trainer.email,
        type: trainer.type
    });
}

addProgram(program) {
  return axios.post(API_URL +'program', {
      name: program.name,
      description: program.description,
      trainerName : program.trainerName,
      price: program.price
  });
}

addMessage(messages) {
  return axios.post(API_URL +'messages', {
      name: messages.name,
      email: messages.email,
      subject: messages.subject,
      message:messages.message
  });
}

}

export default new AuthService();
