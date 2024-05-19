<script setup lang="ts">
import bikeIcon from '@/assets/images/car.jpg';

import {onMounted} from 'vue'
import {useBikeStore} from "@/stores/bikes";
import {storeToRefs} from "pinia";


const bikeStore = useBikeStore()

const {bikes, loading, error} = storeToRefs(bikeStore)

onMounted(() => {
  bikeStore.fetchBikeList()
})

</script>

<template>

  <v-container id="bikes-container">

    <v-row class="mx-0">
      <v-btn variant="outlined" class="mb-2 mt-2 button-border w-100" color="orange">
        <h3><b>Create new bike</b></h3>
      </v-btn>
    </v-row>
    <v-row class="mx-0 mb-5">
      <v-btn variant="outlined" class="mb-2 mt-2 button-border w-100" color="purple">
        <h3><b>Go to rents</b></h3>
      </v-btn>
    </v-row>

    <div class="mb-10 bg-surface-variant mb-6 px-4 py-4" v-for="bike in bikes" :key="bike.id">
      <v-row>
        <v-col cols="4">
          <v-row>
            <v-img
                :width="250"
                :max-height="250"
                cover
                :src="bikeIcon"
            ></v-img>
          </v-row>
          <v-row :class="bike.state.available ? 'text-green' : 'text-red'" class="ml-10">
            <h2><b>{{ bike.state.available ? 'Available' : 'Not Available' }}</b></h2>
          </v-row>
        </v-col>
        <v-col cols="8">
          <v-row class="ml-3 mr-3">
            <h1><b>{{ `${bike.brand} ${bike.model} ${bike.year}` }}</b></h1>
            <v-spacer/>
            <h1><b>{{ bike.price }} EUR</b></h1>
          </v-row>
          <v-row class="ml-10">
            <v-col cols="4">
              <h3>Engine: {{ bike.engine }} <br>
                Power (HP): {{ bike.power }} <br>
                Fuel: {{ bike.fuel }} <br>
                Transmission: {{ bike.transmission }} <br>
                Type: {{ bike.type }}</h3>
            </v-col>

            <v-col cols="4">
              <h3>Street legal: {{ bike.streetLegal}} <br>
                Nr. of seats: {{ bike.backSeat}} <br>
                VIN: {{ bike.vin }} <br>
                ID: {{ bike.id }}</h3>
            </v-col>

            <v-col cols="4">
              <v-btn variant="outlined" class="mb-2 button-border w-100" color="blue" :disabled="!bike.state.available">
                <h3><b>rent</b></h3>
              </v-btn> <br>
              <v-btn variant="outlined" class="mb-2 button-border w-100" color="yellow">
                <h3><b>duplicate</b></h3>
              </v-btn> <br>
              <v-btn variant="outlined" class="mb-2 button-border w-100" color="red">
                <h3><b>delete</b></h3>
              </v-btn>
            </v-col>
          </v-row>
        </v-col>
      </v-row>
    </div>
  </v-container>
</template>

<style scoped>
#bikes-container {
  width: 1280px;
}
.button-border {
  border-width: 3px;
}
</style>