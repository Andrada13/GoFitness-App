<template>

  <div class="container">
    <header class="jumbotron">
<h3>{{content}}</h3>
    </header>

    <a type="button" href="/register" class="btn btn-lg btn-dark">Get Started</a>

  </div>
</template>

<script>
import UserService from '../services/user.service';
export default {
  name: 'Home',
  data() {
    return {
      content:"",
    };
  },
  mounted() {
    UserService.getPublicContent().then(
      response => {
        this.content = response.data;
      },
      error => {
        this.content =
          (error.response && error.response.data && error.response.data.message) ||
          error.message ||
          error.toString();
      }
    );
  }
};
</script>
<style scoped>
.btn{
  padding: 15px 40px;
  font-size: 19px;
}
</style>