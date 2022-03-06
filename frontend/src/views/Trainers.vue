<template>

    <div class="container page-top">
      <div class="row">
        <div class="col-lg-3 col-md-4 col-xs-6 thumb">
          <img
            src="https://images.pexels.com/photos/62307/air-bubbles-diving-underwater-blow-62307.jpeg?auto=compress&cs=tinysrgb&h=650&w=940"
            class="zoom img-fluid"
            alt=""
          />
        </div>
        <div class="col-lg-3 col-md-4 col-xs-6 thumb">
          <img
            src="https://images.pexels.com/photos/38238/maldives-ile-beach-sun-38238.jpeg?auto=compress&cs=tinysrgb&h=650&w=940"
            class="zoom img-fluid"
            alt=""
          />
        </div>

        <div class="col-lg-3 col-md-4 col-xs-6 thumb">
          <img
            src="https://images.pexels.com/photos/158827/field-corn-air-frisch-158827.jpeg?auto=compress&cs=tinysrgb&dpr=2&h=650&w=940"
            class="zoom img-fluid"
            alt=""
          />
        </div>

        <div class="col-lg-3 col-md-4 col-xs-6 thumb">
          <img
            src="https://images.pexels.com/photos/302804/pexels-photo-302804.jpeg?auto=compress&cs=tinysrgb&dpr=2&h=650&w=940"
            class="zoom img-fluid"
            alt=""
          />
        </div>

  <div class="bg-light py-5 service-3">
    <div class="container">
      <!-- Row  -->
      <div class="row">
        <!-- Column -->

        <div
          class="col-md-6 wrap-service3-box"
          v-for="trainer in trainers"
          v-bind:key="trainer.id"
        >
          <div class="card border-0 card-shadow mb-4">
            <div class="card-body d-flex">
              <div class="align-self-center">
                <h6 class="font-weight-medium">{{ trainer.name }}</h6>
                <p class="mt-3">{{ trainer.type }}</p>
              </div>
            </div>
          </div>
        </div>
        <!-- Column -->
                      <div class="col-md-12 mt-3 text-center">
          <a
            href="/programs/onlineClasses"
            class="btn btn-dark text-white border-0 btn-md"
            ><span>Vizualizeaza antrenamentele</span></a
          >
        </div>

      </div>
    </div>

  </div>

      </div>
          <div class="container page-top">
      <div class="row">


        <div class="col-lg-3 col-md-4 col-xs-6 thumb">

          <img
            src="https://images.pexels.com/photos/1038914/pexels-photo-1038914.jpeg?auto=compress&cs=tinysrgb&h=650&w=940"
            class="zoom img-fluid"
            alt=""
          />
        </div>

        <div class="col-lg-3 col-md-4 col-xs-6 thumb">
          <img
            src="https://images.pexels.com/photos/414645/pexels-photo-414645.jpeg?auto=compress&cs=tinysrgb&h=650&w=940"
            class="zoom img-fluid"
            alt=""
          />
        </div>

        <div class="col-lg-3 col-md-4 col-xs-6 thumb">
          <img
            src="https://images.pexels.com/photos/56005/fiji-beach-sand-palm-trees-56005.jpeg?auto=compress&cs=tinysrgb&dpr=2&h=650&w=940"
            class="zoom img-fluid"
            alt=""
          />
        </div>

        <div class="col-lg-3 col-md-4 col-xs-6 thumb">
          <img
            src="https://images.pexels.com/photos/1038002/pexels-photo-1038002.jpeg?auto=compress&cs=tinysrgb&dpr=2&h=650&w=940"
            class="zoom img-fluid"
            alt=""
          />
        </div>
      </div>
    </div>


  </div>
</template>

<style scoped>
.service-3 {
  font-family: "Montserrat", sans-serif;
  color: #8d97ad;
  font-weight: 300;
}

.service-3 h1,
.service-3 h2,
.service-3 h3,
.service-3 h4,
.service-3 h5,
.service-3 h6 {
  color: #3e4555;
}

.service-3 .font-weight-medium {
  font-weight: 500;
}

.service-3 .bg-light {
  background-color: #f4f8fa !important;
}

.service-3 .subtitle {
  color: #8d97ad;
  line-height: 24px;
}

.service-3 .linking {
  color: #3e4555;
}

.service-3 a {
  text-decoration: none;
}

.service-3 .card.card-shadow {
  -webkit-box-shadow: 0px 0px 30px rgba(115, 128, 157, 0.1);
  box-shadow: 0px 0px 30px rgba(115, 128, 157, 0.1);
}

.service-3 .wrap-service3-box .card-body {
  padding: 40px;
}

.service-3 .btn-md {
  padding: 15px 45px;
  font-size: 16px;
}

body {
  background-color: #1d1d1d !important;
  font-family: "Asap", sans-serif;
  color: #989898;
  margin: 10px;
  font-size: 16px;
}

#demo {
  height: 100%;
  position: relative;
  overflow: hidden;
}

.green {
  background-color: #6fb936;
}
.thumb {
  margin-bottom: 30px;
}

.page-top {
  margin-top: 85px;
}

img.zoom {
  width: 100%;
  height: 200px;
  border-radius: 5px;
  object-fit: cover;
  -webkit-transition: all 0.3s ease-in-out;
  -moz-transition: all 0.3s ease-in-out;
  -o-transition: all 0.3s ease-in-out;
  -ms-transition: all 0.3s ease-in-out;
}

.transition {
  -webkit-transform: scale(1.2);
  -moz-transform: scale(1.2);
  -o-transform: scale(1.2);
  transform: scale(1.2);
}
.modal-header {
  border-bottom: none;
}
.modal-title {
  color: #000;
}
.modal-footer {
  display: none;
}
</style>


<script>
import Trainer from "../services/get-trainers";

export default {
  name: "Trainers",
  data() {
    return {
      trainers: [],
    };
  },

  methods: {
    getAllTrainers() {
      Trainer.getTrainers().then((response) => {
        this.trainers = response.data;
      });
    },
  },
  created() {
    this.getAllTrainers();
  },
};
</script>


