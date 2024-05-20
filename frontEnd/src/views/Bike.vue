<script setup lang="ts">
import bikeIcon from '@/assets/images/bike.jpg';

import {computed, type ComputedRef, onMounted, type Ref, ref, type UnwrapRef} from 'vue'
import {useBikeStore} from "@/stores/bikes";
import {storeToRefs} from "pinia";
import type {Bike} from "@/stores/Interfaces";


const bikeStore = useBikeStore()

const {bikes, loading, error} = storeToRefs(bikeStore)

onMounted(() => {
  bikeStore.fetchBikeList()
})

const showAvailable = ref(true)
const sortOrder: Ref<UnwrapRef<'asc' | 'desc'>> = ref('asc')
const toggleSort = () => {
  sortOrder.value = (sortOrder.value === 'asc') ? 'desc' : 'asc'
}

const filteredAndSortedBikes: ComputedRef<Array<Bike>> = computed(() => {
  let filteredBikes = bikes.value.filter((bike: Bike) => {
    return showAvailable.value ? bike.available : true;
  });

  if (sortOrder.value === 'asc') {
    filteredBikes.sort((a, b) => a.price - b.price);
  } else {
    filteredBikes.sort((a, b) => b.price - a.price);
  }

  return filteredBikes;
});

</script>

<template>

  <v-container id="bikes-container">

    <div class="mb-6 px-6 pt-6 pb-0 bg-surface-variant">
      <v-row>
        <v-col cols="10">
          <v-btn variant="outlined" class="mb-2 mt-2 button-border w-100" color="light-green-accent-3" @click="toggleSort">
            <h3><b>Sort by price {{ sortOrder }}</b></h3>
          </v-btn>
        </v-col>

        <v-col cols="2">
          <v-checkbox label="Hide not available" v-model="showAvailable"></v-checkbox>
        </v-col>
      </v-row>
    </div>

    <v-row class="mx-0">
      <v-btn variant="outlined" class="mb-2 mt-2 button-border w-100" color="orange">
        <h3><b>Create new bike</b></h3>
      </v-btn>
    </v-row>
    <v-row class="mx-0 mb-5">
      <router-link :to="{ name: 'bikeRent'}" class="w-100">
        <v-btn variant="outlined" class="mb-2 mt-2 button-border w-100" color="purple">
          <h3><b>Go to rents</b></h3>
        </v-btn>
      </router-link>
    </v-row>

    <div class="bg-surface-variant mb-6 px-4 py-4" v-for="bike in filteredAndSortedBikes" :key="bike.id">
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