<script setup lang="ts">
import carIcon from '@/assets/images/car.jpg'

import { computed, type ComputedRef, onMounted, type Ref, ref, type UnwrapRef } from 'vue'
import { useCarStore } from '@/stores/cars'
import { storeToRefs } from 'pinia'
import type { Car } from '@/interfaces/api'
import { deleteCarById } from '@/api/api'

const carStore = useCarStore()

const { cars, loading, error } = storeToRefs(carStore)

onMounted(() => {
  carStore.fetchCarList()
})

const showAvailable = ref(true)
const sortOrder: Ref<UnwrapRef<'asc' | 'desc'>> = ref('asc')
const toggleSort = () => {
  sortOrder.value = sortOrder.value === 'asc' ? 'desc' : 'asc'
}

const filteredAndSortedCars: ComputedRef<Array<Car>> = computed(() => {
  let filteredCars = cars.value.filter((car: Car) => {
    const isAvailable = showAvailable.value ? car.available : true
    const isNotDeleted = !car.deleted
    return isAvailable && isNotDeleted
  })

  if (sortOrder.value === 'asc') {
    filteredCars.sort((a, b) => a.price - b.price)
  } else {
    filteredCars.sort((a, b) => b.price - a.price)
  }

  return filteredCars
})

const deleteCar = async (id: number) => {
  const isConfirmed = confirm('Are you sure you want to delete this car?')

  if (!isConfirmed) {
    return
  }

  try {
    await deleteCarById(id)
    await carStore.fetchCarList()
  } catch (error) {
    console.error('Failed to delete car:', error)
  }
}
</script>

<template>
  <v-container>
    <div class="mb-6 px-6 pt-6 pb-0 bg-surface-variant">
      <v-row>
        <v-col cols="10">
          <v-btn
            variant="outlined"
            class="mb-2 mt-2 button-border w-100"
            color="light-green-accent-3"
            @click="toggleSort"
          >
            <h3>
              <b>Sort by price {{ sortOrder }}</b>
            </h3>
          </v-btn>
        </v-col>

        <v-col cols="2">
          <v-checkbox label="Hide not available" v-model="showAvailable"></v-checkbox>
        </v-col>
      </v-row>
    </div>

    <v-row class="mx-0">
      <router-link :to="{ name: 'carCreate' }" class="w-100">
        <v-btn variant="outlined" class="mb-2 mt-2 button-border w-100" color="orange">
          <h3><b>Create new car</b></h3>
        </v-btn>
      </router-link>
    </v-row>
    <v-row class="mx-0 mb-5">
      <router-link :to="{ name: 'carRent' }" class="w-100">
        <v-btn variant="outlined" class="mb-2 mt-2 button-border w-100" color="purple">
          <h3><b>Go to rents</b></h3>
        </v-btn>
      </router-link>
    </v-row>

    <div
      class="bg-surface-variant mb-6 px-4 py-4"
      v-for="car in filteredAndSortedCars"
      :key="car.id"
    >
      <v-row>
        <v-col cols="4">
          <v-row>
            <v-img :width="250" :max-height="250" cover :src="carIcon"></v-img>
          </v-row>
          <v-row :class="car.state.available ? 'text-green' : 'text-red'" class="ml-10">
            <h2>
              <b>{{ car.state.available ? 'Available' : 'Not Available' }}</b>
            </h2>
          </v-row>
        </v-col>

        <v-col cols="8">
          <v-row class="ml-3 mr-3">
            <h1>
              <b>{{ `${car.brand} ${car.model} ${car.year}` }}</b>
            </h1>
            <v-spacer />
            <h1>
              <b>{{ car.price }} EUR</b>
            </h1>
          </v-row>
          <v-row class="ml-10">
            <v-col cols="4">
              <h3>
                Engine: {{ car.engine }} <br />
                Power (HP): {{ car.power }} <br />
                Fuel: {{ car.fuel }} <br />
                Transmission: {{ car.transmission }} <br />
                Type: {{ car.type }}
              </h3>
            </v-col>

            <v-col cols="4">
              <h3>
                Drivetrain: {{ car.drivetrain }} <br />
                Nr. of seats: {{ car.seats }} <br />
                VIN: {{ car.vin }} <br />
                ID: {{ car.id }}
              </h3>
            </v-col>

            <v-col cols="4">
              <v-btn
                variant="outlined"
                class="mb-2 button-border w-100"
                color="blue"
                @click="$router.push({ name: 'carCreateRent', params: { id: car.id } })"
                :disabled="!car.state.available"
              >
                <h3><b>rent</b></h3>
              </v-btn>
              <v-btn variant="outlined" class="mb-2 button-border w-100" color="yellow">
                <h3><b>duplicate</b></h3>
              </v-btn>
              <v-btn
                variant="outlined"
                class="mb-2 button-border w-100"
                color="red"
                @click="deleteCar(car.id)"
                :disabled="!car.state.available"
              >
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
.button-border {
  border-width: 3px;
}
</style>
