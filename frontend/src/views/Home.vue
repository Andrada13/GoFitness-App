<template>
<!--
  <div class="container">
    <header class="jumbotron">
      <h3>{{ content }}</h3>
    </header>
    <a type="button" href="/register" class="btn btn-lg btn-dark"
      >Get Started</a
    >
  </div>
  -->
  <div class="circular"
       :style="{ backgroundImage: 'url(' + image + ')' }">

</template>

<script>
import UserService from "../services/user.service";
export default {
  name: "Home",
  data() {
    return {
      content: "",
         	image: "http://1.bp.blogspot.com/-8PfnHfgrH4I/TylX2v8pTMI/AAAAAAAAJJ4/TICBoSEI57o/s1600/search_by_image_image.png"

    };
  },
  mounted() {
    UserService.getPublicContent().then(
      (response) => {
        this.content = response.data;
      },
      (error) => {
        this.content =
          (error.response &&
            error.response.data &&
            error.response.data.message) ||
          error.message ||
          error.toString();
      }
    );
  },
};
</script>
<style>
.btn {
  padding: 15px 40px;
  font-size: 19px;
}
.circular{
  width: 90px;
  height: 90px;
  background-size: cover;
  border-radius: 50px;
  -webkit-border-radius: 50px;
  -moz-border-radius: 50px;
}

</style>