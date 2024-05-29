<script setup lang="ts">
import {useCarsStore} from '@/stores/cars'
import {computed, onMounted, reactive} from 'vue'
import {storeToRefs} from 'pinia'
import VehicleLoader from '@/components/VehicleLoader.vue'
import Vehicle from '@/components/Vehicle.vue'

const store = useCarsStore()
const { items, loading, error } = storeToRefs(store)
const { fetchList } = store

onMounted(() => {
  fetchList()
})

const cars = computed(() => {
  return items.value.map((obj) => {
    return reactive({ ...obj, vehicleType: 'car', showDetails: false })
  })
})
</script>

<template>
  <v-container v-if="!loading">
    <Vehicle v-for="car in cars" :key="car.id" :vehicle="car" />
  </v-container>

  <v-container v-else-if="loading">
    <v-row v-for="n in 3" :key="n" class="my-4">
      <VehicleLoader />
    </v-row>
  </v-container>
</template>

<style scoped></style>
