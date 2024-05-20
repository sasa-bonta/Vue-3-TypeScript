import {createRouter, createWebHistory} from 'vue-router'
import Home from "@/views/Home.vue";
import Car from "@/views/car/Car.vue";
import Bike from "@/views/bike/Bike.vue";
import BikeRent from "@/views/bike/BikeRent.vue";
import CarRent from "@/views/car/CarRent.vue";
import CarCreate from "@/views/car/CarCreate.vue";
import BikeCreate from "@/views/bike/BikeCreate.vue";

const router = createRouter({
    history: createWebHistory(import.meta.env.BASE_URL),
    routes: [
        {
            path: '/',
            name: 'home',
            component: Home
        },

        {
            path: '/bike',
            name: 'bike',
            component: Bike
        },
        {
            path: '/bike/create',
            name: 'bikeCreate',
            component: BikeCreate
        },
        {
            path: '/rent/bike',
            name: 'bikeRent',
            component: BikeRent
        },

        {
            path: '/car',
            name: 'car',
            component: Car
        },
        {
            path: '/car/create',
            name: 'carCreate',
            component: CarCreate
        },
        {
            path: '/rent/car',
            name: 'carRent',
            component: CarRent
        },
    ]
})

export default router
