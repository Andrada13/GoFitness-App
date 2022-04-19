<template>

    <div class="container page-top">
      <div class="row">
        <div class="col-lg-3 col-md-4 col-xs-6 thumb">
          <img
            src="https://images.unsplash.com/photo-1574755851171-8959b8334d6e?ixlib=rb-1.2.1&ixid=MnwxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8&auto=format&fit=crop&w=1469&q=80"
            class="zoom img-fluid"
            alt=""
          />
        </div>
        <div class="col-lg-3 col-md-4 col-xs-6 thumb">
          <img
            src="https://images.unsplash.com/photo-1597076545399-91a3ff0e71b3?ixlib=rb-1.2.1&ixid=MnwxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8&auto=format&fit=crop&w=2070&q=80"
            class="zoom img-fluid"
            alt=""
          />
        </div>

        <div class="col-lg-3 col-md-4 col-xs-6 thumb">
          <img
            src="https://images.unsplash.com/photo-1599058917212-d750089bc07e?ixlib=rb-1.2.1&ixid=MnwxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8&auto=format&fit=crop&w=1469&q=80"
            class="zoom img-fluid"
            alt=""
          />
        </div>

        <div class="col-lg-3 col-md-4 col-xs-6 thumb">
          <img
            src="https://images.unsplash.com/photo-1596357395217-80de13130e92?ixlib=rb-1.2.1&ixid=MnwxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8&auto=format&fit=crop&w=1471&q=80"
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
                <h6 class="font-weight-medium">{{ trainer.fullName }}</h6>
                <p class="mt-3">{{ trainer.description }}</p>
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
            src="https://images.unsplash.com/photo-1580051745101-2dca6e53f15c?ixlib=rb-1.2.1&ixid=MnwxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8&auto=format&fit=crop&w=1469&q=80"
            class="zoom img-fluid"
            alt=""
          />
        </div>

        <div class="col-lg-3 col-md-4 col-xs-6 thumb">
          <img
            src="https://images.unsplash.com/photo-1558611848-73f7eb4001a1?ixlib=rb-1.2.1&ixid=MnwxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8&auto=format&fit=crop&w=1471&q=80"
            class="zoom img-fluid"
            alt=""
          />
        </div>

        <div class="col-lg-3 col-md-4 col-xs-6 thumb">
          <img
            src="https://images.unsplash.com/photo-1517836357463-d25dfeac3438?ixlib=rb-1.2.1&ixid=MnwxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8&auto=format&fit=crop&w=1470&q=80"
            class="zoom img-fluid"
            alt=""
          />
        </div>

        <div class="col-lg-3 col-md-4 col-xs-6 thumb">
          <img
            src="https://images.unsplash.com/photo-1534438327276-14e5300c3a48?ixlib=rb-1.2.1&ixid=MnwxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8&auto=format&fit=crop&w=1470&q=80"
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
  font-family: serif;
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
  color: #121314;
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


.thumb {
  margin-bottom: 30px;
}

.page-top {
  margin-top: 85px;
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


