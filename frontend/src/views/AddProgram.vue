
<template>
  <div class="col-md-12">
    <div class="card card-container">
     <h2 align="center">Add new program</h2>
      <form name="form" @submit.prevent="handleRegister">
        <div v-if="!successful">
          <div class="form-group">
            <label for="name">Name :</label>
            <input
              v-model="program.name"
              v-validate="'required|min:3|max:20'"
              type="text"
              class="form-control"
              name="name"
            />
          <small>  <div
              v-if="submitted && errors.has('name')"
              class="alert-danger"
            >{{errors.first('name')}}</div></small>
          </div>
          <div class="form-group">
            <label for="description">Description :</label>
            <input
              v-model="program.description"
              type="text"
              class="form-control"
              name="description"
            />
          </div>
          <div class="form-group">
            <label for="type">Trainer Name :</label>
            <input
              v-model="program.trainerName"
              v-validate="'required|min:6|max:40'"
              type="text"
              class="form-control"
              name="trainerName"
            />
           <small> <div
              v-if="submitted && errors.has('trainerName')"
              class="alert-danger"
            >{{errors.first('trainerName')}}</div></small>
          </div>
        </div>
        <div class="form-group">
            <label for="type">Price :</label>
            <input
              v-model="program.price"
              v-validate="'required|max:6'"
              type="text"
              class="form-control"
              name="price"
            />
           <small> <div
              v-if="submitted && errors.has('price')"
              class="alert-danger"
            >{{errors.first('price')}}</div></small>
          </div>
          <br>
          <div class="form-group text-center">
            <button class="btn btn-dark btn-block">Add program</button>
          </div>
      </form>

      <div
        v-if="message"
        class="alert"
        :class="successful ? 'alert alert-dark' : 'alert-danger'"
      >{{message}}</div>
    </div>
  </div>
</template>

<script>
import Program from '../models/program';

export default {
  name: 'AddProgram',
  data() {
    return {
      program: new Program('', '', '',''),
      submitted: false,
      successful: false,
      message: ''
    };
  },
    methods: {
    handleRegister() {
      this.message = '';
      this.submitted = true;
      this.$validator.validate().then(isValid => {
        if (isValid) {
          this.$store.dispatch('auth/addProgram', this.program).then(
            data => {
              this.message = data.message;
              this.successful = true;
             this.$router.push("/programs");

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
}
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
