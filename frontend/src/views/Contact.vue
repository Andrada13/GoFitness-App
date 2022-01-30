<template>
  <div class="contact3 py-5">
    <div class="row no-gutters">
      <div class="container">
        <div class="row">
          <div class="col-lg-6">
              <br><br>
            <div class="card-shadow">
              <img
                src="https://images.unsplash.com/photo-1484807352052-23338990c6c6?ixlib=rb-1.2.1&ixid=MnwxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8&auto=format&fit=crop&w=1470&q=80" 
                class="img-fluid"
              />
            </div>
          </div>
          <div class="col-lg-6">
            <div class="contact-box ml-3">
            <br> <br>
              <h1 class="font-weight-light mt-2">Contact us</h1>
              <form class="mt-4">
                <div class="row">
                  <div class="col-lg-12">
                    <div class="form-group mt-2">
                      <input
                        class="form-control"
                        type="text"
                        placeholder="Name :"
                      />
                    </div>
                  </div>
                  <div class="col-lg-12">
                    <div class="form-group mt-2">
                      <input
                        class="form-control"
                        type="email"
                        placeholder="Email address :"
                      />
                    </div>
                  </div>
                  <div class="col-lg-12">
                    <div class="form-group mt-2">
                      <input
                        class="form-control"
                        type="text"
                        placeholder="Subject :"
                      />
                    </div>
                  </div>
                  <div class="col-lg-12">
                    <div class="form-group mt-2">
                      <textarea
                        class="form-control"
                        rows="3"
                        placeholder="Message :"
                      ></textarea>
                    </div>
                  </div>
                  <div class="col-lg-12">
                    <button
                      type="submit"
                      class="btn btn-dark mt-3 text-white border-0 px-3 py-2"
                    >
                      <span> SEND </span>
                    </button>
                  </div>
                </div>
              </form>
            </div>
            <br />
            <br />
            <br /><br /><br /><br />
          </div>
          <div class="col-lg-12">
            <div class="card mt-4 border-0 mb-4">
              <div class="row">
                <div class="col-lg-4 col-md-4">
                  <div
                    class="card-body d-flex align-items-center c-detail pl-0"
                  >
                    <div class="mr-3 align-self-center">
                      <img
                        src="https://cdn-icons-png.flaticon.com/32/484/484167.png"
                      />
                    </div>
                    <div class="">
                      <h6>Address :</h6>
                      <p>
                        Calea Victoriei 125 <br />
                        Sector 1 Bucuresti
                      </p>
                    </div>
                  </div>
                </div>
                <div class="col-lg-4 col-md-4">
                  <div class="card-body d-flex align-items-center c-detail">
                    <div class="mr-3 align-self-center">
                      <img
                        src="https://cdn-icons-png.flaticon.com/32/483/483947.png"
                      />
                    </div>
                    <div class="">
                      <h6>Phone :</h6>
                      <p>
                        +(40) 753627389 <br>
                        +(40) 648264829
                      </p>
                    </div>
                  </div>
                </div>
                <div class="col-lg-4 col-md-4">
                  <div class="card-body d-flex align-items-center c-detail">
                    <div class="mr-3 align-self-center">
                      <img
                        src="https://cdn-icons-png.flaticon.com/32/725/725643.png"
                      />
                    </div>
                    <div class="">
                      <h6>Email :</h6>
                      <p>
                        info@fitness.com
                        <br />
                        gym@fitness.com
                      </p>
                    </div>
                  </div>
                </div>
              </div>
            </div>
          </div>
        </div>
      </div>
    </div>
  </div>
</template>

<script>
export default {
  name: "Contact",
  data() {
    return {
      msg: "Contact us",
    };
  },
};
</script>

<style scoped>
.contact3 {
  font-family: "Montserrat", sans-serif;
  color: #8d97ad;
  font-weight: 300;
}

.contact3 h1,
.contact3 h6 {
  color: #1a1a1b;
}

.contact3 .font-weight-medium {
  font-weight: 500;
}

.contact3 .card-shadow {
  -webkit-box-shadow: 0px 0px 30px rgba(115, 128, 157, 0.1);
  box-shadow: 0px 0px 30px rgba(115, 128, 157, 0.1);
}

.img-fluid {
  height: 450px;
  width: 635px;
}
</style>
