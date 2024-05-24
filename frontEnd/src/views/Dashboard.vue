<script setup lang="ts">
import { RouterView } from 'vue-router'
import logo from '@/assets/car-rent-icon.svg'
import { useDisplay } from 'vuetify'

const { xs, smAndUp } = useDisplay()
</script>

<template>
  <v-layout class="rounded rounded-md" app>
    <v-app-bar color="grey-darken-3">
      <v-app-bar-nav-icon v-if="xs"></v-app-bar-nav-icon>

      <v-app-bar-title v-if="xs">Vehicle for Vacation</v-app-bar-title>

      <v-btn
        icon
        variant="tonal"
        size="large"
        @click="$router.push({ name: 'home' })"
        class="ml-10"
      >
        <v-icon style="height: 35px; width: 35px">
          <v-img :src="logo" alt="Home" />
        </v-icon>
      </v-btn>

      <v-app-bar-title v-if="!xs">Vehicle for Vacation</v-app-bar-title>

      <div v-if="smAndUp">
        <v-btn
          variant="tonal"
          :disabled="$route.name === 'cars'"
          class="mx-6"
          prepend-icon="mdi-car-sports"
          @click="$router.push({ name: 'cars' })"
          >Cars
        </v-btn>
        <v-btn
          variant="tonal"
          :disabled="$route.name === 'bikes'"
          class="mx-6"
          prepend-icon="mdi-motorbike"
          @click="$router.push({ name: 'bikes' })"
          >Bikes
        </v-btn>
        <v-btn
          variant="tonal"
          :disabled="$route.name === 'home'"
          class="mx-6"
          prepend-icon="mdi-format-list-bulleted"
          @click="$router.push({ name: 'home' })"
          >Rents
        </v-btn>
      </div>
    </v-app-bar>

    <v-main class="d-flex align-center justify-center" style="min-height: 300px">
      <RouterView />
    </v-main>
  </v-layout>
</template>

<style scoped></style>
