import Vue from 'vue';
import Router from 'vue-router';
import Home from './views/Home.vue';
import Login from './views/Login.vue';
import Register from './views/Register.vue';
import Contact from './views/Contact.vue';
import Trainers from './views/Trainers.vue';
import OnlineClasses from './views/OnlineClasses.vue';
import Memberships from './views/Memberships.vue';
import About from './views/About.vue';
import OnlineAppointment from './views/OnlineAppointment.vue';





Vue.use(Router);

function getAdminAccess(to, from, next) {

  var loggedIn = false;

  if (localStorage.getItem('user')) {
    loggedIn = true;
  }
  else {
    loggedIn = false;

  }

  if (loggedIn && JSON.parse(localStorage.getItem('user')).roles == 'ROLE_ADMIN') {
    next();

  }
  else {
    next('/login');
  }
}


export const router = new Router({
  mode: 'history',
  routes: [
    {
      path: '/',
      name: 'home',
      component: Home
    },
    {
      path: '/home',
      component: Home
    },
    {
      path: '/login',
      component: Login
    },
    {
      path: '/register',
      component: Register
    },
    {
      path: '/contact',
      component: Contact
    },
    {
      path: '/trainersDetails',
      component: Trainers
    },
    {
      path: '/about',
      component: About
    },
    {
      path: '/programs/onlineClasses',
      component: OnlineClasses
    },
    {
      path: '/programs/memberships',
      component: Memberships
    },
    {
      path: '/programs/onlineClasses/appointment',
      component: OnlineAppointment
    },
      {
      path: '/profile',
      name: 'profile',
      // lazy-loaded
      component: () => import('./views/Profile.vue'),

    },
    {
      path: '/admin',
      name: 'admin',
      // lazy-loaded
      component: () => import('./views/BoardUsers.vue'),
      beforeEnter: getAdminAccess,

    },
    {
      path: '/trainers',
      name: 'trainers',
      // lazy-loaded
      component: () => import('./views/BoardTrainers.vue'),
      beforeEnter: getAdminAccess,

    },

    {
      path: '/programs',
      name: 'programs',
      // lazy-loaded
      component: () => import('./views/BoardPrograms.vue'),
      beforeEnter: getAdminAccess,

    },


    {
      path: "/updateUser/:id",
      name: "Update User",
      component: () => import('./views/UpdateUser.vue'),
      beforeEnter: getAdminAccess,
    },
    {
      path: "/addUser/",
      name: "Add User",
      component: () => import('./views/AddUser.vue'),
      beforeEnter: getAdminAccess,

    },

    {
      path: "/updateTrainer/:id",
      name: "Update Trainer",
      component: () => import('./views/UpdateTrainer.vue'),
      beforeEnter: getAdminAccess,
    },

    {
      path: "/updateProgram/:id",
      name: "Update Program",
      component: () => import('./views/UpdateProgram.vue'),
      beforeEnter: getAdminAccess,
    },
    {
      path: "/programs/onlineClasses/appointment/:id",
      component: () => import('./views/ClassAppointment.vue'),
    },
    {
      path: "/addTrainer/",
      name: "Add Trainer",
      component: () => import('./views/AddTrainer.vue'),
      beforeEnter: getAdminAccess,
    },

    {
      path: "/addProgram/",
      name: "Add Program",
      component: () => import('./views/AddProgram.vue'),
      beforeEnter: getAdminAccess,
    },




  ]
});
/*
 router.beforeEach((to, from, next) => {
   const publicPages = ['/login', '/register', '/home'];
   const authRequired = !publicPages.includes(to.path);
  const loggedIn = localStorage.getItem('user');

   // trying to access a restricted page + not logged in
   // redirect to login page
   if (authRequired && !loggedIn) {
     next('/login');
   } else {
    next();
   }
   
 });*/
