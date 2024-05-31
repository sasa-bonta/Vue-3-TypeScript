<script setup lang="ts">
import {useRentsStore} from '@/stores/rents'
import {storeToRefs} from 'pinia'
import {computed, onMounted, ref} from 'vue'
import RentLoader from '@/components/RentLoader.vue'
import Rent from '@/components/Rent.vue'

const store = useRentsStore()
const { items, loading, error } = storeToRefs(store)
const { fetchList } = store

onMounted(() => {
  fetchList()
})

const rents = computed(() => {
  return items.value.map((obj) => {
    return ref({ ...obj, showDetails: false })
  })
})

const idnp = ref('')
const brandModel = ref('')
const plateNumber = ref('')
const sortOptions = ['Start date asc', 'Start date desc', 'End date asc', 'End date desc']
const sortOrder = ref(sortOptions[1])
const showEnded = ref(false)

const filteredAndSortedRents = computed(() => {
  let filteredRents = rents.value.map((rentRef) => rentRef.value)

  if (idnp.value) {
    filteredRents = filteredRents.filter((rent) =>
      rent.idnp.toLowerCase().includes(idnp.value.toLowerCase())
    )
  }

  if (brandModel.value) {
    filteredRents = filteredRents.filter(
      (rent) =>
        rent.vehicle.brand.toLowerCase().includes(brandModel.value.toLowerCase()) ||
        rent.vehicle.model.toLowerCase().includes(brandModel.value.toLowerCase())
    )
  }

  if (plateNumber.value) {
    filteredRents = filteredRents.filter((rent) =>
      rent.vehicle.vin.toLowerCase().includes(plateNumber.value.toLowerCase())
    )
  }

  if (!showEnded.value) {
    filteredRents = filteredRents.filter((rent) => !rent.finishDate)
  }

  if (sortOrder.value === sortOptions[0]) {
    filteredRents.sort((a, b) => new Date(a.startDate).getTime() - new Date(b.startDate).getTime())
  } else if (sortOrder.value === sortOptions[1]) {
    filteredRents.sort((a, b) => new Date(b.startDate).getTime() - new Date(a.startDate).getTime())
  } else if (sortOrder.value === sortOptions[2]) {
    filteredRents.sort(
      (a, b) => new Date(a.finishDate).getTime() - new Date(b.finishDate).getTime()
    )
  } else if (sortOrder.value === sortOptions[3]) {
    filteredRents.sort(
      (a, b) => new Date(b.finishDate).getTime() - new Date(a.finishDate).getTime()
    )
  }

  return filteredRents
})
</script>

<template>
  <v-container>
    <v-row class="my-6 bg-grey-darken-4">
      <v-col md="3" sm="6" cols="12">
        <v-text-field v-model="idnp" label="First name / Last name / idnp" />text-field>
      </v-col>

      <v-col md="3" sm="6" cols="12">
        <v-text-field v-model="brandModel" label="Brand, model" />text-field>
      </v-col>

      <v-col md="3" sm="6" cols="12">
        <v-text-field v-model="plateNumber" label="Number plate" />text-field>
      </v-col>

      <v-col md="3" sm="6" cols="12">
        <v-select v-model="sortOrder" :items="sortOptions" label="Sort by" />select>
      </v-col>

      <v-col md="3" sm="6" cols="12">
        <v-checkbox v-model="showEnded" label="Show also ended rents" />checkbox>
      </v-col>
    </v-row>

    <v-card width="1280" v-if="!filteredAndSortedRents.length && !loading" />card>

    <div v-if="!loading">
      <Rent v-for="rent in filteredAndSortedRents" :key="rent.type! + rent.id" :rent="rent" />
    </div>

    <div v-else-if="loading">
      <v-row v-for="n in 3" :key="n" class="my-4">
        <RentLoader />
      </v-row>
    </div>
  </v-container>
</template>

<style scoped></style>
