
<template>

  <div>
    <br>
    <br><br><br>
<br>
<h2 align="center">Editeaza profil utilizator</h2>
   <div class="container center_div">
      <div class="col-md-12">
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
        <fieldset class="form-group">
          <label>ID utilizator :</label>
          <input type="text" class="form-control" v-model="id" disabled />
        </fieldset>
        <fieldset class="form-group">
          <label>Rol :</label>
          <input type="text" class="form-control" v-model="role" disabled />
        </fieldset>

        <fieldset class="form-group">
          <label>Nume si prenume :</label>
          <input type="text" class="form-control" v-model="fullName" />
        </fieldset>
        <fieldset class="form-group">
          <label>Nume de utilizator :</label>
          <input type="text" class="form-control" name="username" v-model="username" />
        </fieldset>
        <fieldset class="form-group">
          <label>Email :</label>
          <input type="text" class="form-control" v-model="email" />
        </fieldset>
         <fieldset class="form-group">
          <label>Adresa :</label>
          <input type="text" class="form-control" v-model="address" />
        </fieldset>
         <fieldset class="form-group">
          <label>Numar de telefon :</label>
          <input type="text" class="form-control" v-model="phoneNumber" />
        </fieldset>
        <br>
        <div class="form-group text-center">
            <button class="btn btn-dark w-100" type="submit">Salveaza</button>
          </div>
      </form>
    </div>
  </div>
   </div>
  </div>
</template>

<script>
import User from "../services/get-users";
export default {
  name: "updateUser",
  data() {
    return {
      username: "",
      email: "",
      fullName: "",
      address: "",
      phoneNumber: "",
      role:"",
      errors:[]
    };
  },
  computed: {
    id() {
      return this.$route.params.id;
    },
  },

  methods: {
    UpdateUser() {
      User.getUser(this.id).then((result) => {
        this.fullName = result.data.fullName;
        this.username = result.data.username;
        this.email = result.data.email;
        this.address = result.data.address;
        this.phoneNumber = result.data.phoneNumber;
        this.role= result.data.roles[0].name;    
    });
    },
    validateAndSubmit(e) {
      e.preventDefault();
      this.errors = [];
      if (!this.username) {
        this.errors.push("Introduceti un username.");
      } else if (this.username.length < 3) {
        this.errors.push("Numele de utilizator trebuie sa contina minim 3 caractere.");
      }else if (!this.fullName) {
        this.errors.push("Introduceti numele si prenumele.");
      }else if (!this.address) {
        this.errors.push("Introduceti adresa.");
      }else if (!this.email) {
        this.errors.push("Introduceti email.");
      }else if (!this.phoneNumber) {
        this.errors.push("Introduceti numar de telefon.");
      }

      if (this.errors.length === 0) {
        User.updateUser(this.id, {
          id: this.id,
          fullName :this.fullName,
          username: this.username,
          email: this.email,
          address : this.address,
          phoneNumber: this.phoneNumber,
          role:this.role,
        }).then(() => {
          this.$router.push("/admin");
        });
      }
    },
  },
  created() {
    this.UpdateUser();
  },
};
</script>

<style scoped>

*{
 
  font-family: serif;
} 

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

  background-color: #ffffff;
  padding: 20px 25px 30px 30px;
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
