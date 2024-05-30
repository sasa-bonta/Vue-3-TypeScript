<script setup lang="ts">
import {useRoute, useRouter} from 'vue-router'
import {computed, reactive} from 'vue'
import CreateRentForm from '@/components/CreateRentForm.vue'
import {createBikeRent, createCarRent} from '@/api/api'
import mitt from 'mitt'

const route = useRoute()
const vehicleType = computed(() => route.params.vehicleType)
const id = computed(() => route.params.id)

let form: CarRentForm | BikeRentForm

if (vehicleType.value === 'car') {
  form = reactive<CarRentForm>({
    car: { id: Number(id.value) }
  } as CarRentForm)
} else if (vehicleType.value === 'bike') {
  form = reactive<BikeRentForm>({
    bike: { id: Number(id.value) }
  } as BikeRentForm)
}

const router = useRouter()
const emitter = mitt()

const submitForm = async () => {
  try {
    form.idnp = `${form.firstName} ${form.lastName}, ${form.idnp}`
    if (vehicleType.value === 'car') {
      await createCarRent(form as CarRentForm)
      await router.push({ name: 'cars' })
    } else if (vehicleType.value === 'bike') {
      await createBikeRent(form as BikeRentForm)
      await router.push({ name: 'bikes' })
    }
    emitter.emit('notify-success', 'Rent created')
  } catch (error) {
    emitter.emit('notify-error', 'Failed to create rent: ' + error)
  }
}
</script>

<template>
  <v-container>
    <CreateRentForm :form @submitForm="submitForm" />
  </v-container>
</template>

<style scoped></style>
