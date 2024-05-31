<script setup lang="ts">
import {RouterView} from 'vue-router'
import Navbar from '@/components/Navbar.vue'
import Snackbar from '@/components/Snackbar.vue'
import Confirm from '@/components/Confirm.vue'
</script>

<template>
  <v-layout class="rounded rounded-md" app>
    <Navbar />

    <v-main class="d-flex align-center justify-center" style="min-height: 300px; width: 100%">
      <RouterView />
    </v-main>
  </v-layout>

  <Confirm />
  <Snackbar />
</template>

<style scoped></style>
