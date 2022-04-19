
<template>
  <div class="jumbotron myclass">
    <div class="container">
      <br /><br />
      <br /><br />
                    
      <h6 class="display-6">Antrenori pentru cursul: {{programs.name}}</h6>
      <hr class="my-4" />
      <ul>
        <li v-for="user in users" v-bind:key="user.id">
          {{ user }}
        </li>
      </ul>
      <hr class="my-4" />


      <form name="form" class = "form-control" @submit.prevent="addTrainerToCourse()">
        <br>
              <h6 class="display-8 text-center">Adauga un antrenor:</h6>
     <div class="col-sm-2">
        <select class="form-select" v-model="notUsers.id">
          <option selected disabled>Alegeti antrenorul :</option>

          <option
            v-for="notUser in notUsers"
            v-bind:key="notUser.id"
            v-bind:value="notUser.id"
          >
            {{ notUser.fullName }}
          </option>
        </select>
                  </div>
        <br /><br />
        <div class="form-group">
          <button class="btn btn-dark w-30">Adauga </button>
        </div>
      </form>
    </div>
  </div>
</template>


<script>
import Program from "../services/get-programs";
//import TrainerToCourse from '../models/trainertocourse';

export default {
  name: "Trainers",
  data() {
    return {
      search: "",
      users: [],
      notUsers: [],
      submitted: false,
      content: "",
      programs:[],
    };
  },
  computed: {
    id() {
      return this.$route.params.id;
    },    
     name() {
      return this.$route.params.name;
    },    
  },

  methods: {
     getProgramsById() {
      Program.getProgramsById(this.id).then((response) => {
        this.programs = response.data;
      });
    },
    getTrainersByCourseId() {
      Program.getTrainerByCourse(this.id).then((response) => {
        this.users = response.data;
        console.log(this.name);
      });
    },
    getTrainersNotByCourseId() {
      Program.getTrainerNotByCourse(this.id).then((response) => {
        this.notUsers = response.data;

      });
    },
    addTrainerToCourse() {
      this.message = "";
      this.submitted = true;
      
    // console.log(this.notUsers);

      Program.addTrainerIdToCourseId(this.notUsers.id,this.id);
      
    }
    
  },
  created() {
    this.getProgramsById();
    this.getTrainersByCourseId();
    this.getTrainersNotByCourseId();
   // this.addTrainerToCourse();
  },
};
</script>

<style scoped>
.myclass {
  color: A3C6C4;
}
</style>

