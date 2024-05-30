<script setup lang="ts">
import {reactive} from 'vue'
import {useRouter} from 'vue-router'
import {createCar} from '@/api/api'

const router = useRouter()

const fuelOptions: string[] = ['Diesel', 'Petrol', 'Electric', 'Diesel-hybrid', 'Petrol-hybrid']
const drivetrainOptions: string[] = ['RWD', 'FWD', 'AWD']
const typeOptions: string[] = ['Sedan', 'Coupe', 'SUV', 'Hatchback', 'Wagon']
const transmissionOptions: string[] = ['Auto', 'Manual']

const form = reactive({} as CarForm)

const submitForm = async () => {
  try {
    await createCar(form)
    emitter.emit('notify-success', 'New car created')
    await router.push({ name: 'cars' })
  } catch (error) {
    emitter.emit('notify-error', 'Failed to create car: ' + error)
  }
}
</script>

<template>
  <v-container>
    <v-form>
      <v-row>
        <v-col cols="12" md="6">
          <v-text-field v-model="form.vin" label="Number plate" required></v-text-field>
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
          <v-select v-model="form.fuel" :items="fuelOptions" label="Fuel" />
        </v-col>
        <v-col cols="12" md="6">
          <v-text-field v-model="form.power" label="Power" type="number" required></v-text-field>
        </v-col>
        <v-col cols="12" md="6">
          <v-select v-model="form.type" :items="typeOptions" label="Type" required />
        </v-col>
        <v-col cols="12" md="6">
          <v-text-field v-model="form.photo" label="Photo URL" required></v-text-field>
        </v-col>
        <v-col cols="12" md="6">
          <v-text-field v-model="form.price" label="Price" type="number" required></v-text-field>
        </v-col>
        <v-col cols="12" md="6">
          <v-select
            v-model="form.transmission"
            :items="transmissionOptions"
            label="Transmission"
            required
          />
        </v-col>
        <v-col cols="12" md="6">
          <v-select v-model="form.drivetrain" :items="drivetrainOptions" label="Drivetrain" />
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
