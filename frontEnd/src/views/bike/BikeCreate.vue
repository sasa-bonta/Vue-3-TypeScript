<script setup lang="ts">
import { reactive } from 'vue'
import { createBike } from '@/api/api'
import { useRouter } from 'vue-router'

const router = useRouter()

const form = reactive({
  vin: '4564498454',
  year: 2019,
  brand: 'KTM',
  model: 'DUKE 1290',
  mileage: 2500,
  engine: 'single cylinder',
  fuel: 'Petrol',
  power: 145,
  type: 'Street bike',
  photo: 'https://live.staticflickr.com/65535/52533263334_85eeb4ee79_b.jpg',
  rent: false,
  price: 1000,
  streetLegal: true,
  backSeat: true
} as BikeForm)

const submitForm = async () => {
  try {
    await createBike(form)
    await router.push({ name: 'bike' })
  } catch (error) {
    console.error('Failed to create bike:', error)
  }
}
</script>

<template>
  <v-container>
    <v-form>
      <v-row>
        <v-col cols="12" md="6">
          <v-text-field v-model="form.vin" label="VIN" required></v-text-field>
        </v-col>
        <v-col cols="12" md="6">
          <v-text-field v-model="form.year" label="Year" type="number" required></v-text-field>
        </v-col>
        <v-col cols="12" md="6">
          <v-text-field v-model="form.brand" label="Brand" required></v-text-field>
        </v-col>
        <v-col cols="12" md="6">
          <v-text-field v-model="form.model" label="Model" required></v-text-field>
        </v-col>
        <v-col cols="12" md="6">
          <v-text-field
            v-model="form.mileage"
            label="Mileage"
            type="number"
            required
          ></v-text-field>
        </v-col>
        <v-col cols="12" md="6">
          <v-text-field v-model="form.engine" label="Engine" required></v-text-field>
        </v-col>
        <v-col cols="12" md="6">
          <v-text-field v-model="form.fuel" label="Fuel" required></v-text-field>
        </v-col>
        <v-col cols="12" md="6">
          <v-text-field v-model="form.power" label="Power" type="number" required></v-text-field>
        </v-col>
        <v-col cols="12" md="6">
          <v-text-field v-model="form.type" label="Type" required></v-text-field>
        </v-col>
        <v-col cols="12" md="6">
          <v-text-field v-model="form.photo" label="Photo URL" required></v-text-field>
        </v-col>
        <v-col cols="12" md="6">
          <v-text-field v-model="form.price" label="Price" type="number" required></v-text-field>
        </v-col>
        <v-col cols="12" md="6">
          <v-text-field v-model="form.streetLegal" label="streetLegal" required></v-text-field>
        </v-col>
        <v-col cols="12" md="6">
          <v-text-field v-model="form.backSeat" label="backSeat" required></v-text-field>
        </v-col>
        <v-col cols="12">
          <v-btn @click="submitForm" color="primary"> Submit </v-btn>
        </v-col>
      </v-row>
    </v-form>
  </v-container>
</template>

<style scoped></style>
