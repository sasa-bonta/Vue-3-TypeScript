<script setup lang="ts">
import car from '@/assets/images/car.jpg'
import bike from '@/assets/images/bike.jpg'
</script>

<template>
  <router-link :to="{ name: 'car' }">
    <v-img :width="380" cover :src="car"></v-img>
  </router-link>

  <router-link :to="{ name: 'bike' }">
    <v-img :width="380" cover :src="bike"></v-img>
  </router-link>
</template>

<style scoped></style>
