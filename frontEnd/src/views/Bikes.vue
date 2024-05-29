<script setup lang="ts">
import { useBikesStore } from '@/stores/bikes'
import { storeToRefs } from 'pinia'
import { computed, onMounted, reactive } from 'vue'
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
    return reactive({ ...obj, showDetails: false })
  })
})
</script>

<template>
  <v-container v-if="!loading">
    <Vehicle v-for="bike in bikes" :key="bike.id" :vehicle="bike" vehicleType="bike" />
  </v-container>

  <v-container v-else>
    <v-row v-for="n in 3" :key="n" class="my-4">
      <VehicleLoader />
    </v-row>
  </v-container>
</template>

<style scoped></style>
