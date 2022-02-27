<template>
<div class="bg-light py-5 service-3">
    <div class="container">
        <!-- Row  -->
        <div class="row">
            <!-- Column -->

            <div class="col-md-6 wrap-service3-box" v-for="trainer in trainers" v-bind:key="trainer.id">
                <div class="card border-0 card-shadow mb-4">
                    <div class="card-body d-flex">
                        <div class="align-self-center">
                            <h6 class="font-weight-medium">{{trainer.name}}</h6>
                            <p class="mt-3">{{trainer.type}}</p>
                        </div>
                    </div>
                </div>
            </div>
            <!-- Column -->

						<div class="col-md-12 mt-3 text-center">
                <a href="/programs/onlineClasses" class="btn btn-dark text-white border-0 btn-md btn-arrow" ><span>Vizualizeaza antrenamentele</span></a>
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

.service-3 h1, .service-3 h2, .service-3 h3, .service-3 h4, .service-3 h5, .service-3 h6 {
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


