<script setup lang="ts">
import {type Ref, ref} from 'vue'
import type {VForm} from 'vuetify/components'

const props = defineProps<{ form: CarRentForm | BikeRentForm }>()
const form = props.form

const requiredRule = [(v: string) => !!v || 'This field is required']
const formRef: Ref<InstanceType<typeof VForm> | null> = ref(null)
const emit = defineEmits()

const onSubmit = async () => {
  const validation = await formRef.value!.validate()

  if (validation.valid) {
    emit('submitForm', form)
  }
}
</script>

<template>
  <v-form ref="formRef" @submit.prevent="onSubmit">
    <v-row>
      <v-col cols="12" md="6">
        <v-text-field v-model="form.numberOfDays" :rules="requiredRule" label="Number of days" />
      </v-col>
      <v-col cols="12" md="6">
        <v-text-field
          v-model="form.pricePerDay"
          :rules="requiredRule"
          label="Price per day"
          type="number"
          required
        />
      </v-col>
      <v-col cols="12" md="6">
        <v-text-field v-model="form.idnp" :rules="requiredRule" label="idnp" />
      </v-col>
      <v-col cols="12" md="6">
        <v-text-field v-model="form.firstName" :rules="requiredRule" label="First name" />
      </v-col>
      <v-col cols="12" md="6">
        <v-text-field v-model="form.lastName" :rules="requiredRule" label="Last name" />
      </v-col>
      <v-col cols="12" md="6">
        <v-text-field v-model="form.tel" :rules="requiredRule" label="Tel" />
      </v-col>
      <v-col cols="12" md="6">
        <v-text-field v-model="form.email" :rules="requiredRule" label="Email" />
      </v-col>
      <v-col cols="12">
        <v-btn color="primary" type="submit" block>Submit</v-btn>
      </v-col>
    </v-row>
  </v-form>
</template>

<style scoped></style>
