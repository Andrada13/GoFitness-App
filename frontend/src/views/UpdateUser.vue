
<template>

  <div>
    <br>
<br>
  <!--  <div class="container center_div">-->
      <div class="col-md-12">
    <div class="card card-container">
     <h2 align="center">Update user</h2>

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
          <label>ID user :</label>
          <input type="text" class="form-control" v-model="id" disabled />
        </fieldset>
        <fieldset class="form-group">
          <label>Role :</label>
          <input type="text" class="form-control" v-model="role" disabled />
        </fieldset>

        <fieldset class="form-group">
          <label>Username :</label>
          <input type="text" class="form-control" v-model="username" />
        </fieldset>
        <fieldset class="form-group">
          <label>Email :</label>
          <input type="text" class="form-control" v-model="email" />
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
import User from "../services/get-users";
export default {
  name: "updateUser",
  data() {
    return {
      username: "",
      email: "",
      role:"",
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
        this.username = result.data.username;
        this.email = result.data.email;
        this.role= result.data.roles[0].name;    
        console.log(result);
    });
    },
    validateAndSubmit(e) {
      e.preventDefault();
      this.errors = [];
      if (!this.username) {
        this.errors.push("Enter valid username");
      } else if (this.username.length < 5) {
        this.errors.push("Username should be longer");
      }

      if (this.errors.length === 0) {
        User.updateUser(this.id, {
          id: this.id,
          username: this.username,
          email: this.email,
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
