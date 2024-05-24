<script setup lang="ts">
import { useCarsStore } from '@/stores/cars'
import {onMounted, type Ref, ref} from 'vue'
import { storeToRefs } from 'pinia'

const store = useCarsStore()
const { items: cars, loading, error } = storeToRefs(store)
const { fetchList } = store

onMounted(() => {
  fetchList()
})

const show: Ref<boolean> = ref(false)
</script>

<template>
cars:
  {{cars}}
</template>

<style scoped></style>
