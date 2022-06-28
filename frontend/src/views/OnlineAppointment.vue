<template>
  <div class="jumbotron myclass">
    <div class="container">
    <br /><br /><br> <br><br>
    <div class="titlul">
    <h3 class="display-6">Rezervare cursuri </h3>
    </div>

    <div class="py-5 service-6">
      <div class="container">
        <div class="row">
           <hr class="my-4" />
          <div class="col-md-4 wrap-service6-box">
            <div class="card border-0 bg-success-gradiant text-black mb-4">
              <div class="card-body">
                <h6 class="font-weight-medium text-black">
                  Cat dureaza un curs/antrenament?
                </h6>
                <p class="mt-3">
                  Antrenamentele dureaza in jur de 2 ore cu posibilitatea prelungirii acestora.
                  
                </p>
                                <h6 class="font-weight-medium text-black">
                  Cum se vor desfasura cursurile?
                </h6>

                 <p class="mt-3">
                  Cursurile se vor desfasura in sala de sport, 
                  adresa fiind disponibila in pagina <i>Contact</i>.
                </p>
              </div>
            </div>
          </div>
          <div class="col-md-4 wrap-service6-box">
            <div class="card border-0 bg-info-gradiant text-black mb-4">
              <div class="card-body">
                                  <h6 class="font-weight-medium text-black">
                  Pot cumpara si un abonament?
                </h6>
                <p class="mt-3">
                  Cursurile se desfasoara pe sedinte, cu ajutorul rezervarilor.
                </p>

                <h6 class="font-weight-medium text-black">Pot participa la mai multe cursuri?</h6>
                <p class="mt-3">
                  Da, in functie de programul dumneavoastra, dar si de cel al instructorilor.
                </p>
                
              </div>
            </div>
          </div>

          <div class="col-md-4 wrap-service6-box">
            <div class="card border-0 bg-danger-gradiant text-black mb-4">
              <div class="card-body">
             <h6 class="font-weight-medium text-black">Cum pot plati?</h6>
                <p class="mt-3">
                  Plata se efectueaza exclusiv cu cardul.
                </p>

              <h6 class="font-weight-medium text-black">
                  Ce activitati se desfasoara in cadrul cursurilor?
                </h6>
                <p class="mt-3">
                  Pentru mai multe detalii legate de cursuri, puteti accesa pagina <i>Cursuri</i>.
                </p>

              </div>
            </div>
          </div>
        </div>
      <hr class="my-4" />
      </div>
    
      <br>
      <div class="tabel">
      <h4>
        Programul cursurilor este urmatorul:
      </h4>
      </div>
    </div>

    <table class="table table-striped table-sm">
      <thead class="thead-dark">
        <th>Curs</th>
        <th>Program</th>
        <th>Antrenor</th>
        <th>Pret 2h</th>
        <th>Rezervare</th>
      </thead>
      <tbody>
        <tr v-for="program in programs" v-bind:key="program.id">

         <td>{{ program.name }}</td>
         <td>
         <div v-for="p in program.programs" :key="p.programTime_id">
              {{ p.time_program }}
              <br>
          </div>

         </td>
          <td>
             <div v-for="t in program.trainer" :key="t.id">
              {{ t.fullName }}
              <br>
          </div>
          </td>
          <td>{{ program.price }}</td>
          <td>
            <button class="btn btn-dark" v-on:click="updateProgram(program.id)">
              Rezerva aici
            </button>
          </td>
        </tr>
      </tbody>
    </table>
    <br>
    <br>
</div>
  </div>
</template>

<script>
import Program from "../services/get-programs";
//import TimeProgram from "../services/get-time-programs";
import User from "../services/get-users";


export default {
  name: "Programs",
  data() {
    return {
      programs: [],
      timePrograms:[],
      users:[]
    };
  },

  methods: {
    getAllPrograms() {
      Program.getPrograms().then((response) => {
        this.programs = response.data;
        console.log(this.programs)

      });
    },
   
     getAllTrainersForPrograms() {
      User.getUsers().then((response) => {
        this.users = response.data;

      });
    },
      updateProgram(id) {
      this.$router.push(`/programs-classes/${id}`);
    },

  },
  created() {
    this.getAllPrograms();
   // this.getAllTimePrograms();
    this.getAllTrainersForPrograms();
  },
};
</script>

<style scoped>
*{
  font-family: serif;
}
.titlul h3{
  text-align: center;
}
.table{
  font-family: serif;

}
.tabel h4{
  padding: 30px ;
}

.service-6 {
  font-family: serif;
  color: #8d97ad;
  font-weight: 300;
}

.service-6 h1,
.service-6 h2,
.service-6 h3,
.service-6 h4,
.service-6 h5,
.service-6 h6 {
  color: #3e4555;
}
.service-6 h6{
  font-size:19px;
}
.service-6 .font-weight-medium {
  font-weight: 500;
}

.service-6 .subtitle {
  color: #8d97ad;
  line-height: 24px;
}

.service-6 a {
  text-decoration: none;
}

.service-6 .wrap-service6-box {
  -webkit-transition: 0.2s ease-in;
  -o-transition: 0.2s ease-in;
  transition: 0.2s ease-in;
}

.service-6 .wrap-service6-box .card-body {
  padding: 30px;
}


.service-6 .wrap-service6-box .card-body p {
  opacity: 0.8;
}

.service-6 .wrap-service6-box:hover {
  -webkit-transform: scale(1.1);
  -ms-transform: scale(1.1);
  transform: scale(1.1);
}

.service-6 .bg-danger-gradiant {
  background: rgb(241, 239, 239);
  
}

.service-6 .bg-info-gradiant {
  background: rgb(241, 239, 239);
 
}

.service-6 .bg-success-gradiant {
   background: rgb(241, 239, 239);

}



</style>