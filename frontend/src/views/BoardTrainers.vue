<!--
<template>
  <div class="jumbotron myclass">
    <div class="container">
      <br /><br />
      <h1 class="display-4">Dashboard</h1>
      <hr class="my-4" />
      <table>
        <thead>
          <th><font-awesome-icon icon="list-ul" /> Trainers</th>
          <th>
            <div style="width: 1150px" align="right">
              <button type="button" class="btn btn-dark" v-on:click="addTrainer()">
                New Trainer
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
          placeholder="Search by username :"
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
          <th>Name</th>
          <th>Email</th>
          <th>Type</th>
          <th>Update</th>
          <th>Delete</th>
        </thead>
        <tbody>
          <tr v-for="trainer in filteredTrainers" v-bind:key="trainer.id">
            <td>{{ trainer.id }}</td>
            <td>{{ trainer.name }}</td>
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
    </div>
  </div>
</template>


<script>
import UserService from "../services/user.service";
import Trainer from "../services/get-trainers";

const customStyles = {
  ul: {
    color: "black",
  },
  li: {
    display: "inline-block",
  },
  a: {
    color: "black",
  },
};

export default {
  name: "Trainers",
  data() {
    return {
      search: "",
      trainers: [],
      pageOfItems: [],
      customStyles,
      role: "",
      index: "",
      content: "",
    };
  },

  methods: {
    getAllTrainers() {
      Trainer.getTrainers().then((response) => {
        this.trainers = response.data;
      });
    },
  },
  created() {
    this.getAllTrainers();
  },
  mounted() {
    UserService.getAdminBoard().then(
      (response) => {
        this.trainers = response.data;
      },
      (error) => {
        this.trainers =
          (error.response &&
            error.response.data &&
            error.response.data.message) ||
          error.message ||
          error.toString();
      }
    );
  },

  computed: {
    filteredTrainers() {
      return this.trainers.filter((trainer) => {
        return trainer.name.toLowerCase().match(this.search.toLowerCase());
      });
    },
  },
};
</script>

<style>
.myclass {
  color: A3C6C4;
}
</style>
-->



<template>
  <div class="jumbotron myclass">
    <div class="container">
      <br /><br />
      <h1 class="display-4">Dashboard</h1>
      <hr class="my-4" />
      <table>
        <thead>
          <th><font-awesome-icon icon="list-ul" /> Trainers</th>
          <th>
            <div style="width: 1150px" align="right">
              <button type="button" class="btn btn-dark" v-on:click="addTrainer()">
                New Trainer
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
          placeholder="Search by username :"
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
          <th>Name</th>
          <th>Email</th>
          <th>Type</th>
          <th>Update</th>
          <th>Delete</th>
        </thead>
        <tbody>
          <tr v-for="trainer in filteredTrainers" v-bind:key="trainer.id">
            <tr v-for="trainer in pageOfItems" :key="trainer.name">
            <td>{{ trainer.id }}</td>
            <td>{{ trainer.name }}</td>
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
            <div class="card text-center m-11">
          <jw-pagination
            :pageSize="2"
            :items="filteredTrainers"
            @changePage="changePage"
            :styles="customStyles"
          ></jw-pagination>
        
      </div>
    </div>
  </div>
</template>


<script>
import Trainer from "../services/get-trainers";

const customStyles = {
  ul: {
    color: "black",
  },
  li: {
    display: "inline-block",
  },
  a: {
    color: "black",
  },
};

export default {
  name: "Trainers",
  data() {
    return {
      search: "",
      trainers: [],
      pageOfItems: [],
      customStyles,
      role: "",
      index: "",
      content: "",
    };
  },

  methods: {
    getAllTrainers() {
      Trainer.getTrainers().then((response) => {
        this.trainers = response.data;
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
        changePage(pageOfItems) {
      return (this.pageOfItems = pageOfItems);
    },

  },
  created() {
    this.getAllTrainers();
  },

  computed: {
    filteredTrainers() {
      return this.trainers.filter((trainer) => {
        return trainer.name.toLowerCase().match(this.search.toLowerCase());
      });
    },
  },
};
</script>

<style>
.myclass {
  color: A3C6C4;
}
</style>
