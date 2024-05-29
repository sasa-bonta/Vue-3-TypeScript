<script setup lang="ts">
import { computed, reactive } from 'vue'
import { useRoute } from 'vue-router'
import CreateRentForm from '@/components/CreateRentForm.vue'

const route = useRoute()
const id = computed(() => route.params.id)

const form = reactive({
  car: { id: Number(id.value) }
} as CarRentForm)
</script>

<template>
  <CreateRentForm :form="form" />
</template>

<style scoped></style>
