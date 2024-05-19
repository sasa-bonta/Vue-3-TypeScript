import {createRouter, createWebHistory} from 'vue-router'
import Home from "@/views/Home.vue";
import Car from "@/views/Car.vue";
import Bike from "@/views/Bike.vue";
import BikeRent from "@/views/BikeRent.vue";
import CarRent from "@/views/CarRent.vue";

const router = createRouter({
    history: createWebHistory(import.meta.env.BASE_URL),
    routes: [
        {
            path: '/',
            name: 'home',
            component: Home
        },

        {
            path: '/car',
            name: 'car',
            component: Car
        },
        {
            path: '/bike',
            name: 'bike',
            component: Bike
        },

        {
            path: '/rent/car',
            name: 'carRent',
            component: CarRent
        },
        {
            path: '/rent/bike',
            name: 'bikeRent',
            component: BikeRent
        },

    ]
})

export default router
