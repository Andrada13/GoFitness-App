<template>
  <div id="app">
    <!-- add fixed-top after navbar-->
    <nav class="navbar navbar-expand">
      <div class="navbar-nav mr-auto">
        <li v-if="showAdminBoard" class="nav-item">
          <router-link to="/admin" class="nav-link">Users Board</router-link>
        </li>
        <li v-if="showAdminBoard" class="nav-item">
          <router-link to="/trainers" class="nav-link"
            >Trainers Board</router-link
          >
        </li>
        <li v-if="showAdminBoard" class="nav-item">
          <router-link to='programs' class="nav-link"
            >Programs Board</router-link
          >
        </li>
      </div>

      <div v-if="!currentUser" class="navbar-nav ml-auto ">

        <li class="nav-item">
          <router-link to="/home" class="nav-link"> Acasa </router-link>
        </li>

        <div class="nav-item" @click="isOpen = !isOpen">
          <li class="nav-item-programs">
              Antrenamente
          </li>

          <svg viewBox="0 0 1030 638" width="5">
            <path
              d="M1017 68L541 626q-11 12-26 12t-26-12L13 68Q-3 49 6 24.5T39 0h952q24 0 33 24.5t-7 43.5z"
              fill="#FFF"
            ></path>
          </svg>

          <transition name="fade" apear>
            <div class="sub-menu" v-if="isOpen">
              <div class="nav-item">
                <router-link to="/programs/onlineClasses"
                  >Antrenamente Online</router-link
                >
              </div>
              <div class="nav-item">
                <router-link to="/programs/memberships"
                  >Abonamente</router-link
                >
              </div>
            </div>
          </transition>
        </div>

        <li class="nav-item">
          <router-link to="/trainersDetails" class="nav-link"> Antrenori </router-link>
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
          <router-link to="/login" class="nav-link"> Intra in cont </router-link>
        </li>
      </div>

      <div v-if="currentUser" class="navbar-nav ml-auto">
        <li class="nav-item">
          <router-link to="/profile" class="nav-link">
            {{ currentUser.username }}
          </router-link>
        </li>
        <li class="nav-item">
          <a class="nav-link" href @click.prevent="logOut"> Delogare </a>
        </li>
      </div>
    </nav>

    <div class="container">
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
  display: flex;
  align-items: center;
  justify-content: center;

}
nav .nav-item {
  color: rgb(252, 248, 248);
  padding: 0px 40px 0px;
  position: relative;
  text-align: center; 
  border-bottom: 5px solid transparent;
  display: flex;
  transition: 0.4s;
  font-size: 17px;
  font-family: "Montserrat", sans-serif;
   

}

nav .nav-item-programs {
  color: rgb(250, 243, 243);
  padding: 8px 20px 3px;
  position: relative;
  text-align: center; 
  border-bottom: 5px solid transparent;
  display: flex;
  transition: 0.4s;
  font-size: 17px;
  font-family: "Montserrat", sans-serif;


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
