<template>

  <div class="container">
 <br><br>
      <h6 class="display-6">
        Buna, <strong>{{currentUser.username}}</strong>! 
      </h6>
      <p class="mt-0 mb-5">Acesta este profilul tau.</p>
   
      <!-- Page content -->
    <div class="container-fluid mt--14">
      <div class="row">
        <div class="col-xl-4 order-xl-2 mb-5 mb-xl-0">
          <div class="card card-profile shadow">
            <div class="card-body pt-0 pt-md-4">
              <div class="text-center">
                <h3>
                  {{currentUser.username}}<span class="font-weight-light"></span>
                </h3>
                <div class="h5 font-weight-300">
                  <i class="ni location_pin mr-2"></i>Bucharest, Romania
                </div>
                <div class="h5 mt-4">
                  <i class="ni business_briefcase-24 mr-2"></i>Solution Manager - Creative Tim Officer
                </div>
                <div>
                  <i class="ni education_hat mr-2"></i>University of Computer Science
                </div>
                <hr class="my-4">
                <p></p>
              </div>
            </div>
          </div>
        </div>
      </div>
<br><br>

        <div class="col-xl-8 order-xl-1">
          <div class="card bg-secondary shadow">
            <div class="card-header bg-white border-0">
              <div class="row align-items-center">
                <div class="col-8">
                  <h3 class="mb-0">Contul meu</h3>
                </div>
              </div>
            </div>
            <div class="card-body">
              <form>
                <h6 class="heading-small text-muted mb-4">Informatiile utilizatorului</h6>
                <div class="pl-lg-4">
                  <div class="row">
                    <div class="col-lg-6">
                      <div class="form-group focused">
                        <label class="form-control-label" for="input-username">Nume de utilizator : 
                          {{currentUser.username}}
                        </label>
                      </div>
                    </div>
                    <div class="col-lg-6">
                      <div class="form-group">
                        <label class="form-control-label" for="input-email">Adresa de mail :
                          {{currentUser.email}}
                        </label>
                      </div>
                    </div>
                    <div class="col-12 text-right">
                  <a href="#!" class="btn btn-dark">Editeaza profilul</a>
                </div>

                  </div>
                  <div class="row">
                  
                  </div>
                </div>
                <hr class="my-4">
                <!-- Address -->
                <h6 class="heading-small text-muted mb-4">Curs/Abonament in desfasurare</h6>
                <div class="pl-lg-4">
                  <div class="row">
                    <div class="col-md-12">
                      <div class="form-group focused">
                        <label class="form-control-label" for="input-address">Curs/Abonament</label>
                        <input id="input-address" class="form-control form-control-alternative" placeholder="" value="" type="text">
                      </div>
                    </div>
                  </div>
                  <div class="row">
                    <div class="col-lg-4">
                      <div class="form-group focused">
                        <label class="form-control-label" for="input-city">City</label>
                        <input type="text" id="input-city" class="form-control form-control-alternative" placeholder="" value="">
                      </div>
                    </div>
                    <div class="col-lg-4">
                      <div class="form-group focused">
                        <label class="form-control-label" for="input-country">Country</label>
                        <input type="text" id="input-country" class="form-control form-control-alternative" placeholder="" value="">
                      </div>
                    </div>
                  </div>
                </div>
                
              </form>
            </div>
          </div>
        </div>
    </div>
    <p>
      <strong>Email:</strong>
      {{currentUser.email}}
    </p>
    <strong>Authorities:</strong>
    <ul>
      <li v-for="(role,index) in currentUser.roles" :key="index">{{role}}</li>
    </ul>
  </div>


  
</template>

<script>

export default {
  name: 'Profile',

  computed: {
    currentUser() {
      return this.$store.state.auth.user;
    }, 
  },
  mounted() {
    if (!this.currentUser) {
      this.$router.push('/login');
    }
  },

};
</script>

<style scoped>

body {
  font-family: "Montserrat", sans-serif;
  font-size: 1rem;
  font-weight: 400;
  line-height: 1.5;
  margin: 0;
  text-align: left;
}

hr {
  overflow: visible;
  box-sizing: content-box;
  height: 0;
}

h1,
h3,
h4,
h5,
 {
  margin-top: 0;
  margin-bottom: .5rem;
}

p {
  margin-top: 0;
  margin-bottom: 1rem;
}



strong {
  font-weight: bolder;
}

a {
  text-decoration: none;
  color: #080808;
  background-color: transparent;
  -webkit-text-decoration-skip: objects;
}

a:hover {
  text-decoration: none;
  color: #f9fafc;
}


label {
  display: inline-block;
  margin-bottom: .5rem;
}


input,
button {
  font-family: inherit;
  font-size: inherit;
  line-height: inherit;
  margin: 0;
}

button,
input {
  overflow: visible;
}

h1,
h3,
h4,
h5,

.h1,
.h3,
.h4,
.h5
 {
  font-family: inherit;
  font-weight: 600;
  line-height: 1.5;
  margin-bottom: .5rem;
  color: #32325d;
}

h1,
.h1 {
  font-size: 1.625rem;
}

h3,
.h3 {
  font-size: 1.0625rem;
}

h4,
.h4 {
  font-size: .9375rem;
}

h5,
.h5 {
  font-size: .8125rem;
}



hr {
  margin-top: 2rem;
  margin-bottom: 2rem;
  border: 0;
  border-top: 1px solid rgba(0, 0, 0, .1);
}


.container {
  width: 100%;
  margin-right: auto;
  margin-left: auto;
  padding-right: 15px;
  padding-left: 15px;
}



.container-fluid {
  width: 100%;
  margin-right: auto;
  margin-left: auto;
  padding-right: 15px;
  padding-left: 15px;
}

.row {
  display: flex;
  margin-right: -15px;
  margin-left: -15px;
  flex-wrap: wrap;
}

.col-4,
.col-8,
.col,
.col-md-10,
.col-md-12,
.col-lg-3,
.col-lg-4,
.col-lg-6,
.col-lg-7,
.col-xl-4,
.col-xl-6,
.col-xl-8 {
  position: relative;
  width: 100%;
  min-height: 1px;
  padding-right: 15px;
  padding-left: 15px;
}

.col {
  max-width: 100%;
  flex-basis: 0;
  flex-grow: 1;
}

.col-4 {
  max-width: 33.33333%;
  flex: 0 0 33.33333%;
}

.col-8 {
  max-width: 66.66667%;
  flex: 0 0 66.66667%;
}


.form-control {
  font-size: 1rem;
  line-height: 1.5;
  display: block;
  width: 100%;
  height: calc(2.75rem + 2px);
  padding: .625rem .75rem;
  transition: all .2s cubic-bezier(.68, -.55, .265, 1.55);
  color: #c0c0c4;
  border: 1px solid #cad1d7;
  border-radius: .375rem;
  background-color: #fff;
  background-clip: padding-box;
  box-shadow: none;
}


.form-control:-ms-input-placeholder {
  opacity: 1;
  color: #adb5bd;
}

.form-control::-ms-input-placeholder {
  opacity: 1;
  color: #adb5bd;
}

.form-control::placeholder {
  opacity: 1;
  color: #adb5bd;
}

.form-control:disabled,
.form-control[readonly] {
  opacity: 1;
  background-color: #e9ecef;
}


.form-group {
  margin-bottom: 1.5rem;
}

.form-inline {
  display: flex;
  flex-flow: row wrap;
  align-items: center;
}



.btn {
  font-size: 1rem;
  font-weight: 600;
  line-height: 1.5;
  display: inline-block;
  padding: .625rem 1.25rem;
  text-align: center;
  vertical-align: middle;
  white-space: nowrap;
  border: 1px solid transparent;
  border-radius: .375rem;
}



.progress {
  font-size: .75rem;
  display: flex;
  overflow: hidden;
  height: 1rem;
  border-radius: .375rem;
  background-color: #fcfdfd;
  box-shadow: inset 0 .1rem .1rem rgba(0, 0, 0, .1);
}


.bg-secondary {
  background-color: #fff !important;
}


.border-0 {
  border: solid !important;
}

.rounded-circle {
  border-radius: 50% !important;
}



.text-right {
  text-align: right !important;
}


.font-weight-light {
  font-weight: 300 !important;
}

.font-weight-bold {
  font-weight: 600 !important;
}

.text-white {
  color: #fff !important;
}

.text-white {
  color: #fff !important;
}

.font-weight-300 {
  font-weight: 300 !important;
}

.text-sm {
  font-size: .875rem !important;
}

.text-white {
  color: #fff !important;
}

a.text-white:hover,
a.text-white:focus {
  color: #e6e6e6 !important;
}

.btn {
  font-size: .875rem;
  position: relative;
  transition: all .15s ease;
  letter-spacing: .025em;
  text-transform: none;
  will-change: transform;
}





.form-control-label {
  font-size: .875rem;
  font-weight: 600;
  color: #525f7f;
}

.form-control {
  font-size: .875rem;
}

.form-control:focus:-ms-input-placeholder {
  color: #adb5bd;
}

.form-control:focus::-ms-input-placeholder {
  color: #adb5bd;
}

.form-control:focus::placeholder {
  color: #adb5bd;
}

.progress {
  overflow: hidden;
  height: 8px;
  margin-bottom: 1rem;
  border-radius: .25rem;
  background-color: #e9ecef;
  box-shadow: inset 0 1px 2px rgba(0, 0, 0, .1);
}

p {
  font-size: 1rem;
  font-weight: 300;
  line-height: 1.7;
}

.description {
  font-size: .875rem;
}

.heading {
  font-size: .95rem;
  font-weight: 600;
  letter-spacing: .025em;
  text-transform: uppercase;
}

.heading-small {
  font-size: .75rem;
  padding-top: .25rem;
  padding-bottom: .25rem;
  letter-spacing: .04em;
  text-transform: uppercase;
}


</style>