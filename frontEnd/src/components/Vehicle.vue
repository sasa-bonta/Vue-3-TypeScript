<script setup lang="ts">
import {reactive} from 'vue'
import type {Bike, Car, Vehicle} from '@/interfaces/api'
import {useConfirmStore} from '@/stores/confirm'
import {deleteVehicleById} from '@/api/api'
import emitter from '@/utils/emitter'
import {useCarsStore} from '@/stores/cars'
import {useBikesStore} from '@/stores/bikes'

const props = defineProps<{ vehicle: Vehicle }>()
const vehicle: Vehicle = reactive(props.vehicle)

const isCar = (vehicle: Vehicle): vehicle is Car => vehicle.vehicleType === 'car'
const isBike = (vehicle: Vehicle): vehicle is Bike => vehicle.vehicleType === 'bike'

const confirmStore = useConfirmStore()
const { openConfirm } = confirmStore

const carsStore = useCarsStore()
const bikesStore = useBikesStore()

const deleteVehicle = async () => {
  const isConfirmed = await openConfirm(
    `Are you sure you want to delete this ${vehicle.vehicleType}?`
  )

  if (!isConfirmed) {
    return
  }

  try {
    await deleteVehicleById(vehicle.vehicleType!, vehicle.id)
    emitter.emit('notify-success', 'Vehicle deleted')
    if (isCar(vehicle)) {
      await carsStore.fetchList()
    } else if (isBike(vehicle)) {
      await bikesStore.fetchList()
    }
  } catch (error) {
    emitter.emit('notify-error', 'Failed to delete vehicle: ' + error)
  }
}
</script>

<template>
  <v-row class="my-4">
    <v-card class="mx-auto" width="1280">
      <v-container>
        <v-row>
          <v-col md="3" sm="6">
            <v-img width="250" height="200" :src="vehicle.photo" cover class="rounded mx-auto" />
          </v-col>
          <v-col md="3" sm="6">
            <v-list>
              <v-list-subheader>Technical information</v-list-subheader>
              <v-list-item v-if="isCar(vehicle)" color="primary" variant="plain">
                <template v-slot:prepend>
                  <v-icon icon="mdi-car-shift-pattern" />
                </template>
                <v-list-item-title>{{ vehicle.transmission }}</v-list-item-title>
                <v-tooltip activator="parent" location="top">Transmission</v-tooltip>
              </v-list-item>
              <v-list-item v-else-if="isBike(vehicle)" color="primary" variant="plain">
                <template v-slot:prepend>
                  <v-icon icon="mdi-road-variant" />
                </template>
                <v-list-item-title>{{ vehicle.streetLegal ? 'Yes' : 'No' }}</v-list-item-title>
                <v-tooltip activator="parent" location="top">Street legal</v-tooltip>
              </v-list-item>
              <v-list-item color="primary" variant="plain">
                <template v-slot:prepend>
                  <v-icon icon="mdi-engine" />
                </template>
                <v-list-item-title>{{ vehicle.power }} HP</v-list-item-title>
                <v-tooltip activator="parent" location="top">Power</v-tooltip>
              </v-list-item>
              <v-list-item color="primary" variant="plain">
                <template v-slot:prepend> <v-icon icon="mdi-car-side" />icon> </template>
                <v-list-item-title>{{ vehicle.type }}</v-list-item-title>
                <v-tooltip activator="parent" location="top">Vehicle type</v-tooltip>
              </v-list-item>
            </v-list>
          </v-col>

          <v-col md="3" sm="6">
            <v-list>
              <v-list-subheader>General data</v-list-subheader>
              <v-list-item color="primary" variant="plain">
                <template v-slot:prepend> <v-icon icon="mdi-id-card" />icon> </template>
                <v-list-item-title>{{ vehicle.vin }}</v-list-item-title>
                <v-tooltip activator="parent" location="top">Number plate</v-tooltip>
              </v-list-item>
              <v-list-item color="primary" variant="plain">
                <template v-slot:prepend> <v-icon icon="mdi-counter" />icon> </template>
                <v-list-item-title>{{ vehicle.mileage }} KM</v-list-item-title>
                <v-tooltip activator="parent" location="top">Mileage</v-tooltip>
              </v-list-item>
              <v-list-item color="primary" variant="plain">
                <template v-slot:prepend> <v-icon icon="mdi-fuel" />icon> </template>
                <v-list-item-title>{{ vehicle.fuel }}</v-list-item-title>
                <v-tooltip activator="parent" location="top">Fuel type</v-tooltip>
              </v-list-item>
            </v-list>
          </v-col>

          <v-col md="3" sm="6">
            <v-list>
              <v-list-subheader>Additional information</v-list-subheader>
              <v-list-item v-if="isCar(vehicle)" color="primary" variant="plain">
                <template v-slot:prepend> <v-icon icon="mdi-seat-passenger" />icon> </template>
                <v-list-item-title>{{ vehicle.seats }}</v-list-item-title>
                <v-tooltip activator="parent" location="top">Number of seats</v-tooltip>
              </v-list-item>
              <v-list-item v-else-if="isBike(vehicle)" color="primary" variant="plain">
                <template v-slot:prepend> <v-icon icon="mdi-seat-passenger" />icon> </template>
                <v-list-item-title>{{ vehicle.backSeat ? 'Yes' : 'No' }}</v-list-item-title>
                <v-tooltip activator="parent" location="top">Back seat</v-tooltip>
              </v-list-item>
              <v-list-item color="primary" variant="plain">
                <template v-slot:prepend> <v-icon icon="mdi-calendar" />icon> </template>
                <v-list-item-title>{{ vehicle.year }}</v-list-item-title>
                <v-tooltip activator="parent" location="top">Year</v-tooltip>
              </v-list-item>
              <v-list-item color="primary" variant="plain">
                <template v-slot:prepend> <v-icon icon="mdi-cash" />icon> </template>
                <v-list-item-title>{{ vehicle.price }} €</v-list-item-title>
                <v-tooltip activator="parent" location="top">Price</v-tooltip>
              </v-list-item>
            </v-list>
          </v-col>
        </v-row>
      </v-container>

      <v-card-title> {{ vehicle.brand }} {{ vehicle.model }}</v-card-title>

      <v-card-subtitle> {{ vehicle.available ? 'Available' : 'Not available' }}</v-card-subtitle>

      <v-card-actions>
        <v-btn
          :disabled="!vehicle.available"
          variant="tonal"
          color="orange-lighten-2"
          text="Rent now"
          @click="
            $router.push({
              name: 'createRent',
              params: { vehicleType: vehicle.vehicleType, id: vehicle.id }
            })
          "
        />
        <v-btn
          :disabled="!vehicle.available"
          variant="tonal"
          color="red-lighten-2"
          text="Delete"
          @click="deleteVehicle"
        />

        <v-spacer />

        <v-btn
          :icon="vehicle.showDetails ? 'mdi-chevron-up' : 'mdi-chevron-down'"
          @click="vehicle.showDetails = !vehicle.showDetails"
        />btn>
      </v-card-actions>

      <v-expand-transition>
        <div v-show="vehicle.showDetails">
          <v-divider />divider>

          <v-card-text>
            <pre>{{ vehicle }}</pre>
          </v-card-text>
        </div>
      </v-expand-transition>
    </v-card>
  </v-row>
</template>

<style scoped></style>
