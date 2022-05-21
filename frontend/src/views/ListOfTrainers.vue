
<template>
  <div class="jumbotron myclass">
    <div class="container">
      <br /><br />
      <br /><br />

      <h6 class="display-6">Antrenori pentru cursul: {{ programs.name }}</h6>
      <hr class="my-4" />
      <ul>
        <li v-for="user in users" v-bind:key="user.id">
          {{ user.fullName }}
        </li>
      </ul>
      <hr class="my-4" />
      <br /><br />
      <div style="display: flex; justify-content: space-around">
        <div>
          <form
            name="form"
            class="form-control"
            @submit.prevent="addTrainerToCourse()"
          >
            <br />
            <h6 class="display-8">Adauga un antrenor:</h6>
            <div>
              <select class="form-select" v-model="notUsers.id">
                <option selected disabled>Alegeti antrenorul :</option>

                <option
                  v-for="notUser in notUsers"
                  v-bind:key="notUser.id"
                  v-bind:value="notUser.id"
                >
                  {{ notUser.fullName }}
                </option>
                <option selected disabled v-if="notUsers.length === 0">
                  Nu exista antrenori!
                </option>
              </select>
            </div>
            <br /><br />
            <div class="form-group">
              <button class="btn btn-dark w-100">Adauga</button>
            </div>
          </form>
        </div>
        <div>
          <form
            name="form"
            class="form-control"
            @submit.prevent="removeTrainerFromCourse()"
          >
            <br />
            <h6 class="display-8">Sterge un antrenor:</h6>
            <div>
              <select class="form-select" v-model="users.id">
                <option selected disabled>Alegeti antrenorul :</option>

                <option
                  v-for="user in users"
                  v-bind:key="user.id"
                  v-bind:value="user.id"
                >
                  {{ user.fullName }}
                </option>
                <option selected disabled v-if="users.length === 0">
                  Nu exista antrenori!
                </option>
              </select>
            </div>
            <br /><br />
            <div class="form-group">
              <button class="btn btn-dark w-100">Sterge</button>
            </div>
          </form>
        </div>
      </div>
      <br />
      <hr class="my-4" />
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
      programs: [],
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

      Program.addTrainerIdToCourseId(this.notUsers.id, this.id);
      location.reload();
    },

    removeTrainerFromCourse() {
      this.message = "";
      this.submitted = true;

      // console.log(this.notUsers);

      // Program.addTrainerIdToCourseId(this.notUsers.id,this.id);
      Program.removeTrainerFromCourse(this.id, this.users.id);
      console.log(this.id, this.users.id);
      // console.log(this.id,this.users.map(({id}) => id));

      location.reload();
    },
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
