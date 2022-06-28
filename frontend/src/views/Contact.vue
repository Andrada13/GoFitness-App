<template>
  <div class="contact3 py-5">
  
      <div class="container">
        <div class="row">
          <div class="col-lg-5">
            <br /><br /> <br>
            <div class="card-shadow">
              <img
                src="https://images.unsplash.com/photo-1586810165616-94c631fc2f79?ixlib=rb-1.2.1&ixid=MnwxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8&auto=format&fit=crop&w=1470&q=80"
                class="img-fluid"
              />
            </div>
          </div>
          <div class="col-lg-6">
            <div class="contact-box ml-3">
              <br />
              <br />
              <br>
              <h2 class="mt-2">Trimite un mesaj</h2>
              <br />
              <form class="mt-4" @submit.prevent="handleMessages">
                <div v-if="!successful">
                  <div class="row">
                    <div class="col-lg-10">
                      <div class="form-group mt-2">
                        <input
                          v-model="messages.name"
                          class="form-control"
                          type="text"
                          placeholder="Nume :"
                        />
                      </div>
                    </div>
                    <div class="col-lg-10">
                      <div class="form-group mt-2">
                        <input
                          v-model="messages.email"
                          class="form-control"
                          type="email"
                          placeholder="Adresa de email :"
                        />
                      </div>
                    </div>
                    <div class="col-lg-10">
                      <div class="form-group mt-2">
                        <input
                          v-model="messages.subject"
                          class="form-control"
                          type="text"
                          placeholder="Subiect :"
                        />
                      </div>
                    </div>
                    <div class="col-lg-10">
                      <div class="form-group mt-2">
                        <textarea
                          v-model="messages.message"
                          class="form-control"
                          rows="3"
                          placeholder="Mesaj :"
                        ></textarea>
                      </div>
                    </div>
                    <div class="col-lg-10">
                      <button
                        type="submit"
                        class="btn btn-dark mt-3 text-white border-0 px-3 py-2 w-100"
                      >
                        <span> TRIMITE </span>
                      </button>
                    </div>
                  </div>
                </div>
              </form>
              <div
                v-if="message"
                class="alert"
                :class="successful ? 'alert-dark' : 'alert-danger'"
              >
                {{ message }}
              </div>
            </div>
            <br />
            <br />
            <br /><br /><br /><br />
          </div>
          <div class="col-lg-12">
            <div class="card mt-4 border-0 mb-4">
              <div class="row">
                <div class="col-lg-4 col-md-4">
                  <div
                    class="card-body d-flex align-items-center c-detail pl-0"
                  >
                    <div class="mr-3 align-self-center">
                      <img
                        src="https://cdn-icons-png.flaticon.com/32/484/484167.png"
                      />
                    </div>
                    <div class="">
                      <h6>Adresa :</h6>
                      <p>
                        Calea Bucuresti 125 <br />
                        Craiova
                      </p>
                    </div>
                  </div>
                </div>
                <div class="col-lg-4 col-md-4">
                  <div class="card-body d-flex align-items-center c-detail">
                    <div class="mr-3 align-self-center">
                      <img
                        src="https://cdn-icons-png.flaticon.com/32/483/483947.png"
                      />
                    </div>
                    <div class="">
                      <h6>Telefon :</h6>
                      <p>
                        +(40) 753627389 <br />
                        +(40) 648264829
                      </p>
                    </div>
                  </div>
                </div>
                <div class="col-lg-4 col-md-4">
                  <div class="card-body d-flex align-items-center c-detail">
                    <div class="mr-3 align-self-center">
                      <img
                        src="https://cdn-icons-png.flaticon.com/32/725/725643.png"
                      />
                    </div>
                    <div class="">
                      <h6>Email :</h6>
                      <p>
                        info@fitness.com
                        <br />
                        gym@fitness.com
                      </p>
                    </div>
                  </div>
                </div>
              </div>
            </div>
          </div>
        </div>
      </div>
 
  </div>
</template>

<script>
import Message from "../models/message";

export default {
  name: "Contact",
  data() {
    return {
      msg: "Contact us",
      messages: new Message("", "", "", ""),
      submitted: false,
      successful: false,
      message: "",
    };
  },
  methods: {
    handleMessages() {
      this.message = "";
      this.submitted = true;
      this.$validator.validate().then((isValid) => {
        if (isValid) {
          this.$store.dispatch("auth/addMessage", this.messages).then(
            (data) => {
              this.message = data.message;
              this.successful = true;
              this.$router.push("/contact");
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
.contact3 {
  font-family: serif;
  color: #8d97ad;
  font-weight: 300;
}

.contact3 h1,
.contact3 h2,
.contact3 h6 {
  color: #1a1a1b;
}

.contact3 .font-weight-medium {
  font-weight: 500;
}

.contact3 .card-shadow {
  -webkit-box-shadow: 0px 0px 30px rgba(115, 128, 157, 0.1);
  box-shadow: 0px 0px 30px rgba(115, 128, 157, 0.1);
}

.img-fluid {
  height: 450px;
  width: 720px;
}
</style>
