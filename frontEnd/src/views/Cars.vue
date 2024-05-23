<script setup lang="ts">
import { useCarsStore } from '@/stores/cars'
import { onMounted } from 'vue'
import { storeToRefs } from 'pinia'

const store = useCarsStore()
const { items: cars, loading, error } = storeToRefs(store)
const { fetchList } = store

onMounted(() => {
  fetchList()
})
</script>

<template>
  cars:
  <pre>{{ cars }}</pre>
</template>

<style scoped></style>
