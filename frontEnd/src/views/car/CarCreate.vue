<script setup lang="ts">
import {reactive} from 'vue'
import {createCar} from '@/api/api'
import {useRouter} from 'vue-router'

const router = useRouter()

const form = reactive({
  vin: '32106516',
  year: 2017,
  brand: 'MB',
  model: 'w213',
  mileage: 250000,
  engine: 'OM651',
  fuel: 'Benzin',
  power: 194,
  type: 'Sedan',
  photo: 'https://live.staticflickr.com/65535/52533263334_85eeb4ee79_b.jpg',
  rent: false,
  price: 17000,
  transmission: 'Auto',
  drivetrain: 'RWD',
  seats: 5
} as CarForm)

const submitForm = async () => {
  try {
    await createCar(form)
    await router.push({name: 'car'})
  } catch (error) {
    console.error('Failed to create car:', error)
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
          <v-text-field v-model="form.transmission" label="Transmission" required></v-text-field>
        </v-col>
        <v-col cols="12" md="6">
          <v-text-field v-model="form.drivetrain" label="Drivetrain" required></v-text-field>
        </v-col>
        <v-col cols="12" md="6">
          <v-text-field v-model="form.seats" label="Seats" type="number" required></v-text-field>
        </v-col>
        <v-col cols="12">
          <v-btn @click="submitForm" color="primary"> Submit</v-btn>
        </v-col>
      </v-row>
    </v-form>
  </v-container>
</template>

<style scoped></style>
