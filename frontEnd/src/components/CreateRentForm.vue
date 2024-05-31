<script setup lang="ts">
const props = defineProps<{ form: CarRentForm | BikeRentForm }>()
const form = props.form

const requiredRule = [(v: string) => !!v || 'This field is required']
</script>

<template>
  <v-form>
    <v-row>
      <v-col cols="12" md="6">
        <v-text-field v-model="form.numberOfDays" :rules="requiredRule" label="Number of days" />
      </v-col>
      <v-col cols="12" md="6">
        <v-text-field v-model="form.pricePerDay" :rules="requiredRule" label="Price per day" type="number" required />
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
        <v-btn @click="$emit('submitForm', form)" color="primary"> Submit</v-btn>
      </v-col>
    </v-row>
  </v-form>
</template>

<style scoped></style>