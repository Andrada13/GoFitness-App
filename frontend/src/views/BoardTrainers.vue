
<template>

  <div class="jumbotron myclass">
    <div class="container">
      <br /><br /> <br><br>
      <h1 class="display-4">Dashboard antrenori</h1>
      <hr class="my-4" />

<!--
      <table>
        <thead>
          <h6> Antrenori : {{numberOfTrainers}}</h6> 
        </thead>
      </table>
   
          <th>
            <div style="width: 1150px" align="right">
              <button type="button" class="btn btn-dark" v-on:click="addTrainer()">
                New Trainer
              </button>
            </div>
          </th>
        </thead>
      </table>
   -->  

      <br /><br />
      <h3>{{ content }}</h3>
      <div class="input-group">
        <input
          type="text"
          class="form-control rounded"
          placeholder="Search trainer by username :"
          v-model="search"
          aria-label="Search"
          aria-describedby="search-addon"
        />
      </div>
      <br />
      <br />
<!--

      <table class="table table-striped">
        <thead class="thead-dark">
          <th>#</th>
          <th>Trainer Name</th>
          <th>Email</th>
          <th>Type</th>
          <th>Update</th>
          <th>Delete</th>
        </thead>
        <tbody>
          <tr v-for="trainer in filteredTrainers" v-bind:key="trainer.id">
            <td>{{ trainer.id }}</td>
            <td>{{ trainer.fullName }}</td>
            <td>{{ trainer.email }}</td>
            <td>{{ trainer.type }}</td>
            <td>
              <button class="btn btn-dark" v-on:click="updateTrainer(trainer.id)">
                Update
              </button>
            </td>
            <td>
              <button class="btn btn-dark" v-on:click="deleteTrainer(trainer.id)">
                Delete
              </button>
            </td>
          </tr>
       </tbody>
      </table>
  -->

      <ul>
  <li v-for="trainer in filteredTrainers" v-bind:key="trainer.id">
    {{ trainer.fullName }}
  </li>
</ul>
    </div>
  </div>


</template>


<script>
import Trainer from "../services/get-trainers";

export default {
  name: "Trainers",
  data() {
    return {
      search: "",
      trainers: [],
      role: "",
      index: "",
      content: "",
      numberOfTrainers: 0,
    };
  },

  methods: {
    getAllTrainers() {
      Trainer.getTrainers().then((response) => {
        this.trainers = response.data;
      });
    },
    getNumbers() {
      Trainer.getNumberOfTrainers().then((response) => {
        this.numberOfTrainers = response.data;
      });
    },

    updateTrainer(id) {
      this.$router.push(`/updateTrainer/${id}`);
    },

    deleteTrainer(id) {
      Trainer.deleteTrainersById(id).then(() => {
        this.getAllTrainers();
      });
    },

    addTrainer() {
      this.$router.push(`/addTrainer/`);
    },
    //   changePage(pageOfItems) {
    //  return (this.pageOfItems = pageOfItems);
    //  },
  },
  created() {
    this.getAllTrainers();
    this.getNumbers();
  },

  computed: {
    filteredTrainers() {
      return this.trainers.filter((trainer) => {
        return trainer.fullName.toLowerCase().match(this.search.toLowerCase());
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

