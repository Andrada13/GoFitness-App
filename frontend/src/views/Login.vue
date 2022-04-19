<template>
  <body>
    <br><br><br>
    <div class="container">
      <div class="row">
        <div class="col-lg-10 col-xl-9 mx-auto">
          <div
            class="card flex-row my-5 border-0 shadow rounded-3 overflow-hidden"
          >
            <div class="card-img-left d-none d-md-flex">
            </div>
            <div class="card-body p-4 p-sm-6">
              <h6 class="card-title text-center mb-5 fw-light fs-5">
                Intrati in cont
              </h6>

              <form name="form" @submit.prevent="handleLogin">
                <div v-if="!successful">
                  <div class="form-floating mb-3">
                    <input
                      v-model="user.username"
                      v-validate="'required|min:3|max:20'"
                      type="text"
                      class="form-control"
                      id="floatingInputUsername"
                      placeholder="username"
                      required
                      autofocus
                    />
                    <label for="floatingInputUsername">Nume de utilizator :</label>

                    <small>
                      <div
                        v-if="submitted && errors.has('username')"
                        class="alert-danger"
                      >
                        {{ errors.first("username") }}
                      </div></small
                    >
                  </div>

                  <div class="form-floating mb-3">
                    <input
                      v-model="user.password"
                      v-validate="'required|min:6|max:40'"
                      type="password"
                      class="form-control"
                      id="floatingPassword"
                      placeholder="Password"
                    />
                    <label for="floatingPassword">Parola :</label>

                    <small>
                      <div
                        v-if="submitted && errors.has('password')"
                        class="alert-danger"
                      >
                        {{ errors.first("password") }}
                      </div></small
                    >
                  </div>

                  <div class="d-grid mb-2">
                    <button
                      class="
                        btn btn-lg btn-dark btn-login
                        fw-bold
                        text-uppercase
                      "
                      type="submit"
                    >
                      Conectare
                    </button>

                  </div>

                  <br />
                  <a class="d-block text-center mt-2 small" href="/register"
                    >Nu aveti un cont de utilizator? Inregistrati-va aici</a
                  >
                  <hr class="my-4" />
                </div>
              </form>

              <div
                v-if="message"
                class="alert"
                :class="successful ? 'alert-success' : 'alert-danger'"
              >
                {{ message }}
              </div>
            </div>
          </div>
        </div>
      </div>
    </div>
  </body>
</template>

<script>
import User from '../models/user';
export default {
  name: 'Login',
  data() {
    return {
      user: new User('', ''),
      message: '',
      submitted: false,
      successful: false,


    };
  },
  computed: {
    loggedIn() {
      return this.$store.state.auth.status.loggedIn;
    }
  },
  created() {
    if (this.loggedIn) {
      this.$router.push('/profile').catch((e)=>e);
    }
  },
  methods: {
    handleLogin() {
      this.$validator.validateAll().then(isValid => {
        if (!isValid) {
          return;
        }
        if (this.user.username && this.user.password) {
          this.$store.dispatch('auth/login', this.user).then( //Actions are called in the components with the method dispatch()
            () => {
              if(JSON.parse(localStorage.getItem('user')).roles == "ROLE_ADMIN"){
                   this.$router.push('/adminProfile');
              }else if(JSON.parse(localStorage.getItem('user')).roles == "ROLE_TRAINER"){
                   this.$router.push('/trainerProfile');
              }else if(JSON.parse(localStorage.getItem('user')).roles == "ROLE_USER"){
              this.$router.push('/profile');
              }
            },
            error => {
              this.message =
                (error.response && error.response.data && error.response.data.message) ||
                error.message ||
                error.toString();
            }
          );
        }
      });
    }
  }
};
</script>

<style scoped>
*{
  font-family: serif;
}
body {
  background: #fdfdfd;
}

.card-img-left {
  width: 45%;
  background: scroll center
    url("https://images.unsplash.com/photo-1590487988152-68aca60adba1?ixlib=rb-1.2.1&ixid=MnwxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8&auto=format&fit=crop&w=735&q=80");
  background-size: cover;
}

.btn-login {
  font-size: 0.9rem;
  letter-spacing: 0.05rem;
  padding: 0.75rem 1rem;
}

.d-block {
  color: black;
}
</style>