<script setup lang="ts">
import { RouterView } from 'vue-router'
import Navbar from '@/components/Navbar.vue'
</script>

<template>
  <v-layout class="rounded rounded-md" app>
    <Navbar />

    <v-main class="d-flex align-center justify-center" style="min-height: 300px; width: 100%">
      <RouterView />
    </v-main>
  </v-layout>
</template>

<style scoped></style>
