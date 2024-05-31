<script setup lang="ts">
import emitter from '@/utils/emitter'
import {ref} from 'vue'

const snackbarSuccess = ref(false)
const snackbarSuccessText = ref('')

const snackbarError = ref(false)
const snackbarErrorText = ref('')

emitter.on('notify-success', (message: string) => {
  snackbarSuccessText.value = message
  snackbarSuccess.value = true
})

emitter.on('notify-error', (message: string) => {
  snackbarError.value = true
  snackbarErrorText.value = message
})
</script>

<template>
  <v-snackbar v-model="snackbarSuccess" color="green-lighten-2" timeout="2000">
    SUCCESS: {{ snackbarSuccessText }}

    <template v-slot:actions>
      <v-btn color="blue" variant="text" @click="snackbarSuccess = false"> Close</v-btn>
    </template>
  </v-snackbar>

  <v-snackbar v-model="snackbarError" color="red-lighten-2" timeout="2000">
    ERROR: {{ snackbarErrorText }}

    <template v-slot:actions>
      <v-btn color="blue" variant="text" @click="snackbarError = false"> Close</v-btn>
    </template>
  </v-snackbar>
</template>

<style scoped></style>
