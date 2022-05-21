
<template>
  <div class="jumbotron myclass">
    <div class="container">
      <br /><br />
      <br /><br />
      <h6 class="display-6">Program pentru cursul: {{ programs.name }}</h6>
      <hr class="my-4" />

      <ul>
        <li v-for="time in times" v-bind:key="time.programTime_id">
          {{ time }}
        </li>
      </ul>
      <hr class="my-4" />
<div style="display: flex; justify-content: space-around">
<div>
      <form name="form" class = "form-control" @submit.prevent="addTimeToCourse()">
        <h6 class="display-8">Alegeti ziua:</h6>
        <select class="form-select" v-model="message">
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
        <vue-timepicker
          hour-label="Ora"
          minute-label="Minute"
          v-model="message1"
          format="HH:mm"
        ></vue-timepicker>
        <br /><br />
<br>
        <h6 class="display-8" v-bind:value="message + message1">
          Data este: {{ message }} {{ message1 }}
        </h6>
        <br />
        <div class="form-group">
          <button class="btn btn-dark w-40">Adauga program</button>
        </div>
      </form>
</div>
<div>
      <form name="form" class = "form-control" @submit.prevent="removeTimeFromCourse()">
        <h6 class="display-8">Alegeti programul:</h6>
        <select class="form-select" v-model="times.time_program">

          <option v-for="time in times" v-bind:key="time.programTime_id" v-bind:value="time">
          {{time}}
          </option>
         
          <option selected disabled v-if="times.length === 0">
            Nu exista program!
          </option>
        </select>
      <br>
        
        <div class="form-group">
          <button class="btn btn-dark w-40">Sterge program</button>
        </div>
      </form>
</div>

</div>
<br>
      <hr class="my-4" />

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
      programs: [],
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
        console.log(this.times, this.id)

      });
    },

    addTimeToCourse() {
      //  this.message = "";
      this.submitted = true;

      // console.log(this.notUsers);
      this.message3 = this.message  +', ' + this.message1;
      console.log(this.message3);

      Program.addTimeProgram(this.message3, this.id);
      //face refresh automat
      location.reload();
    },

     removeTimeFromCourse() {
      //  this.message = "";
      this.submitted = true;

      // console.log(this.notUsers);
     // console.log(this.message3);


      Program.removeTimeFromProgram(this.times.time_program, this.id);
      console.log(this.times.time_program, this.id)

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

.form-select{
  width:160px;
}
.container{
  width:550px;
}
</style>

