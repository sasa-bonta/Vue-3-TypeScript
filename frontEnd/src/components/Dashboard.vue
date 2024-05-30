<script setup lang="ts">
import {RouterView} from 'vue-router'
import Navbar from '@/components/Navbar.vue'
import {ref} from 'vue'
import mitt from 'mitt'

const snackbarSuccess = ref(false)
const snackbarSuccessText = ref('')

const snackbarError = ref(false)
const snackbarErrorText = ref('')

const emitter = mitt()

emitter.on('notify-success', (message: string) => {
  alert("Success")
  snackbarSuccessText.value = message
  snackbarSuccess.value = true
})

emitter.on('notify-error', (message: string) => {
  snackbarError.value = true
  snackbarErrorText.value = message
})
</script>

<template>
  <v-layout class="rounded rounded-md" app>
    <Navbar />

    <v-main class="d-flex align-center justify-center" style="min-height: 300px; width: 100%">
      <RouterView />
    </v-main>
  </v-layout>

  <v-snackbar v-model="snackbarSuccess" color="green-lighten-2" timeout="2000">
    SUCCESS: {{ snackbarSuccessText }}

    <template v-slot:actions>
      <v-btn color="blue" variant="text" @click="snackbarError = false"> Close </v-btn>
    </template>
  </v-snackbar>

  <v-snackbar v-model="snackbarError" color="red-lighten-2" timeout="2000">
    ERROR: {{ snackbarErrorText }}

    <template v-slot:actions>
      <v-btn color="blue" variant="text" @click="snackbarError = false"> Close </v-btn>
    </template>
  </v-snackbar>
</template>

<style scoped></style>
