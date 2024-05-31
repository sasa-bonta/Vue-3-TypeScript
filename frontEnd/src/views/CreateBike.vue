<script setup lang="ts">
import {useRouter} from 'vue-router'
import {reactive, ref, type Ref} from 'vue'
import {createBike} from '@/api/api'
import emitter from '@/utils/emitter'
import type {VForm} from 'vuetify/components'

const router = useRouter()

const fuelOptions: string[] = ['Petrol', 'Electric']
const typeOptions: string[] = ['Classic', 'Chopper', 'Sport bike', 'Cross']

const form = reactive({ streetLegal: true, backSeat: true, fuel: fuelOptions[0] } as BikeForm)
const requiredRule = [(v: string) => !!v || 'This field is required']
const formRef: Ref<InstanceType<typeof VForm> | null> = ref(null)

const submitForm = async () => {
  const validation = await formRef.value!.validate()

  if (!validation.valid) {
    return
  }

  try {
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
          <v-text-field v-model="form.fuel" :rules="requiredRule" label="Fuel" required />
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
          <v-switch
            v-model="form.streetLegal"
            :label="`Street legal: ${form.streetLegal}`"
            hide-details
            inset
          />
        </v-col>
        <v-col cols="12" md="6">
          <v-switch
            v-model="form.backSeat"
            :label="`Back seat: ${form.backSeat}`"
            hide-details
            inset
          />
        </v-col>
        <v-col cols="12">
          <v-btn @submit="submitForm" color="primary" type="submit" block> Submit</v-btn>
        </v-col>
      </v-row>
    </v-form>
  </v-container>
</template>

<style scoped></style>
