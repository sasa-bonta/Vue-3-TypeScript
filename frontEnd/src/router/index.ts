import { createRouter, createWebHistory } from 'vue-router'
import Home from '@/views/Home.vue'
import Cars from '@/views/Cars.vue'
import Bikes from '@/views/Bikes.vue'
import CreateRent from '@/views/CreateRent.vue'

const router = createRouter({
  history: createWebHistory(import.meta.env.BASE_URL),
  routes: [
    {
      path: '/',
      name: 'home',
      component: Home
    },
    {
      path: '/cars',
      name: 'cars',
      component: Cars
    },
    {
      path: '/bikes',
      name: 'bikes',
      component: Bikes
    },
    {
      path: '/create-rent/:vehicleType/:id',
      name: 'createRent',
      component: CreateRent
    }
  ]
})

export default router
