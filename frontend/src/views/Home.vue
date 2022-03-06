
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


    <div class="py-5 service-26" >
    <div class="container" >
        <div class="row wrap-service-26" >
        </div>
        <div class="row wrap-service-26 mt-4 pt-3">
            <div class="col-md-6"> 
							<img src="https://images.unsplash.com/photo-1506126613408-eca07ce68773?ixlib=rb-1.2.1&ixid=MnwxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8&auto=format&fit=crop&w=799&q=80" class="img-fluid-2" /> 
						</div>
            <div class="col-md-6 align-self-center">
                <h3 class="mt-3">asdhja</h3>
                <p class="mt-3">You can relay on our amazing features list and also our customer services will be greatexperience for you without doubt and in no-time and with great quality for design.</p>
                <p>We guarantee you with our WrapKit that you will make your site in record time and it will be fun to work on.</p>
            </div>
        </div>
<br>
        <div class="row wrap-service-26">
            <div class="col-md-7 align-self-center">
                <div class="max-box"> 
                    <h3 class="mt-3">MUSCLE BUILDING CLASS </h3>
                    <p class="mt-3">You can relay on our amazing features list and also our customer services will be greatexperience for you without doubt and in no-time and with great quality for design.</p>
                    <p>We guarantee you with our WrapKit that you will make your site in record time and it will be fun to work on.</p>
                </div>
            </div>
            <div class="col-md-5 col-md-5"> 
				<img src="https://images.unsplash.com/photo-1637430308606-86576d8fef3c?ixlib=rb-1.2.1&ixid=MnwxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8&auto=format&fit=crop&w=687&q=80" class="img-fluid" /> 
			</div>
        </div>



<br><br>

 <div class="row wrap-service-26 mt-4 pt-3">
            <div class="col-md-6"> 
				<img src="https://images.unsplash.com/photo-1551529674-48920e9b835b?ixlib=rb-1.2.1&ixid=MnwxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8&auto=format&fit=crop&w=2194&q=80" class="img-fluid-6" /> 
			</div>
            <div class="col-md-6 align-self-center">
                <h3 class="mt-3">LIFESTYLE CLASS</h3>
                <p class="mt-3">You can relay on our amazing features list and also our customer services will be greatexperience for you without doubt and in no-time and with great quality for design.</p>
                <p>We guarantee you with our WrapKit that you will make your site in record time and it will be fun to work on.</p>
            </div>
        </div>


<br>
<br>
      <div class="col-md-12 mt-3 text-center">
          <button type="button" class="btn btn-secondary btn-lg">Make Online Appointment</button>

      </div>    
    </div>


</div>
</template>
  

<script>
import UserService from "../services/user.service";

export default {
  name: "Home",
  data() {
    return {


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

<style scoped>


<style scoped>

*{
background-color: black;
margin: 0 auto;


}

.img-fluid{
    width: 550px;
    height: 700px;
}

.img-fluid-1{
    width: 500px;
    height: 650px;
}
.img-fluid-2{
    width: 550px;
    height: 600px;
}

.img-fluid-6{
    width: 630px;
    height: 530px;
}

.service-26 {
  font-family: "Montserrat", sans-serif;
	color: #8d97ad;
  font-weight: 300;
}

.service-26 h1, .service-26 h2, .service-26 h3, .service-26 h4, .service-26 h5, .service-26 h6 {
  color: #3e4555;
}

.service-26 .font-weight-medium {
	font-weight: 500;
}

.service-26 .badge {
	line-height: 14px;
}


.service-26 .wrap-service-26 .max-box {
  max-width: 580px;
}

.service-26 a {
	text-decoration: none;
}




</style>