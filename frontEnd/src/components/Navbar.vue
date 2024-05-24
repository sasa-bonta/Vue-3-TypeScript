<script setup lang="ts">
import logo from '@/assets/car-rent-icon.svg'
import { useDisplay } from 'vuetify'
import { ref, type Ref } from 'vue'

const { xs, smAndUp, mdAndUp } = useDisplay()

const drawer: Ref<boolean> = ref(false)
const navigationRoutes: Ref<Array<{ title: string; icon: string; name: string }>> = ref([
  {
    title: 'Home',
    icon: 'mdi-home',
    name: 'home'
  },
  {
    title: 'Cars',
    icon: 'mdi-motorbike',
    name: 'cars'
  },
  {
    title: 'Bikes',
    icon: 'mdi-car-sports',
    name: 'bikes'
  },
  {
    title: 'Rents',
    icon: 'mdi-format-list-bulleted',
    name: 'rents'
  }
])
</script>

<template>
  <v-app-bar color="grey-darken-3">
    <v-app-bar-nav-icon v-if="xs" @click.stop="drawer = !drawer" />

    <v-app-bar-title v-if="xs">Vehicle for Vacation</v-app-bar-title>

    <v-btn icon variant="tonal" size="large" @click="$router.push({ name: 'home' })" class="ml-10">
      <v-icon style="height: 35px; width: 35px">
        <v-img :src="logo" alt="Home" />
      </v-icon>
    </v-btn>

    <v-app-bar-title v-if="!xs">Vehicle for Vacation</v-app-bar-title>

    <div v-if="smAndUp">
      <v-btn
        v-for="route in navigationRoutes"
        :key="route.name"
        :class="mdAndUp ? 'mx-6' : 'mx-2'"
        variant="tonal"
        :prepend-icon="route.icon"
        :disabled="$route.name === route.name"
        @click="$router.push({ name: route.name })"
        >{{ route.title }}
      </v-btn>
    </div>
  </v-app-bar>

  <v-navigation-drawer color="grey-darken-3" v-model="drawer" temporary v-if="xs">
    <v-list>
      <v-list-item
        v-for="route in navigationRoutes"
        :key="route.name"
        :title="route.title"
        :disabled="$route.name === route.name"
        @click="$router.push({ name: route.name })"
      >
        <template v-slot:prepend>
          <v-icon :icon="route.icon"></v-icon>
        </template>
      </v-list-item>
    </v-list>
  </v-navigation-drawer>
</template>

<style scoped></style>
