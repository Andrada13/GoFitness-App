<!--
<template>
  <div class="col-md-12">
    <div class="card card-container">
       <h2 align= "center">Login</h2>
        <p class="mt-2 text-center text-sm text-gray-600">
          Or
          {{ ' ' }}
          <a href="/register" class="font-medium text-black-600 hover:text-black-500">
            create a new account
          </a>
        </p>
      <form name="form" @submit.prevent="handleLogin">
        <div class="form-group">
          <label for="username">Username :</label>
          <input
            v-model="user.username"
            v-validate="'required'"
            type="text"
            class="form-control"
            name="username"
          />
         <small> <div
            v-if="errors.has('username')"
            class="alert alert-danger"
            role="alert"
          >Username is required!</div></small>
        </div>
        <div class="form-group">
          <label for="password">Password :</label>
          <input
            v-model="user.password"
            v-validate="'required'"
            type="password"
            class="form-control"
            name="password"
          />
         <small> <div
            v-if="errors.has('password')"
            class="alert alert-danger"
            role="alert"
          >Password is required!</div></small>
        </div>
        <br>
        <div class="form-group text-center">
          <button class="btn btn-dark btn-block" :disabled="loading">
            <span v-show="loading" class="spinner-border spinner-border-sm"></span>
            <span>Login</span>
          </button>
        </div>
        <div class="form-group">
          <div v-if="message" class="alert alert-danger" role="alert">{{message}}</div>
        </div>
      </form>
    </div>
  </div>
</template>

<script>
import User from '../models/user';
export default {
  name: 'Login',
  data() {
    return {
      user: new User('', ''),
      loading: false,
      message: ''
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
      this.loading = true;
      this.$validator.validateAll().then(isValid => {
        if (!isValid) {
          this.loading = false;
          return;
        }
        if (this.user.username && this.user.password) {
          this.$store.dispatch('auth/login', this.user).then( //Actions are called in the components with the method dispatch()
            () => {
              this.$router.push('/profile');
            },
            error => {
              this.loading = false;
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

label {
  display: block;
  margin-top: 20px;
}
.card-container.card {
  max-width: 350px !important;
  padding: 40px 40px;
}
.card {
  background-color: #fcfbf6;
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
                Login
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
                      Login
                    </button>

                  </div>

                  <br />
                  <a class="d-block text-center mt-2 small" href="/register"
                    >Don't have an account? Register</a
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
      message: ''
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
              this.$router.push('/profile');
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
body {
  background: #feffff;
}

.card-img-left {
  width: 45%;
  /* Link to your background image using in the property below! */
  background: scroll center
    url("https://images.unsplash.com/photo-1571902943202-507ec2618e8f?ixlib=rb-1.2.1&ixid=MnwxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8&auto=format&fit=crop&w=1375&q=80");
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