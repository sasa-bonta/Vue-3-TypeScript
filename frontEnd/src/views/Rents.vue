<script setup lang="ts">
import {useRentsStore} from '@/stores/rents'
import {storeToRefs} from 'pinia'
import {computed, onMounted, reactive} from 'vue'
import Rent from '@/components/Rent.vue'
import RentLoader from '@/components/RentLoader.vue'

const store = useRentsStore()
const { items, loading, error } = storeToRefs(store)
const { fetchList } = store

onMounted(() => {
  fetchList()
})

const rents = computed(() => {
  return items.value.map((obj) => {
    return reactive({ ...obj, showDetails: false })
  })
})
</script>

<template>
  <v-container v-if="!loading">
    <Rent v-for="rent in rents" :key="rent.type! + rent.id" :rent="rent" />
  </v-container>

  <v-container v-else-if="loading">
    <v-row v-for="n in 3" :key="n" class="my-4">
      <RentLoader />
    </v-row>
  </v-container>
</template>

<style scoped></style>
