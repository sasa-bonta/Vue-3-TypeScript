<script setup lang="ts">
import { useRoute } from 'vue-router'
import { computed } from 'vue'
import CreateCarRentForm from '@/components/CreateCarRentForm.vue'
import CreateBikeRentForm from '@/components/CreateBikeRentForm.vue'

const route = useRoute()
const vehicleType = computed(() => route.params.vehicleType)
</script>

<template>
  <v-container v-if="vehicleType === 'car'">
    <CreateCarRentForm />
  </v-container>
  <v-container v-else-if="vehicleType === 'bike'">
    <CreateBikeRentForm />
  </v-container>
</template>

<style scoped></style>
