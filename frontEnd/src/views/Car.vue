<script setup lang="ts">
import carIcon from '@/assets/images/car.jpg';

import {onMounted} from 'vue'
import {useCarStore} from "@/stores/cars";
import {storeToRefs} from "pinia";


const carStore = useCarStore()

const {cars, loading, error} = storeToRefs(carStore)

onMounted(() => {
  carStore.fetchCarList()
})

</script>

<template>

  <v-btn variant="outlined" class="mb-2 mt-2 button-border" color="purple">
    <h3><b>Create new car</b></h3>
  </v-btn> <br>

  <v-container id="cars-container" class="bg-surface-variant mb-6">
    <div class="mb-10" v-for="car in cars" :key="car.id">
      <v-row>
        <v-col cols="4">
          <v-row>
            <v-img
                :width="250"
                :max-height="250"
                cover
                :src="carIcon"
            ></v-img>
          </v-row>
          <v-row :class="car.state.available ? 'text-green' : 'text-red'" class="ml-10">
            <h2><b>{{ car.state.available ? 'Available' : 'Not Available' }}</b></h2>
          </v-row>
        </v-col>
        <v-col cols="8">
          <v-row class="ml-3 mr-3">
            <h1><b>{{ `${car.brand} ${car.model} ${car.year}` }}</b></h1>
            <v-spacer/>
            <h1><b>{{ car.price }} EUR</b></h1>
          </v-row>
          <v-row class="ml-10">
            <v-col cols="5">
              <h3>Engine: {{ car.engine }} <br>
                Power (HP): {{ car.power }} <br>
                Fuel: {{ car.fuel }} <br>
                Transmission: {{ car.transmission }} <br>
                Type: {{ car.type }}</h3>
            </v-col>

            <v-col cols="5">
              <h3>Drivetrain: {{ car.drivetrain }} <br>
                Nr. of seats: {{ car.seats }} <br>
                VIN: {{ car.vin }} <br>
                ID: {{ car.id }}</h3>
            </v-col>

            <v-col cols="2">
              <v-btn variant="outlined" class="mb-2 button-border" color="blue" :disabled="!car.state.available">
                <h3><b>rent</b></h3>
              </v-btn> <br>
              <v-btn variant="outlined" class="mb-2 button-border" color="yellow">
                <h3><b>duplicate</b></h3>
              </v-btn> <br>
              <v-btn variant="outlined" class="mb-2 button-border" color="red">
                <h3><b>delete</b></h3>
              </v-btn>
            </v-col>
          </v-row>
        </v-col>
      </v-row>
      <v-divider class="my-4"/>
    </div>
  </v-container>
</template>

<style scoped>
#cars-container {
  width: 1280px;
}
.button-border {
  border-width: 3px;
}
</style>