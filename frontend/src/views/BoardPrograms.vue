<template>
  <div class="jumbotron myclass">
    <div class="container">
      <br /><br /><br /><br />
      <h1 class="display-4">Dashboard cursuri</h1>
      <hr class="my-4" />

      <table>
        <thead>
          <h6>Cursuri : {{ numberOfPrograms }}</h6>
          <th>
            <div style="width: 1150px" align="right">
              <button
                type="button"
                class="btn btn-dark"
                v-on:click="addProgram()"
              >
                Curs nou
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
          placeholder="Cauta curs dupa nume :"
          v-model="search"
          aria-label="Search"
          aria-describedby="search-addon"
        />
      </div>
      <br />
      <br />

      <table class="table table-hover table-sm">
        <thead class="thead-dark">
          <th>ID</th>
          <th>Denumire curs</th>
          <th>Descriere</th>
          <th>Pret</th>
          <th>Antrenori</th>
          <th>Program</th>
          <th>Editeaza</th>
          <th>Sterge</th>
        </thead>
        <tbody>
          <tr v-for="program in filteredPrograms" v-bind:key="program.id">
            <td>{{ program.id }}</td>
            <td>{{ program.name }}</td>
            <td>{{ program.description }}</td>

            <td>{{ program.price }}</td>
            <td>
              <button
                class="btn btn-dark btn-sm"
                v-on:click="listTrainers(program.id)"
              >
                Antrenori
              </button>
            </td>
            <td>
              <button class="btn btn-dark btn-sm" v-on:click="listProgram(program.id)">
                Program
              </button>
            </td>
            <td>
              <button
                class="btn btn-dark btn-sm"
                v-on:click="updateProgram(program.id)"
              >
                Editeaza
              </button>
            </td>
            <td>
              <button
                class="btn btn-dark btn-sm"
                v-on:click="deleteProgram(program.id)"
              >
                Sterge
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
    listProgram(id) {
      this.$router.push(`/list-program/${id}`);
    },
    listTrainers(id) {
      this.$router.push(`/list-trainer/${id}`);
    },
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
        this.getNumbers();
      });
    },

    addProgram() {
      this.$router.push(`/addProgram`);
    },
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
