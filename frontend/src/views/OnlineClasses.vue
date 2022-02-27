<template>
  <div class="py-5 service-12">
    <div class="container">
      <div class="row">
        <div class="col-lg-6">
          <br />
          <h4 class="my-3">Antrenamente Disponibile</h4>
          <br />
          <h6 class="font-weight-light subtitle">
            Puteti sa va bucurati de experienta antrenorilor nostri alegand un
            program specific dorintelor dumnevoastra :
          </h6>

          <hr class="my-4" />
          <br />
          <div class="row">
            <div
              class="col-md-6 mt-3"
              v-for="program in programs"
              v-bind:key="program.id"
            >
              <br />
              <br />
              <h5 class="font-weight-medium">{{ program.name }}</h5>
              <p>
                <i>{{ program.description }}</i>
              </p>
              <p>{{ program.trainerName }}</p>
            </div>
            <hr class="my-4" />
            <br />
            <br />
            <br />
            <br />
            <br />
            <div class="col-md-12 mt-3 text-center">
              <button type="button" class="btn btn-dark btn-lg">
                   Faceti o programare online
              </button>
            </div>
          </div>
        </div>
        <div class="col-lg-6">
          <div class="row wrap-service12">
            <div class="col-md-6">
              <div class="row">
                <div class="col-md-12 img-hover mb-4">
                  <img
                    v-bind:src="myImage"
                    class="rounded img-shadow img-fluid"
                  />
                </div>
                <div class="col-md-12 img-hover mb-4">
                  <img
                    v-bind:src="myImage1"
                    class="rounded img-shadow img-fluid"
                  />
                </div>
              </div>
            </div>
            <div class="col-md-6 uneven-box">
              <div class="row">
                <div class="col-md-12 img-hover mb-4">
                  <img
                    v-bind:src="myImage2"
                    class="rounded img-shadow img-fluid"
                  />
                </div>
                <div class="col-md-12 img-hover mb-4">
                  <img
                    v-bind:src="myImage3"
                    class="rounded img-shadow img-fluid"
                  />
                </div>
              </div>
            </div>
          </div>
        </div>
      </div>
    </div>
  </div>
</template>

<style scoped>
.service-12 {
  font-family: "Montserrat", sans-serif;
  color: #8d97ad;
  font-weight: 300;
}

.service-12 h1,
.service-12 h2,
.service-12 h3,
.service-12 h4,
.service-12 h5,
.service-12 h6 {
  color: #3e4555;
}

.service-12 h6 {
  line-height: 22px;
  font-size: 18px;
}

.service-12 .font-weight-medium {
  font-weight: 500;
}

.service-12 .badge {
  line-height: 14px;
}

.service-12 .badge-info {
  background: #188ef4;
}

.service-12 .subtitle {
  color: #8d97ad;
  line-height: 24px;
}

.service-12 .card.card-shadow {
  -webkit-box-shadow: 0px 0px 30px rgba(115, 128, 157, 0.1);
  box-shadow: 0px 0px 30px rgba(115, 128, 157, 0.1);
}

.service-12 .img-shadow {
  -webkit-box-shadow: 0px 0px 30px rgba(115, 128, 157, 0.3);
  box-shadow: 0px 0px 30px rgba(115, 128, 157, 0.3);
}

.service-12 .wrap-service12 .img-hover {
  -webkit-transition: 0.2s ease-in;
  -o-transition: 0.2s ease-in;
  transition: 0.2s ease-in;
}

.service-12 .wrap-service12 .img-hover:hover {
  -webkit-transform: scale(1.1);
  -ms-transform: scale(1.1);
  transform: scale(1.1);
}

.service-12 .wrap-service12 .uneven-box {
  margin-top: 100px;
}
</style>

<script>
import Program from "../services/get-programs";

export default {
  name: "Programs",
  data() {
    return {
      programs: [],
      myImage2:
        "https://images.unsplash.com/photo-1604467731651-3d8b9c702b86?ixlib=rb-1.2.1&ixid=MnwxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8&auto=format&fit=crop&w=688&q=80",
      myImage3:
        "https://images.unsplash.com/photo-1580261450046-d0a30080dc9b?ixlib=rb-1.2.1&ixid=MnwxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8&auto=format&fit=crop&w=709&q=80",
      myImage:
      "https://images.unsplash.com/photo-1633394782240-f81aba3f850d?ixlib=rb-1.2.1&ixid=MnwxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8&auto=format&fit=crop&w=2187&q=80",
      myImage1:
      "https://images.unsplash.com/photo-1549476464-37392f717541?ixlib=rb-1.2.1&ixid=MnwxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8&auto=format&fit=crop&w=687&q=80",

    };
  },

  methods: {
    getAllPrograms() {
      Program.getPrograms().then((response) => {
        this.programs = response.data;
      });
    },
  },
  created() {
    this.getAllPrograms();
  },
};
</script>

