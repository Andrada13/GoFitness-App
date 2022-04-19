
<template>
  <div>
    <br />
    <br />
    <div class="col-md-12">
      <div class="card card-container">
        <h2 align="center">Editeaza curs</h2>

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
          <fieldset class="form-group">
            <label>ID curs :</label>
            <input type="text" class="form-control" v-model="id" disabled />
          </fieldset>
          <fieldset class="form-group">
            <label>Nume :</label>
            <input type="text" class="form-control" v-model="name" />
          </fieldset>

          <fieldset class="form-group">
            <label>Descriere :</label>
            <input type="text" class="form-control" v-model="description" />
          </fieldset>

           <fieldset class="form-group">
            <label>Pret :</label>
            <input type="text" class="form-control" v-model="price" />
          </fieldset>

          <br />
          <div class="form-group text-center">
            <button class="btn btn-dark btn-block" type="submit">Save</button>
          </div>
        </form>
      </div>
    </div>
  </div>
</template>

<script>
import Program from "../services/get-programs";
export default {
  name: "updateProgram",
  data() {
    return {
      name: "",
      description: "",
      price : 0,
      errors: [],
    };
  },
  computed: {
    id() {
      return this.$route.params.id;
    },
  },

  methods: {
    UpdateProgram() {
      Program.getProgram(this.id).then((result) => {
        this.name = result.data.name;
        this.description = result.data.description;
        this.price = result.data.price;
      });
    },

    
    validateAndSubmit(e) {
      e.preventDefault();
      this.errors = [];
      if (!this.name) {
        this.errors.push("Enter valid username");
      } else if (this.name.length < 2) {
        this.errors.push("Numele trebuie sa fie mai lung");
      }

      if (this.errors.length === 0) {
        Program.updateProgram(this.id, {
          id: this.id,
          name: this.name,
          description: this.description,
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
  margin: 0 auto;
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
