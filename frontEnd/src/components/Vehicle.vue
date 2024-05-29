<script setup lang="ts">
import {reactive} from 'vue'
import type {Bike, Car, Vehicle} from '@/interfaces/api'

const props = defineProps<{ vehicle: Vehicle }>()
const vehicle: Vehicle = reactive(props.vehicle)

const isCar = (vehicle: Vehicle): vehicle is Car => vehicle.vehicleType === 'car'
const isBike = (vehicle: Vehicle): vehicle is Bike => vehicle.vehicleType === 'bike'
</script>

<template>
  <v-row class="my-4">
    <v-card class="mx-auto" width="1280">
      <v-container>
        <v-row>
          <v-col md="3" sm="6" xs="12">
            <v-img
              width="250"
              height="200"
              :src="vehicle.photo"
              cover
              class="rounded mx-auto"
            ></v-img>
          </v-col>
          <v-col md="3" sm="6" xs="12">
            <v-list>
              <v-list-subheader>Technical information</v-list-subheader>
              <v-list-item v-if="isCar(vehicle)" color="primary" variant="plain">
                <template v-slot:prepend>
                  <v-icon icon="mdi-car-shift-pattern"></v-icon>
                </template>
                <v-list-item-title>{{ vehicle.transmission }}</v-list-item-title>
                <v-tooltip activator="parent" location="top">Transmission </v-tooltip>
              </v-list-item>
              <v-list-item v-else-if="isBike(vehicle)" color="primary" variant="plain">
                <template v-slot:prepend>
                  <v-icon icon="mdi-road-variant"></v-icon>
                </template>
                <v-list-item-title>{{ vehicle.streetLegal ? 'Yes' : 'No' }}</v-list-item-title>
                <v-tooltip activator="parent" location="top">Street legal </v-tooltip>
              </v-list-item>
              <v-list-item color="primary" variant="plain">
                <template v-slot:prepend>
                  <v-icon icon="mdi-engine"></v-icon>
                </template>
                <v-list-item-title>{{ vehicle.power }} HP</v-list-item-title>
                <v-tooltip activator="parent" location="top">Power </v-tooltip>
              </v-list-item>
              <v-list-item color="primary" variant="plain">
                <template v-slot:prepend>
                  <v-icon icon="mdi-car-side"></v-icon>
                </template>
                <v-list-item-title>{{ vehicle.type }}</v-list-item-title>
                <v-tooltip activator="parent" location="top">Vehicle type </v-tooltip>
              </v-list-item>
            </v-list>
          </v-col>

          <v-col md="3" sm="6" xs="12">
            <v-list>
              <v-list-subheader>General data</v-list-subheader>
              <v-list-item color="primary" variant="plain">
                <template v-slot:prepend>
                  <v-icon icon="mdi-id-card"></v-icon>
                </template>
                <v-list-item-title>{{ vehicle.vin }}</v-list-item-title>
                <v-tooltip activator="parent" location="top">Plate number </v-tooltip>
              </v-list-item>
              <v-list-item color="primary" variant="plain">
                <template v-slot:prepend>
                  <v-icon icon="mdi-counter"></v-icon>
                </template>
                <v-list-item-title>{{ vehicle.mileage }} KM</v-list-item-title>
                <v-tooltip activator="parent" location="top">Mileage </v-tooltip>
              </v-list-item>
              <v-list-item color="primary" variant="plain">
                <template v-slot:prepend>
                  <v-icon icon="mdi-fuel"></v-icon>
                </template>
                <v-list-item-title>{{ vehicle.fuel }}</v-list-item-title>
                <v-tooltip activator="parent" location="top">Fuel type </v-tooltip>
              </v-list-item>
            </v-list>
          </v-col>

          <v-col md="3" sm="6" xs="12">
            <v-list>
              <v-list-subheader>Additional information</v-list-subheader>
              <v-list-item v-if="isCar(vehicle)" color="primary" variant="plain">
                <template v-slot:prepend>
                  <v-icon icon="mdi-seat-passenger"></v-icon>
                </template>
                <v-list-item-title>{{ vehicle.seats }}</v-list-item-title>
                <v-tooltip activator="parent" location="top">Number of seats </v-tooltip>
              </v-list-item>
              <v-list-item v-else-if="isBike(vehicle)" color="primary" variant="plain">
                <template v-slot:prepend>
                  <v-icon icon="mdi-seat-passenger"></v-icon>
                </template>
                <v-list-item-title>{{ vehicle.backSeat ? 'Yes' : 'No' }}</v-list-item-title>
                <v-tooltip activator="parent" location="top">Back seat </v-tooltip>
              </v-list-item>
              <v-list-item color="primary" variant="plain">
                <template v-slot:prepend>
                  <v-icon icon="mdi-calendar"></v-icon>
                </template>
                <v-list-item-title>{{ vehicle.year }}</v-list-item-title>
                <v-tooltip activator="parent" location="top">Year </v-tooltip>
              </v-list-item>
              <v-list-item color="primary" variant="plain">
                <template v-slot:prepend>
                  <v-icon icon="mdi-cash"></v-icon>
                </template>
                <v-list-item-title>{{ vehicle.price }} €</v-list-item-title>
                <v-tooltip activator="parent" location="top">Price </v-tooltip>
              </v-list-item>
            </v-list>
          </v-col>
        </v-row>
      </v-container>

      <v-card-title> {{ vehicle.brand }} {{ vehicle.model }}</v-card-title>

      <v-card-subtitle> {{ vehicle.available ? 'Available' : 'Not available' }}</v-card-subtitle>

      <v-card-actions>
        <v-btn
          variant="tonal"
          color="orange-lighten-2"
          text="Rent now"
          @click="
            $router.push({
              name: 'createRent',
              params: { vehicleType: vehicle.vehicleType, id: vehicle.id }
            })
          "
        ></v-btn>
        <v-btn variant="tonal" color="deep-purple-lighten-2" text="View rent history"></v-btn>

        <v-spacer></v-spacer>

        <v-btn
          :icon="vehicle.showDetails ? 'mdi-chevron-up' : 'mdi-chevron-down'"
          @click="vehicle.showDetails = !vehicle.showDetails"
        ></v-btn>
      </v-card-actions>

      <v-expand-transition>
        <div v-show="vehicle.showDetails">
          <v-divider></v-divider>

          <v-card-text>
            <pre>{{ vehicle }}</pre>
          </v-card-text>
        </div>
      </v-expand-transition>
    </v-card>
  </v-row>
</template>

<style scoped></style>
