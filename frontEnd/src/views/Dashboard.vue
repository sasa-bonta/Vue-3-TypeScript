<script setup lang="ts">
import { RouterView } from 'vue-router'
import logo from '@/assets/car-rent-icon.svg'
import { useDisplay } from 'vuetify'
import { watch } from 'vue'

const {
  mobile,
  smAndDown, // < 960px
  smAndUp, // > 600px
  mdAndDown, // < 1280px
  mdAndUp, // > 960px
  lgAndDown, // < 1919px
  lgAndUp, // > 1280px
  xlAndDown, // < 2559px
  xlAndUp // > 1920px
} = useDisplay()

// watch(mobile, (newValue, oldValue) => {
//   alert(newValue)
// })
</script>

<template>
  <v-layout class="rounded rounded-md" app>
    <v-app-bar color="grey-darken-3">
      <v-app-bar-nav-icon v-if="mdAndDown"></v-app-bar-nav-icon>

      <v-spacer class="w-100" v-if="smAndDown" />

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

      <v-app-bar-title v-if="smAndUp">Vehicle for Vacation</v-app-bar-title>

      <v-spacer></v-spacer>

      <div v-if="mdAndUp">
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
