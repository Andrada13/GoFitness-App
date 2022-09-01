
<template>
  <div class="rezervare">
    <br /><br /><br><br>
    <div class="col-md-12">
      <h2 align="center">Programare curs</h2>

      <div class="card card-container">
        <form @submit.prevent="insertBooking()" v-if="!savingSuccessful">
            
        
          <label><strong> Informatii utilizator: </strong></label>
           <label
            >Nume: <i> {{ currentUser.fullName }} </i></label
          >
          <label
            >Email: <i> {{ currentUser.email }} </i></label
          >

          <hr class="my-4" />

         <fieldset class="form-group">
            <label>Grupa:</label>
            <select class="form-select" v-model="grupa" >
              <option value="" selected disabled>
                Alegeti grupa:
              </option>

              <option>
                1
              </option>
               <option >
                2
              </option>
              
            </select>
          </fieldset>

          <fieldset class="form-group">
            <label>Denumire:</label>
            <input type="text" class="form-control" v-model="name" disabled />
          </fieldset>

          <fieldset class="form-group">
           
           
            
            

       
             <div v-if="grupa === '1'" >
             <label >Antrenor:</label>
           <input type="text" class="form-control" v-model=" fullName[0].fullName" disabled />
               </div>
            <div v-else-if="grupa === '2'">
            <label >Antrenor:</label>
                    <input type="text" class="form-control" v-model=" fullName[1].fullName" disabled />
                  </div>
        
          
          </fieldset>
          <fieldset class="form-group">
            <label>Pret (RON):</label>
            <input type="text" class="form-control" v-model="price" disabled />
          </fieldset>

          <fieldset class="form-group">

            
       
             <div v-if="grupa === '1'" >
             <label >Program:</label>
           <input type="text" class="form-control" v-model=" time_program[0].time_program" disabled />
               </div>
            <div v-else-if="grupa === '2'">
            <label >Program:</label>
                    <input type="text" class="form-control" v-model="time_program[1].time_program" disabled />
                  </div>

          </fieldset>

          <br />
          <div class="form-group text-center">
            <button class="btn btn-dark btn-block w-100" >
              Rezervare
            </button>
          </div>

        </form>

        <div class="success" v-if="savingSuccessful"> 
    {{ "Rezervarea a fost efectuata cu succes!" }} 
</div>
       
      </div>
    </div>
  </div>
</template>

<script>
import Program from "../services/get-programs";

export default {
  name: "ClassAppointment",
  data() {
    return {
      name: "",
      date: "",
      price: 0,
      time_program: "",
      timePrograms: [],
      trainer:[],
      fullName:"",
      content: "",
      grupa:"",
      successful: false,
      savingSuccessful: false
    };
  },
  computed: {
    id() {
      return this.$route.params.id;
    },
    currentUser() {
      return this.$store.state.auth.user;
    },
  },

  methods: {
    UpdateProgram() {
      Program.getProgram(this.id).then((result) => {
        this.name = result.data.name;
        this.price = result.data.price;
        this.time_program = result.data.programs;
        this.fullName = result.data.trainer;
        if (this.grupa === 1) {
      console.log(this.id, this.grupa,this.time_program[0].programTime_id,this.fullName[0].id,this.currentUser.id)
     } else if (this.grupa === 2) {
       console.log(this.id, this.grupa,this.time_program[1].programTime_id,this.fullName[1].id,this.currentUser.id)

     } 
      });
    },
  
     insertBooking() {

     if (this.grupa=== '1') {
      Program.insertBooking(this.id, this.grupa,this.time_program[0].programTime_id,this.fullName[1].id,this.currentUser.id)
             this.savingSuccessful = true;

     } else if (this.grupa === '2') {
      Program.insertBooking(this.id, this.grupa,this.time_program[1].programTime_id,this.fullName[1].id,this.currentUser.id)
  this.savingSuccessful = true;
     }

  
    },
  },
  created() {
    this.UpdateProgram();
  },
};
</script>

<style scoped>
*{
  font-family: serif;
}

.center_div {
 
  width: 80%;
}
label {
  display: block;
  margin-top: 10px;
}

.card-container.card {
  max-width: 350px !important;
  padding: 40px 40px;
}
.card {
  background-color: #fbfbfb;
  padding: 25px 25px 30px;
  margin: 0 auto 25px;
  margin-top: 50px;
  -moz-border-radius: 2px;
  -webkit-border-radius: 2px;
  border-radius: 2px;
  -moz-box-shadow: 0px 2px 2px rgba(0, 0, 0, 0.3);
  -webkit-box-shadow: 0px 2px 2px rgba(0, 0, 0, 0.3);
  box-shadow: 0px 2px 2px rgba(0, 0, 0, 0.3);
  
  
}
</style>
