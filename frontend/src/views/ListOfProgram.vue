
<template>
  <div class="jumbotron myclass">
    <div class="container">
      <br /><br />
      <br /><br />
 <h6 class="display-6">Program pentru cursul: {{programs.name}}</h6>
       <hr class="my-4" />

      <ul>
        <li v-for="time in times" v-bind:key="time.programTime_id">
          {{ time }}
        </li>
      </ul>
       <hr class="my-4" />

      <form name="form" @submit.prevent="addTrainerToCourse()">
 <h6 class="display-8">Alegeti ziua:</h6>
        <select class="form-select"  v-model="message">

          <option>Luni</option>
          <option>Marti</option>
          <option>Miercuri</option>
          <option>Joi</option>
          <option>Vineri</option>
          <option>Sambata</option>
          <option>Duminca</option>
        </select>
        <br />
         <h6 class="display-8">Alegeti ora:</h6>
              <vue-timepicker hour-label="Ora" minute-label="Minute" v-model="message1" format="HH:mm"></vue-timepicker>
              <br><br>

                <h6 class="display-8" v-bind:value="message + message1">
        Data aleasa este {{ message }}  {{ message1 }}
      </h6>
      <br> 
        <div class="form-group text-center">
          <button class="btn btn-dark w-100">Adauga program</button>
        </div>
      </form>



    
    </div>
  </div>
</template>


<script>
import Program from "../services/get-programs";
import "vue2-timepicker/dist/VueTimepicker.css";

//import TrainerToCourse from '../models/trainertocourse';

export default {
  name: "ProgramTime",
  data() {
    return {
      times: [],
      submitted: false,
      content: "",
      message: "",
      message1: "",
      message3: "",
      programs:[],
    };
  },
  computed: {
    id() {
      return this.$route.params.id;
    },
  },
  methods: {
      getProgramsById() {
      Program.getProgramsById(this.id).then((response) => {
        this.programs = response.data;
      });
    },
    getTimeForCourseId() {
      Program.getTimeByCourseId(this.id).then((response) => {
        this.times = response.data;
      });
    },

    addTrainerToCourse() {
      //  this.message = "";
      this.submitted = true;

      // console.log(this.notUsers);
      this.message3 = this.message + ", " + this.message1;
      console.log(this.message3);

      Program.addTimeProgram(this.message3, this.id);
      //face refresh automat
      location.reload();
    },
  },
  created() {
    this.getProgramsById();
    this.getTimeForCourseId();
  },
};
</script>

<style scoped>
.myclass {
  color: A3C6C4;
}
</style>

