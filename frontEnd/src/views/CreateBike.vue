<script setup lang="ts">
import {useRouter} from 'vue-router'
import {reactive} from 'vue'
import {createBike} from '@/api/api'
import mitt from 'mitt'

const router = useRouter()
const emitter = mitt()

const fuelOptions: string[] = ['Petrol', 'Electric']
const typeOptions: string[] = ['Classic', 'Chopper', 'Sport bike', 'Cross']

const form = reactive({ streetLegal: true, backSeat: true, fuel: fuelOptions[0] } as BikeForm)

const submitForm = async () => {
  try {
    console.log(form)
    await createBike(form)
    emitter.emit('notify-success', 'New bike created')
    await router.push({ name: 'bikes' })
  } catch (error) {
    emitter.emit('notify-error', 'Failed to create bike: ' + error)
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
          <v-text-field v-model="form.fuel" label="Fuel" required></v-text-field>
        </v-col>
        <v-col cols="12" md="6">
          <v-text-field v-model="form.power" label="Power" type="number" required></v-text-field>
        </v-col>
        <v-col cols="12" md="6">
          <v-select v-model="form.type" :items="typeOptions" label="Type" />
        </v-col>
        <v-col cols="12" md="6">
          <v-text-field v-model="form.photo" label="Photo URL" required></v-text-field>
        </v-col>
        <v-col cols="12" md="6">
          <v-text-field v-model="form.price" label="Price" type="number" required></v-text-field>
        </v-col>
        <v-col cols="12" md="6">
          <v-switch
            v-model="form.streetLegal"
            :label="`Street legal: ${form.streetLegal}`"
            hide-details
            inset
          ></v-switch>
        </v-col>
        <v-col cols="12" md="6">
          <v-switch
            v-model="form.backSeat"
            :label="`Back seat: ${form.backSeat}`"
            hide-details
            inset
          ></v-switch>
        </v-col>
        <v-col cols="12">
          <v-btn @click="submitForm" color="primary"> Submit</v-btn>
        </v-col>
      </v-row>
    </v-form>
  </v-container>
</template>

<style scoped></style>
