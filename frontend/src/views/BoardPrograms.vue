<template>
  <div class="jumbotron myclass">
    <div class="container">
      <br /><br /><br><br>
      <h1 class="display-4">Dashboard cursuri</h1>
      <hr class="my-4" />

      <table>
        <thead>
          <h6> Cursuri : {{numberOfPrograms}}</h6> 
          <th>
            <div style="width: 1150px" align="right">
              <button type="button" class="btn btn-dark" v-on:click="addProgram()">
                Adauga curs
              </button>
            </div>
          </th>
        </thead>
      </table>

      <br /><br />
      <h3>{{ content }}</h3>
      <div class="input-group">
        <input
          type="text"
          class="form-control rounded"
          placeholder="Search program by username :"
          v-model="search"
          aria-label="Search"
          aria-describedby="search-addon"
        />
      </div>
      <br />
      <br />

      <table class="table table-striped">
        <thead class="thead-dark">
          <th>#</th>
          <th>Program Name</th>
          <th>Description</th>
          <th>Trainer Name</th>
          <th>Price</th>
          <th>Update</th>
          <th>Delete</th>
        </thead>
        <tbody>
          <tr v-for="program in filteredPrograms" v-bind:key="program.id">
            <td>{{ program.id }}</td>
            <td>{{ program.name }}</td>
            <td>{{ program.description }}</td>
            <td>{{program.trainerName}}</td>
            <td>{{ program.price }}</td>
            <td>
              <button class="btn btn-dark" v-on:click="updateProgram(program.id)">
                Update
              </button>
            </td>
            <td>
              <button class="btn btn-dark" v-on:click="deleteProgram(program.id)">
                Delete
              </button>
            </td>
          </tr>
       </tbody>
      </table>
    </div>
  </div>
</template>


<script>
import Program from "../services/get-programs";


export default {
  name: "Programs",
  data() {
    return {
      search: "",
      programs: [],
      role: "",
      index: "",
      content: "",
      numberOfPrograms: 0,

    };
  },

  methods: {
    getAllPrograms() {
      Program.getPrograms().then((response) => {
        this.programs = response.data;
      });

    },
    getNumbers() {
      Program.getNumberOfPrograms().then((response) => {
        this.numberOfPrograms = response.data;
      });
    },

    updateProgram(id) {
      this.$router.push(`/updateProgram/${id}`);
    },

     deleteProgram(id) {
      Program.deleteProgramsById(id).then(() => {
        this.getAllPrograms();
      });
    },

    addProgram() {
      this.$router.push(`/addProgram/`);
    },
     //   changePage(pageOfItems) {
    //  return (this.pageOfItems = pageOfItems);
  //  },

  },
  created() {
    this.getAllPrograms();
    this.getNumbers();
  },

  computed: {
    filteredPrograms() {
      return this.programs.filter((program) => {
        return program.name.toLowerCase().match(this.search.toLowerCase());
      });
    },
  },
};
</script>

<style scoped>
.myclass {
  color: A3C6C4;
}
</style>
