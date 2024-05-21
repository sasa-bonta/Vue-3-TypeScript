<script setup lang="ts">
import {computed, reactive} from 'vue'
import {createCarRent} from '@/api/api'
import {useRoute, useRouter} from 'vue-router'

const route = useRoute();
const id = computed(() => route.params.id);

const router = useRouter()

const form = reactive({
  numberOfDays: 1,
  pricePerDay: 100,
  idnp: "123456",
  tel: "11111",
  email: "email@email.email",
  car: {id: Number(id.value)},
} as CarRentForm)

const submitForm = async () => {
  try {
    await createCarRent(form)
    await router.push({name: 'car'})
  } catch (error) {
    console.error('Failed to rent car:', error)
  }
}
</script>

<template>
  <v-container>
    <v-form>
      <v-row>
        <v-col cols="12" md="6">
          <v-text-field v-model="form.numberOfDays" label="Number of days" required></v-text-field>
        </v-col>
        <v-col cols="12" md="6">
          <v-text-field v-model="form.pricePerDay" label="Price per day" type="number" required></v-text-field>
        </v-col>
        <v-col cols="12" md="6">
          <v-text-field v-model="form.idnp" label="IDNP" required></v-text-field>
        </v-col>
        <v-col cols="12" md="6">
          <v-text-field v-model="form.tel" label="Tel" required></v-text-field>
        </v-col>
        <v-col cols="12">
          <v-btn @click="submitForm" color="primary"> Submit</v-btn>
        </v-col>
      </v-row>
    </v-form>
  </v-container>
</template>

<style scoped></style>
