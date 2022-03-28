
<template>
  <div class="rezervare">
    <br /><br />
    <div class="col-md-12">
      <h2 align="center">Programare curs online</h2>

      <div class="card card-container">
        <form @submit="validateAndSubmit">
          <div v-if="errors.length">
            <div
              class="alert alert-warning"
              v-bind:key="index"
              v-for="(error, index) in errors"
            >
              {{ error }}
            </div>
          </div>

          <label><strong> Informatii utilizator : </strong></label>
          <label
            >Email : <i> {{ currentUser.email }} </i></label
          >

          <hr class="my-4" />

          <fieldset class="form-group">
            <label>Denumire :</label>
            <input type="text" class="form-control" v-model="name" disabled />
          </fieldset>

          <fieldset class="form-group">
            <label>Antrenor :</label>
            <input
              type="text"
              class="form-control"
              v-model="trainerName"
              disabled
            />
          </fieldset>
          <fieldset class="form-group">
            <label>Pret :</label>
            <input type="text" class="form-control" v-model="price" disabled />
          </fieldset>

          <fieldset class="form-group">
            <label>Program :</label>

            <select class="form-select">
              <option selected disabled>
                Alegeti ziua si ora rezervarii :
              </option>

              <option v-for="p in time_program" v-bind:key="p.programTime_id">
                {{ p.time_program }}
              </option>
            </select>
          </fieldset>

          <br />
          <div class="form-group text-center">
            <button class="btn btn-dark btn-block" type="submit">
              Rezervare
            </button>
          </div>
        </form>
      </div>
    </div>
  </div>
</template>

<script>
import Program from "../services/get-programs";

export default {
  name: "ClassAppointment",
  data() {
    return {
      name: "",
      date: "",
      trainerName: "",
      price: 0,
      time_program: "",
      timePrograms: [],
      content: "",
    };
  },
  computed: {
    id() {
      return this.$route.params.id;
    },
    currentUser() {
      return this.$store.state.auth.user;
    },
  },

  methods: {
    UpdateProgram() {
      Program.getProgram(this.id).then((result) => {
        this.name = result.data.name;
        this.trainerName = result.data.trainerName;
        this.price = result.data.price;
        this.time_program = result.data.programs;

        console.log(this.time_program);
      });
    },

    validateAndSubmit(e) {
      e.preventDefault();
      this.errors = [];
      if (!this.name) {
        this.errors.push("Enter valid username");
      } else if (this.name.length < 5) {
        this.errors.push("Name should be longer");
      }

      if (this.errors.length === 0) {
        Program.updateProgram(this.id, {
          id: this.id,
          name: this.name,
          trainerName: this.trainerName,
          price: this.price,
        }).then(() => {
          this.$router.push("/programs");
        });
      }
    },
  },
  created() {
    this.UpdateProgram();
  },
};
</script>

<style scoped>


.center_div {
 
  width: 80%;
}
label {
  display: block;
  margin-top: 10px;
}

.card-container.card {
  max-width: 350px !important;
  padding: 40px 40px;
}
.card {
  background-color: #ffffff;
  padding: 25px 25px 30px;
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
