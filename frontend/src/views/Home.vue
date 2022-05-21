
<template>
  <body>
    <br /><br /><br> <br>
    <!--
     <div class="title">
      <h3 class="mt-3">Calatoria ta spre un stil de viata mai sanatoas</h3>
    </div>
    -->
    <div class="py-5 service-26">
      <div class="container">
        <div class="row wrap-service-26"></div>
        <div class="row wrap-service-26 mt-4 pt-3">
          <div class="col-md-6">
            <img
              src="https://images.unsplash.com/photo-1506126613408-eca07ce68773?ixlib=rb-1.2.1&ixid=MnwxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8&auto=format&fit=crop&w=799&q=80"
              class="img-fluid-2"
            />
          </div>
          <div class="col-md-6 align-self-center">
            <h3 class="mt-3">STIL DE VIATA ACTIV</h3>
            <p class="mt-3">
              Oferim programe accesibile ce va vor ajuta sa aveti un stil de
              viata mai sanatos, sa pierdeti kilogramele nedorite, dar si sa va
              mentineti in forma.
            </p>
            <p>
              Promovam miscarea, sportul si alimentatia sanatoasa, iar impreuna
              cu antrenorii nostri putem sa va imbunatatim sanatatea prin
              antrenamentele online pe care le oferim, dar si prin abonamentele
              pe care puteti dispune daca ne alegeti.
            </p>
            <div class="col-md-12 mt-3 text-left">
              <a type="button" class="btn btn-dark" href="/register">
                INCEPETI ACUM
              </a>
            </div>
          </div>
        </div>
        <br />
        <div class="row wrap-service-26">
          <div class="col-md-7 align-self-center">
            <div class="max-box">
              <h3 class="mt-3">ANTRENAMENTE ONLINE</h3>
              <p class="mt-3">
                Aici puteti gasi cursuri organizate de
                catre cei mai profesionisti antrenori.
              </p>
              <p>
                Pentru mai multe detalii puteti vizita pagina destinata cursurilor :
              </p>
              <div class="col-md-12 mt-3 text-left">
                <a type="button" class="btn btn-dark" href="programs/onlineClasses">
                  ANTRENAMENTE
                </a>
              </div>
            </div>
          </div>
          <div class="col-md-5 col-md-5">
            <img
              src="https://images.unsplash.com/photo-1637430308606-86576d8fef3c?ixlib=rb-1.2.1&ixid=MnwxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8&auto=format&fit=crop&w=687&q=80"
              class="img-fluid"
            />
          </div>
        </div>

        <br /><br />

        <div class="row wrap-service-26 mt-4 pt-3">
          <div class="col-md-6">
            <img
              src="https://images.unsplash.com/photo-1580051745101-2dca6e53f15c?ixlib=rb-1.2.1&ixid=MnwxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8&auto=format&fit=crop&w=1469&q=80"
              class="img-fluid-6"
            />
          </div>
          <div class="col-md-6 align-self-center">
            <h3 class="mt-3">INTALNESTE ANTRENORII</h3>
            <p class="mt-3">
              Antreneaza-te alegand unul dintre cursurile noastre
              si vei fi sub indrumarea unui antrenor a carui experienta te va
              ajuta sa iti atingi mai repede si mai eficient scopul pe care ti l-ai propus.
            </p>
            <div class="col-md-12 mt-3 text-left">
              <a type="button" class="btn btn-dark" href="/trainersDetails">
                ANTRENORI
              </a>
            </div>
          </div>
        </div>

        <br />
        <br />
      </div>
    </div>
  </body>
</template>
  

<script>
import UserService from "../services/user.service";

export default {
  name: "Home",
  data() {
    return {};
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
*{
  background-color: black;
  font-family: serif;
}
.title h3 {
  font-family: serif;
  text-align: center;
  color: #3e4555;
}

.img-fluid {
  width: 550px;
  height: 700px;
  max-width: 100%;
}

.img-fluid-1 {
  width: 500px;
  height: 650px;
  max-width: 100%;
}
.img-fluid-2 {
  width: 550px;
  height: 600px;
  max-width: 100%;
}

.img-fluid-6 {
  width: 600px;
  height: 450px;
  max-width: 100%; /*responsive image*/
}

.service-26 {
  font-family: serif;
  color: #8d97ad;
  font-weight: 300;
  font-size: 100%; /* make paragraph responsive */
}
.service-26 p {
  font-size: 17px;
}
.service-26 h2,
.service-26 h3,
.service-26 h4,
.service-26 h5,
.service-26 h6 {
  color: #3e4555;
}


.service-26 .badge {
  line-height: 14px;
}


</style>