<template>
    <div class="pricing1 py-4 bg-light">
  <div class="container">
    <div class="row justify-content-center">
      <div class="col-md-8 text-center">
        <h3 class="mt-3 font-weight-medium mb-1">Abonamente</h3>
        <h6 class="subtitle">Puteti alege un singur abonament de mai jos : </h6>
      </div>
    </div>
    <br>
    <!-- Row  -->
    <div class="row mt-5">
      <!-- Column -->
      <div class="col-lg-4 col-md-6" v-for="membership in memberships"
              v-bind:key="membership.id"
            >
        <div class="card text-center card-shadow on-hover border-0 mb-4">
          <div class="card-body font-14">
            <h5 class="mt-3 mb-1 font-weight-medium">{{membership.name}}</h5>
            <h6 class="subtitle font-weight-normal">For Team of 3-5 Members</h6>
            <div class="pricing my-3">
              <sup>$</sup>
              <span class="monthly display-5">{{membership.price}}</span>
              <span class="yearly display-5">240</span>
              <small class="monthly">/mo</small>
              <small class="yearly">/yr</small>
            </div>
            <ul class="list-inline">
              <li class="d-block py-2">Perfect of Small Team</li>
              <li class="d-block py-2">Unlimited Invoices</li>
              <li class="d-block py-2">Project Management</li>
              <li class="d-block py-2">&nbsp;</li>
              <li class="d-block py-2">&nbsp;</li>
            </ul>
            <div class="bottom-btn">
              <a class="btn btn-dark btn-md text-white btn-block" href="#f1"><span>Alege abonamentul</span></a>
            </div>
          </div>
        </div>
      </div>
    </div>
  </div>
</div>
</template>


<style scoped>
@import url(//fonts.googleapis.com/css?family=Montserrat:300,400,500,600,700,800);
.pricing1 {
  font-family: "Montserrat", sans-serif;
  color: #8d97ad;
  font-weight: 300;
}

.pricing1 h1,
.pricing1 h2,
.pricing1 h3,
.pricing1 h4,
.pricing1 h5,
.pricing1 h6 {
  color: #3e4555;
}

.pricing1 .font-weight-medium {
  font-weight: 500;
}

.pricing1 .bg-light {
  background-color: #f4f8fa !important;
}

.pricing1 .subtitle {
  color: #8d97ad;
  line-height: 24px;
  font-size: 14px;
}

.pricing1 .font-14 {
  font-size: 14px;
}

.pricing1 h5 {
    line-height: 22px;
    font-size: 18px;
}

.pricing1 .card.card-shadow {
  -webkit-box-shadow: 0px 0px 30px rgba(115, 128, 157, 0.1);
  box-shadow: 0px 0px 30px rgba(115, 128, 157, 0.1);
}

.pricing1 .on-hover {
  -webkit-transition: 0.1s;
  -o-transition: 0.1s;
  transition: 0.1s;
}

.pricing1 .on-hover:hover {
  -ms-transform: scale(1.05);
  transform: scale(1.05);
  -webkit-transform: scale(1.05);
  -webkit-font-smoothing: antialiased;
}


.pricing1 .btn-md {
  padding: 15px 30px;
  font-size: 16px;
}

.pricing1 .onoffswitch {
  width: 70px;
  -webkit-user-select: none;
  -moz-user-select: none;
  -ms-user-select: none;
  margin: 0 auto;
}

.pricing1 .onoffswitch-label {
  cursor: pointer;
  border: 2px solid transparent;
  border-radius: 20px;
}

.pricing1 .onoffswitch-inner {
  width: 200%;
  margin-left: -100%;
  -webkit-transition: margin 0.3s ease-in 0s;
  -o-transition: margin 0.3s ease-in 0s;
  transition: margin 0.3s ease-in 0s;
}

.pricing1 .onoffswitch-inner::before,
.pricing1 .onoffswitch-inner::after {
  display: block;
  float: left;
  width: 50%;
  height: 30px;
  padding: 0;
  line-height: 30px;
  font-size: 14px;
  color: white;
  -webkit-box-sizing: border-box;
  box-sizing: border-box;
}

.pricing1 .onoffswitch-inner::before {
  content: "";
  padding-right: 27px;
  background-color: #2cdd9b;
  color: #FFFFFF;
}

.pricing1 .onoffswitch-inner::after {
  content: "";
  padding-right: 24px;
  background-color: #3e4555;
  color: #999999;
  text-align: right;
}

.pricing1 .onoffswitch-switch {
  width: 23px;
  margin: 6px;
  height: 23px;
  top: -1px;
  bottom: 0;
  right: 35px;
  border-radius: 20px;
  -webkit-transition: all 0.3s ease-in 0s;
  -o-transition: all 0.3s ease-in 0s;
  transition: all 0.3s ease-in 0s;
}

.pricing1 .onoffswitch-checkbox:checked+.onoffswitch-label .onoffswitch-inner {
  margin-left: 0;
}

.pricing1 .onoffswitch-checkbox:checked+.onoffswitch-label .onoffswitch-switch {
  right: 0px;
}

.pricing1 .price-badge {
  top: -13px;
  left: 0;
  right: 0;
  width: 100px;
  margin: 0 auto;
}

.pricing1 .badge-inverse {
  background-color: #3e4555;
}

.pricing1 .display-5 {
  font-size: 3rem;
	color: #263238;
}

.pricing1 .pricing sup {
  font-size: 18px;
  top: -20px;
}

.pricing1 .pricing .yearly {
  display: none;
}
</style>


<script>
import Memberships from "../services/get-memberships";

export default {
  name: "Memberships",
  data() {
    return {
      memberships: [],
    };
  },

  methods: {
    getAllMemberships() {
      Memberships.getMemberships().then((response) => {
        this.memberships = response.data;
      });
    },
  },
  created() {
    this.getAllMemberships();
  },
};
</script>


