import {createRouter, createWebHistory} from 'vue-router'
import Home from '@/views/Home.vue'
import Cars from '@/views/Cars.vue'
import Bikes from '@/views/Bikes.vue'
import CreateRent from '@/views/CreateRent.vue'
import Rents from '@/views/Rents.vue'
import CreateCar from '@/views/CreateCar.vue'
import CreateBike from '@/views/CreateBike.vue'

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
      path: '/create-car',
      name: 'createCar',
      component: CreateCar
    },
    {
      path: '/bikes',
      name: 'bikes',
      component: Bikes
    },
    {
      path: '/create-bike',
      name: 'createBike',
      component: CreateBike
    },
    {
      path: '/create-rent/:vehicleType/:id',
      name: 'createRent',
      component: CreateRent
    },
    {
      path: '/rents',
      name: 'rents',
      component: Rents
    }
  ]
})

export default router
