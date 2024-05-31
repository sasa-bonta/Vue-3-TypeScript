<script setup lang="ts">
const props = defineProps<{ form: CarRentForm | BikeRentForm }>()
const form = props.form
</script>

<template>
  <v-form>
    <v-row>
      <v-col cols="12" md="6">
        <v-text-field v-model="form.numberOfDays" label="Number of days" />
      </v-col>
      <v-col cols="12" md="6">
        <v-text-field v-model="form.pricePerDay" label="Price per day" type="number" required />
      </v-col>
      <v-col cols="12" md="6">
        <v-text-field v-model="form.idnp" label="idnp" />
      </v-col>
      <v-col cols="12" md="6">
        <v-text-field v-model="form.firstName" label="First name" />
      </v-col>
      <v-col cols="12" md="6">
        <v-text-field v-model="form.lastName" label="Last name" />
      </v-col>
      <v-col cols="12" md="6">
        <v-text-field v-model="form.tel" label="Tel" />
      </v-col>
      <v-col cols="12" md="6">
        <v-text-field v-model="form.email" label="Email" />
      </v-col>
      <v-col cols="12">
        <v-btn @click="$emit('submitForm', form)" color="primary"> Submit</v-btn>
      </v-col>
    </v-row>
  </v-form>
</template>

<style scoped></style>
