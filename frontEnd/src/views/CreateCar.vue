<script setup lang="ts">
import {reactive, ref, type Ref} from 'vue'
import {useRouter} from 'vue-router'
import {createCar} from '@/api/api'
import emitter from '@/utils/emitter'
import type {VForm} from 'vuetify/components'

const router = useRouter()

const fuelOptions: string[] = ['Diesel', 'Petrol', 'Electric', 'Diesel-hybrid', 'Petrol-hybrid']
const drivetrainOptions: string[] = ['RWD', 'FWD', 'AWD']
const typeOptions: string[] = ['Sedan', 'Coupe', 'SUV', 'Hatchback', 'Wagon']
const transmissionOptions: string[] = ['Auto', 'Manual']

const form = reactive({} as CarForm)
const requiredRule = [(v: string) => !!v || 'This field is required']
const formRef: Ref<InstanceType<typeof VForm> | null> = ref(null)

const submitForm = async () => {
  const validation = await formRef.value!.validate()

  if (!validation.valid) {
    return
  }

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
    <v-form ref="formRef" @submit.prevent="submitForm">
      <v-row>
        <v-col cols="12" md="6">
          <v-text-field v-model="form.vin" :rules="requiredRule" label="Number plate" required />
        </v-col>
        <v-col cols="12" md="6">
          <v-text-field
            v-model="form.year"
            :rules="requiredRule"
            label="Year"
            type="number"
            required
          />
        </v-col>
        <v-col cols="12" md="6">
          <v-text-field v-model="form.brand" :rules="requiredRule" label="Brand" required />
        </v-col>
        <v-col cols="12" md="6">
          <v-text-field v-model="form.model" :rules="requiredRule" label="Model" required />
        </v-col>
        <v-col cols="12" md="6">
          <v-text-field
            v-model="form.mileage"
            :rules="requiredRule"
            label="Mileage"
            type="number"
            required
          />
        </v-col>
        <v-col cols="12" md="6">
          <v-text-field v-model="form.engine" :rules="requiredRule" label="Engine" required />
        </v-col>
        <v-col cols="12" md="6">
          <v-select
            v-model="form.fuel"
            :items="fuelOptions"
            :rules="requiredRule"
            label="Fuel"
            required
          />
        </v-col>
        <v-col cols="12" md="6">
          <v-text-field
            v-model="form.power"
            :rules="requiredRule"
            label="Power"
            type="number"
            required
          />
        </v-col>
        <v-col cols="12" md="6">
          <v-select
            v-model="form.type"
            :items="typeOptions"
            :rules="requiredRule"
            label="Type"
            required
          />
        </v-col>
        <v-col cols="12" md="6">
          <v-text-field v-model="form.photo" :rules="requiredRule" label="Photo URL" required />
        </v-col>
        <v-col cols="12" md="6">
          <v-text-field
            v-model="form.price"
            :rules="requiredRule"
            label="Price"
            type="number"
            required
          />
        </v-col>
        <v-col cols="12" md="6">
          <v-select
            v-model="form.transmission"
            :items="transmissionOptions"
            :rules="requiredRule"
            label="Transmission"
            required
          />
        </v-col>
        <v-col cols="12" md="6">
          <v-select
            v-model="form.drivetrain"
            :items="drivetrainOptions"
            :rules="requiredRule"
            label="Drivetrain"
            required
          />
        </v-col>
        <v-col cols="12" md="6">
          <v-text-field
            v-model="form.seats"
            :rules="requiredRule"
            label="Seats"
            type="number"
            required
          />
        </v-col>
        <v-col cols="12">
          <v-btn color="primary" type="submit" block> Submit</v-btn>
        </v-col>
      </v-row>
    </v-form>
  </v-container>
</template>

<style scoped></style>
