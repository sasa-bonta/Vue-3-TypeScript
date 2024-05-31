<script setup lang="ts">
import {useConfirmStore} from '@/stores/confirm'
import {storeToRefs} from 'pinia'

const store = useConfirmStore()
const { isOpen, message } = storeToRefs(store)
const { closeConfirm } = store
</script>

<template>
  <div class="text-center pa-4">
    <v-dialog v-model="isOpen" max-width="400" persistent>
      <v-card :text="message" title="Confirm your action">
        <template v-slot:actions>
          <v-spacer></v-spacer>

          <v-btn @click="closeConfirm(false)"> Cancel</v-btn>

          <v-btn @click="closeConfirm(true)"> Ok</v-btn>
        </template>
      </v-card>
    </v-dialog>
  </div>
</template>

<style scoped></style>
