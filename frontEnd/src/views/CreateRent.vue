<script setup lang="ts">
import {useRoute, useRouter} from 'vue-router'
import {computed, reactive} from 'vue'
import CreateRentForm from "@/components/CreateRentForm.vue";

const route = useRoute()
const vehicleType = computed(() => route.params.vehicleType)
const id = computed(() => route.params.id)

let form: CarRentForm | BikeRentForm;

if (vehicleType.value === 'car') {
  form = reactive<CarRentForm>({
    car: {id: Number(id.value)}
  } as CarRentForm);
} else if (vehicleType.value === 'bike') {
  form = reactive<BikeRentForm>({
    bike: {id: Number(id.value)}
  } as BikeRentForm)
}

const router = useRouter()

const submitForm = async () => {
  try {
    form.idnp = `${form.idnp}, ${form.firstName}, ${form.lastName}`
    if (vehicleType.value === 'car') {
      // await createCarRent(form)
    } else if (vehicleType.value === 'bike') {
      // await createBikeRent(form)
    }
    await router.push({name: 'cars'})
  } catch (error) {
    console.error('Failed to rent car:', error)
  }
}
</script>

<template>
  <v-container>
    <CreateRentForm :form @submitForm="submitForm"/>
  </v-container>
</template>

<style scoped></style>
