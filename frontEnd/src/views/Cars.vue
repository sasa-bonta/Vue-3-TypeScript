<script setup lang="ts">
import {useCarsStore} from '@/stores/cars'
import {computed, onMounted, ref} from 'vue'
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
    return ref({ ...obj, vehicleType: 'car', showDetails: false })
  })
})

const brandModel = ref('')
const plateNumber = ref('')
const gearboxOptions = ['Auto', 'Manual']
const gearbox = ref('')
const sortOptions = ['price asc', 'price desc']
const sortOrder = ref(sortOptions[0])
const showRented = ref(false)

const filteredAndSortedCars = computed(() => {
  let filteredCars = cars.value.map((carRef) => carRef.value)

  if (brandModel.value) {
    filteredCars = filteredCars.filter(
      (car) =>
        car.brand.toLowerCase().includes(brandModel.value.toLowerCase()) ||
        car.model.toLowerCase().includes(brandModel.value.toLowerCase())
    )
  }

  if (plateNumber.value) {
    filteredCars = filteredCars.filter((car) =>
      car.vin.toLowerCase().includes(plateNumber.value.toLowerCase())
    )
  }

  if (gearbox.value) {
    filteredCars = filteredCars.filter(
      (car) => car.transmission.toLowerCase() === gearbox.value.toLowerCase()
    )
  }

  if (!showRented.value) {
    filteredCars = filteredCars.filter((car) => car.available)
  }

  if (sortOrder.value === 'price asc') {
    filteredCars.sort((a, b) => a.price - b.price)
  } else if (sortOrder.value === 'price desc') {
    filteredCars.sort((a, b) => b.price - a.price)
  }

  return filteredCars
})
</script>

<template>
  <v-container>
    <v-row class="my-6 bg-grey-darken-4">
      <v-col>
        <v-text-field v-model="brandModel" label="Brand, model" required></v-text-field>
      </v-col>

      <v-col>
        <v-text-field v-model="plateNumber" label="Plate number" required></v-text-field>
      </v-col>

      <v-col>
        <v-select v-model="gearbox" :items="gearboxOptions" label="Gear box" required></v-select>
      </v-col>

      <v-col>
        <v-select v-model="sortOrder" :items="sortOptions" label="Sort by" required></v-select>
      </v-col>

      <v-col>
        <v-checkbox v-model="showRented" label="Show also not available" required></v-checkbox>
      </v-col>
    </v-row>

    <div v-if="!loading">
      <Vehicle v-for="car in filteredAndSortedCars" :key="car.id" :vehicle="car" />
    </div>

    <div v-else-if="loading">
      <v-row v-for="n in 3" :key="n" class="my-4">
        <VehicleLoader />
      </v-row>
    </div>
  </v-container>
</template>

<style scoped></style>
