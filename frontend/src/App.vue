<template>
  <div id="app">
    <!-- add fixed-top after navbar-->

    <nav class="navbar fixed-top navbar-expand">
      <div class="ms-auto">
        <div class="navbar-nav">
          <li v-if="showAdminBoard" class="nav-item">
            <router-link to="/admin" class="nav-link">Utilizatori</router-link>
          </li>
         
          <li v-if="showAdminBoard" class="nav-item">
            <router-link to="programs" class="nav-link"
              >Cursuri</router-link
            >
          </li>
           <li v-if="showAdminBoard" class="nav-item">
            <router-link to="admin-profile" class="nav-link"
              >Profil</router-link
            >
          </li>
          <li v-if="showAdminBoard" class="nav-item">
            <a class="nav-link" href @click.prevent="logOut"> Iesi din cont </a>
          </li>
        </div>
      </div>

      <div class="ms-auto">
        <div v-if="!currentUser" class="navbar-nav">
          <li class="nav-item">
            <router-link to="/home" class="nav-link"> Acasa </router-link>
          </li>

          <li class="nav-item">
            <router-link to="/program" class="nav-link"> Cursuri </router-link>
          </li>


          <li class="nav-item">
            <router-link to="/trainers-details" class="nav-link">
              Antrenori
            </router-link>
          </li>

          <li class="nav-item">
            <router-link to="/about" class="nav-link"> Despre noi </router-link>
          </li>

          <li class="nav-item">
            <router-link to="/contact" class="nav-link"> Contact </router-link>
          </li>

          <li class="nav-item">
            <router-link to="/register" class="nav-link">
              Creeaza cont
            </router-link>
          </li>
          <li class="nav-item">
            <router-link to="/login" class="nav-link">
              Intra in cont
            </router-link>
          </li>
        </div>
      </div>
      <div v-if="currentUser && !showAdminBoard" class="navbar-nav">
        <li class="nav-item">
          <router-link to="/home" class="nav-link"> Acasa </router-link>
        </li>

        <li class="nav-item">
            <router-link to="/program" class="nav-link"> Cursuri </router-link>
          </li>

        <li class="nav-item">
          <router-link to="/trainers-details" class="nav-link">
            Antrenori
          </router-link>
        </li>

        <li class="nav-item">
          <router-link to="/about" class="nav-link"> Despre noi </router-link>
        </li>

        <li class="nav-item">
          <router-link to="/contact" class="nav-link"> Contact </router-link>
        </li>

 <div v-if ="currentUser.roles == 'ROLE_USER'">
        <li  class="nav-item">
          <router-link to="/profile" class="nav-link">
            Profil
          </router-link>
        </li>
 </div>
 <div v-else-if="currentUser.roles == 'ROLE_TRAINER'">
    <li  class="nav-item">
          <router-link to="/trainerProfile" class="nav-link">
            Profil
          </router-link>
        </li>
 </div>
        <li class="nav-item py-7 px-8">
          <a class="nav-link" href @click.prevent="logOut"> Iesi din cont </a>
        </li>
      </div>
    </nav>

    <div>
      <router-view />
    </div>
  </div>
</template>

<script>
export default {
  data() {
    return {
      isOpen: false,
    };
  },

  computed: {
    currentUser() {
      return this.$store.state.auth.user;
    },
    showAdminBoard() {
      if (this.currentUser && this.currentUser.roles) {
        return this.currentUser.roles.includes("ROLE_ADMIN");
      }
      return false;
    },
  },
  methods: {
    logOut() {
      this.$store.dispatch("auth/logout");
      this.$router.push("/login");
    },
  },
};
</script>

<style>
nav {
  width: 100%;
  background-color: #0f0f0f;
  align-items: center;
  justify-content: center;
  display: flex;
  max-width: 100%;
}
nav .nav-item {
  color: rgb(252, 248, 248);
  padding: 0px 40px 0px 0px;
  position: relative;

  border-bottom: 5px solid transparent;
  display: flex;
  transition: 0.4s;
  font-size: 20px;
  font-family: serif;
}

nav .nav-item-programs {
  color: rgb(250, 243, 243);
  padding: 8px 20px 3px;
  position: relative;
  /*text-align: center;*/
  border-bottom: 5px solid transparent;
  display: inline;
  transition: 0.4s;
  font-size: 20px;
  font-family: serif;
}

nav .nav-link.active,
nav .nav-link:hover,
nav .nav-link:visited {
  background-color: #131212;
  color: rgb(238, 238, 238);
}
nav .nav-item a {
  color: inherit;
  text-decoration: none;
}

nav .nav-item .sub-menu {
  position: absolute;
  background-color: rgb(7, 6, 6);
  top: calc(100% + 5px);
  left: 50%;
  transform: translateX(-50%);
  width: max-content;
  border-radius: 0px 0px 16px 16px;
}

.fade-enter-active,
.fade-leave-active {
  transition: all 0.5s ease-out;
}
.fade-enter,
.fade-leave-to {
  opacity: 0;
}
.sub-menu {
  cursor: pointer;
}
nav .nav-item svg {
  width: 10px;
  margin-left: 0px;
  height: 45px;
}
</style>
