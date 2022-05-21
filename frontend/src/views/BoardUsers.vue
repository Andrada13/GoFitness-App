<template>
  <div class="jumbotron myclass">
    <div class="container">
      <br /><br /><br><br>
      <h1 class="display-4">Dashboard utilizatori</h1>
      <hr class="my-4" />

      <table>
        <thead>
          <h6> Utilizatori : {{numberOfUsers}}</h6> 
          <th>
            <div style="width: 1150px" align="right">
              <button type="button" class="btn btn-dark" v-on:click="addUser()">
                Utilizator Nou
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
          placeholder="Cauta dupa numele de utilizator :"
          v-model="search"
          aria-label="Search"
          aria-describedby="search-addon"
        />
      </div>
      <br />
      <br />

      <table class="table table-hover table-sm">
        <thead class="thead-light">
          <th>ID</th>
          <th>Nume si prenume</th>
          <th>Nume de utilizator</th>
          <th>Email</th>
          <th>Adresa</th>
          <th>Numar de telefon</th>
          <th>Rol</th>
          <th>Editeaza</th>
          <th>Sterge</th>
        </thead>
        <tbody>
          <tr v-for="user in filteredUsers" v-bind:key="user.id"></tr>
          <tr v-for="user in pageOfItems" :key="user.username">
            <td>{{ user.id }}</td>
            <td>{{user.fullName}}</td>
            <td>{{ user.username }}</td>
            <td>{{ user.email }}</td>
            <td>{{user.address}}</td>
            <td>{{user.phoneNumber}}</td>
            <td v-for="(role, index) in user.roles" :key="index">
              {{ role.name }}
            </td>
            <td>
              <button class="btn btn-dark btn-sm" v-on:click="updateUser(user.id)">
                Editeaza
              </button>
            </td>
            <td>
              <button class="btn btn-dark btn-sm" v-on:click="deleteUser(user.id)">
                Sterge
              </button>
            </td>
          </tr>
        </tbody>
      </table>
      <div class="card text-center m-11">
        <jw-pagination
          :pageSize="2"
          :items="filteredUsers"
          @changePage="changePage"
          :styles="customStyles"
        ></jw-pagination>
      </div>
    </div>
  </div>
</template>


<script>
import UserService from "../services/user.service";
import User from "../services/get-users";

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
  name: "Users",
  data() {
    return {
      search: "",
      users: [],
      pageOfItems: [],
      customStyles,
      role: "",
      index: "",
      content: "",
      numberOfUsers: 0,
    };
  },

  methods: {
    getAllUsers() {
      User.getUsers().then((response) => {
        this.users = response.data;
      });
    },
    getNumbers() {
      User.getNumberOfUsers().then((response) => {
        this.numberOfUsers = response.data;
      });
    },

    deleteUser(id) {
      User.deleteUsersById(id).then(() => {
        this.getAllUsers();
        this.getNumbers();
      });
    },
    updateUser(id) {
      this.$router.push(`/updateUser/${id}`);
    },
    addUser() {
      this.$router.push(`/addUser`);
    },
    changePage(pageOfItems) {
      return (this.pageOfItems = pageOfItems);
    },
  },
  created() {
    this.getAllUsers();
    this.getNumbers();
  },
  mounted() {
    UserService.getAdminBoard().then(
      (response) => {
        this.users = response.data;
      },
      (error) => {
        this.users =
          (error.response &&
            error.response.data &&
            error.response.data.message) ||
          error.message ||
          error.toString();
      }
    );
  },

  computed: {
    filteredUsers() {
      return this.users.filter((user) => {
        return user.username.toLowerCase().match(this.search.toLowerCase()) ;
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
