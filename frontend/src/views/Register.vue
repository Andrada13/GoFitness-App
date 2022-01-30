<!--
<template>

  <div class="col-md-12">
    <div class="card card-container">
     <h2 align="center">Register</h2>

     <p class="mt-2 text-center text-sm text-gray-600">
          Or
          {{ ' ' }}
          <a href="/login" class="font-medium text-black-600 hover:text-black-500">
            login into your account
          </a>
          <br>
        </p>
      
      <form name="form" @submit.prevent="handleRegister">
        <div v-if="!successful">
          <div class="form-group">
            <label for="username">Username :</label>
            <input
              v-model="user.username"
              v-validate="'required|min:3|max:20'"
              type="text"
              class="form-control"
              name="username"
            />
          <small>  <div
              v-if="submitted && errors.has('username')"
              class="alert-danger"
            >{{errors.first('username')}}</div></small>
          </div>
          <div class="form-group">
            <label for="email">Email :</label>
            <input
              v-model="user.email"
              v-validate="'required|email|max:50'"
              type="email"
              class="form-control"
              name="email"
            />
         <small>   <div
              v-if="submitted && errors.has('email')"
              class="alert-danger"
            >{{errors.first('email')}}</div></small>
          </div>
          <div class="form-group">
            <label for="password">Password :</label>
            <input
              v-model="user.password"
              v-validate="'required|min:6|max:40'"
              type="password"
              class="form-control"
              name="password"
            />
           <small> <div
              v-if="submitted && errors.has('password')"
              class="alert-danger"
            >{{errors.first('password')}}</div></small>
          </div>
          <br>
          <div class="form-group text-center">
            <button class="btn btn-dark btn-block">Sign Up</button>
          </div>
        </div>
      </form>

      <div
        v-if="message"
        class="alert"
        :class="successful ? 'alert-success' : 'alert-danger'"
      >{{message}}</div>
    </div>
  </div>
</template>

<script>
import User from '../models/user';
export default {
  name: 'Register',
  data() {
    return {
      user: new User('', '', ''),
      submitted: false,
      successful: false,
      message: ''
    };
  },
  computed: {
    loggedIn() {
      return this.$store.state.auth.status.loggedIn;
    }
  },
  mounted() {
    if (this.loggedIn) {
      this.$router.push('/profile');
    }
  },
  methods: {
    handleRegister() {
      this.message = '';
      this.submitted = true;
      this.$validator.validate().then(isValid => {
        if (isValid) {
          this.$store.dispatch('auth/register', this.user).then(
            data => {
              this.message = data.message;
              this.successful = true;
            },
            error => {
              this.message =
                (error.response && error.response.data && error.response.data.message) ||
                error.message ||
                error.toString();
              this.successful = false;
            }
          );
        }
      });
    }
  }
};
</script>

<style scoped>
label {
  display: block;
  margin-top: 10px;
}
.card-container.card {
  max-width: 350px !important;
  padding: 40px 40px;
}
.card {
  background-color: #f7f7f7;
  padding: 20px 25px 30px;
  margin: 0 auto 25px;
  margin-top: 50px;
  -moz-border-radius: 2px;
  -webkit-border-radius: 2px;
  border-radius: 2px;
  -moz-box-shadow: 0px 2px 2px rgba(0, 0, 0, 0.3);
  -webkit-box-shadow: 0px 2px 2px rgba(0, 0, 0, 0.3);
  box-shadow: 0px 2px 2px rgba(0, 0, 0, 0.3);
}
</style>
-->
<template>
  <body>
    <div class="container">
      <div class="row">
        <div class="col-lg-10 col-xl-9 mx-auto">
          <div
            class="card flex-row my-5 border-0 shadow rounded-3 overflow-hidden"
          >
            <div class="card-img-left d-none d-md-flex">
              <!-- Background image for card set in CSS! -->
            </div>
            <div class="card-body p-4 p-sm-6">
              <h6 class="card-title text-center mb-5 fw-light fs-5">
                Register
              </h6>

              <form name="form" @submit.prevent="handleRegister">
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
                    <label for="floatingInputUsername">Username :</label>

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
                      v-model="user.email"
                      v-validate="'required|email|max:50'"
                      type="email"
                      class="form-control"
                      id="floatingInputEmail"
                      placeholder="name@example.com"
                    />
                    <label for="floatingInputEmail">Email address :</label>

                    <small>
                      <div
                        v-if="submitted && errors.has('email')"
                        class="alert-danger"
                      >
                        {{ errors.first("email") }}
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
                    <label for="floatingPassword">Password :</label>

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
                      Register
                    </button>
                  </div>

                  <br />
                  <a class="d-block text-center mt-2 small" href="/login"
                    >Have an account? Sign In</a
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
import User from "../models/user";
export default {
  name: "Register",
  data() {
    return {
      user: new User("", "", ""),
      submitted: false,
      successful: false,
      message: "",
    };
  },
  computed: {
    loggedIn() {
      return this.$store.state.auth.status.loggedIn;
    },
  },
  mounted() {
    if (this.loggedIn) {
      this.$router.push("/profile");
    }
  },
  methods: {
    handleRegister() {
      this.message = "";
      this.submitted = true;
      this.$validator.validate().then((isValid) => {
        if (isValid) {
          this.$store.dispatch("auth/register", this.user).then(
            (data) => {
              this.message = data.message;
              this.successful = true;
            },
            (error) => {
              this.message =
                (error.response &&
                  error.response.data &&
                  error.response.data.message) ||
                error.message ||
                error.toString();
              this.successful = false;
            }
          );
        }
      });
    },
  },
};
</script>

<style scoped>
body {
  background: #feffff;
}

.card-img-left {
  width: 45%;
  /* Link to your background image using in the property below! */
  background: scroll center
    url("https://images.unsplash.com/photo-1590487988256-9ed24133863e?ixlib=rb-1.2.1&ixid=MnwxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8&auto=format&fit=crop&w=728&q=80");
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