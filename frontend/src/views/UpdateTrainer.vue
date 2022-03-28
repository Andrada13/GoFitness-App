
<template>

  <div>
    <br>
<br>
      <div class="col-md-12">
    <div class="card card-container">
     <h2 align="center">Update trainer</h2>

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
          <label>ID trainer :</label>
          <input type="text" class="form-control" v-model="id" disabled />
        </fieldset>
        <fieldset class="form-group">
          <label>Name :</label>
          <input type="text" class="form-control" v-model="name" />
        </fieldset>

        <fieldset class="form-group">
          <label>Email :</label>
          <input type="text" class="form-control" v-model="email" />
        </fieldset>
        <fieldset class="form-group">
          <label>Type :</label>
          <input type="text" class="form-control" v-model="type" />
        </fieldset>
        <br>
        <div class="form-group text-center">
            <button class="btn btn-dark btn-block" type="submit">Save</button>
          </div>

      </form>
    </div>
  </div>
  </div>
</template>

<script>
import Trainer from "../services/get-trainers";
export default {
  name: "updateTrainer",
  data() {
    return {
      name: "",
      email: "",
      type:"",
      errors:[],
    };
  },
  computed: {
    id() {
      return this.$route.params.id;
    },
  },

  methods: {
    UpdateTrainer() {
      Trainer.getTrainer(this.id).then((result) => {
        this.name = result.data.name;
        this.email = result.data.email;
        this.type= result.data.type;    
        console.log(result);
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
        Trainer.updateTrainer(this.id, {
          id: this.id,
          name: this.name,
          email: this.email,
          type:this.type,
        }).then(() => {
          this.$router.push("/trainers");
        });
      }
    },
  },
  created() {
    this.UpdateTrainer();
  },
};
</script>

<style scoped>

.center_div{
    margin: 0 auto;
    width:80% 
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
