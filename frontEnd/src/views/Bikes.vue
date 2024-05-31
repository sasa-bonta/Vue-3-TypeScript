<script setup lang="ts">
import {useBikesStore} from '@/stores/bikes'
import {storeToRefs} from 'pinia'
import {computed, onMounted, ref} from 'vue'
import VehicleLoader from '@/components/VehicleLoader.vue'
import Vehicle from '@/components/Vehicle.vue'

const store = useBikesStore()
const { items, loading, error } = storeToRefs(store)
const { fetchList } = store

onMounted(() => {
  fetchList()
})

const bikes = computed(() => {
  return items.value.map((obj) => {
    return ref({ ...obj, vehicleType: 'bike', showDetails: false })
  })
})

const brandModel = ref('')
const plateNumber = ref('')
const backSeatOptions = ['Yes', 'No']
const backSeat = ref(null)
const sortOptions = ['price asc', 'price desc']
const sortOrder = ref(sortOptions[0])
const showRented = ref(false)

const filteredAndSortedBikes = computed(() => {
  let filteredBikes = bikes.value.map((bikeRef) => bikeRef.value)

  if (brandModel.value) {
    filteredBikes = filteredBikes.filter(
      (bike) =>
        bike.brand.toLowerCase().includes(brandModel.value.toLowerCase()) ||
        bike.model.toLowerCase().includes(brandModel.value.toLowerCase())
    )
  }

  if (plateNumber.value) {
    filteredBikes = filteredBikes.filter((bike) =>
      bike.vin.toLowerCase().includes(plateNumber.value.toLowerCase())
    )
  }

  if (backSeat.value) {
    const option = backSeat.value! === backSeatOptions[0]
    filteredBikes = filteredBikes.filter((bike) => bike.backSeat === option)
  }

  if (!showRented.value) {
    filteredBikes = filteredBikes.filter((bike) => bike.available)
  }

  if (sortOrder.value === 'price asc') {
    filteredBikes.sort((a, b) => a.price - b.price)
  } else if (sortOrder.value === 'price desc') {
    filteredBikes.sort((a, b) => b.price - a.price)
  }

  return filteredBikes
})
</script>

<template>
  <v-container>
    <v-row class="my-6 bg-grey-darken-4">
      <v-col md="3" sm="6" xs="12">
        <v-text-field v-model="brandModel" label="Brand, model" />
      </v-col>

      <v-col md="3" sm="6" cols="12">
        <v-text-field v-model="plateNumber" label="Number plate" />
      </v-col>

      <v-col md="3" sm="6" cols="12">
        <v-select v-model="backSeat" :items="backSeatOptions" label="Back seat" />
      </v-col>

      <v-col md="3" sm="6" cols="12">
        <v-select v-model="sortOrder" :items="sortOptions" label="Sort by" />
      </v-col>

      <v-col md="3" sm="6" cols="12">
        <v-checkbox v-model="showRented" label="Show also not available" />
      </v-col>
    </v-row>

    <v-card width="1280" v-if="!filteredAndSortedBikes.length && !loading" />

    <div v-if="!loading">
      <Vehicle v-for="bike in filteredAndSortedBikes" :key="bike.id" :vehicle="bike" />
    </div>

    <div v-else-if="loading">
      <v-row v-for="n in 3" :key="n" class="my-4">
        <VehicleLoader />
      </v-row>
    </div>

    <v-row class="my-6 bg-grey-darken-4 justify-center">
      <v-col cols="auto" class="d-flex justify-center">
        <v-btn
          variant="tonal"
          color="teal-lighten-4"
          text="Create new bike"
          @click="$router.push({ name: 'createBike' })"
        />
      </v-col>
    </v-row>
  </v-container>
</template>

<style scoped></style>
