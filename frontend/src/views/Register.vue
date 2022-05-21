<template>
  <body>
    <br><br><br>
        <div class="col-lg-10 col-xl-9 mx-auto">
          <div
            class="card flex-row my-5 border-0 shadow rounded-3 overflow-hidden"
          >
            <div class="card-img-left d-none d-md-flex">
            </div>
            <div class="card-body p-4 p-sm-4">
              <h6 class="card-title text-center mb-5 fw-light fs-5">
                Creeaza cont
              </h6>

              <form name="form" @submit.prevent="handleRegister">
                <div v-if="!successful">


                <div class="form-floating mb-3">
                    <input
                      v-model="user.fullName"
                      v-validate="'required|max:50'"
                      type="text"
                      class="form-control"
                      id="floatingInputFullName"
                      placeholder="fullName"
                      name="fullName"
                      required
                      autofocus
                    />
                    <label for="floatingInputFullName">Nume si prenume :</label>

                  </div>



                  <div class="form-floating mb-3">

                    <input
                      v-model="user.username"
                      v-validate="'required|min:3|max:20'"
                      data-vv-validate-on="username" 
                      type="text"
                      class="form-control"
                      id="floatingInputUsername"
                      placeholder="username"
                      name="username"
                      required
                      autofocus
                    />
                <label for="floatingInputUsername">Nume de utilizator :</label>

                    <small>
                      <div
                        v-if="submitted && errors.has('username') && errors.collect('username')"
                        class="alert-danger"
                      >
                        {{ "Numele de utilizator trebuie sa contina minim 3 si maxim 20 caractere."}}
                      </div></small
                    >
                  </div>

                  <div class="form-floating mb-3">
                    <input
                      v-model="user.email"
                      v-validate="'required|email'"
                      type="email"
                      class="form-control"
                      id="floatingInputEmail"
                      placeholder="email"
                      name="email"
                    />
                    <label for="floatingInputEmail">Adresa de email :</label>

                    <small>
                      <div
                        v-if="submitted && errors.has('email')"
                        class="alert-danger"
                      >
                        {{ "Introduceti o adresa de email valida." }}
                      </div></small
                    >
                  </div>

                  <div class="form-floating mb-3">
                    <input
                      v-model="user.password"
                      v-validate="'required|min:6'"
                      type="password"
                      class="form-control"
                      id="floatingPassword"
                      placeholder="Password"
                      name="password"
                    />
                    <label for="floatingPassword">Parola :</label>

                    <small>
                      <div
                        v-if="submitted && errors.has('password')"
                        class="alert-danger"
                      >
                        {{ "Parola trebuie sa contina minim 6 caractere." }}
                      </div></small
                    >
                  </div>

                <div class="form-floating mb-3">
                    <input
                      v-model="user.phoneNumber"
                      v-validate="'digits:10'" type="text"
                      class="form-control"
                      id="phoneNumber"
                      placeholder="phoneNumber"
                      name="phoneNumber"
                      required
                      autofocus
                    />
                     

                    <label for="phoneNumber">Numar de telefon :</label>


                    <small>
                      <div
                        v-if="submitted && errors.has('phoneNumber')"
                        class="alert-danger"
                      >
                        {{ "Introduceti un numar de telefon valid." }}
                      </div></small
                    >
                  </div>

                  <div class="form-floating mb-3">
                    <input
                      v-model="user.address"
                      v-validate="'required|max:70'"
                      type="text"
                      class="form-control"
                      id="floatingInputAddress"
                      placeholder="address"
                      required
                      autofocus
                    />
                    <label for="floatingInputAddress">Adresa :</label>

                   
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
                      Inregistrare
                    </button>
                  </div>

                  <br />
                  <a class="d-block text-center mt-2 small" href="/login"
                    >Aveti un cont? Conectati-va aici</a
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
  </body>
</template>

<script>
import User from "../models/user";

export default {
  name: "Register",
  data() {
    return {
      user: new User("", "", "","","",""),
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
            error => {
              this.message =
                (error.response &&
                  error.response.data ) ||
                //  error.response.data.message ||
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

*{
  font-family: serif;
}

.card-img-left {
  width: 45%;
    background-size: cover;


  background: scroll center
    url("https://images.unsplash.com/photo-1620188467120-5042ed1eb5da?ixlib=rb-1.2.1&ixid=MnwxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8&auto=format&fit=crop&w=687&q=80");
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