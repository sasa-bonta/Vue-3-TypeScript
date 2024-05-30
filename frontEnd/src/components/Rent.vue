<script setup lang="ts">
import {reactive} from 'vue'
import type {BikeRent, CarRent, Rent} from '@/interfaces/api'
import {endRent} from '@/api/api'
import {useRentsStore} from '@/stores/rents'
import mitt from "mitt";

const props = defineProps<{ rent: Rent }>()
const rent: Rent = reactive(props.rent)

const isCarRent = (rent: Rent): rent is CarRent => rent.type === 'car'
const isBikeRent = (rent: Rent): rent is BikeRent => rent.type === 'bike'

const store = useRentsStore()
const { fetchList } = store

const emitter = mitt()

const endRentAction = async () => {
  const isConfirmed = confirm('Are you sure you want to end this rent?')

  if (!isConfirmed) {
    return
  }

  try {
    await endRent(rent.type!, rent.id)
    await fetchList()
    emitter.emit('notify-success', 'Rent ended')
  } catch (error) {
    emitter.emit('notify-error', 'Filed to delete rent: ' + error)
  }
}
</script>

<template>
  <v-row class="my-4">
    <v-card class="mx-auto" width="1280">
      <v-container>
        <v-row>
          <v-col md="3" sm="6" xs="12">
            <v-img
              v-if="isCarRent(rent)"
              width="250"
              height="200"
              :src="rent.car.photo"
              cover
              class="rounded mx-auto"
            ></v-img>
            <v-img
              v-else-if="isBikeRent(rent)"
              width="250"
              height="200"
              :src="rent.bike.photo"
              cover
              class="rounded mx-auto"
            ></v-img>
          </v-col>

          <v-col md="3" sm="6" xs="12">
            <v-list>
              <v-list-subheader>Rent information</v-list-subheader>

              <v-list-item color="primary" variant="plain">
                <template v-slot:prepend>
                  <v-icon icon="mdi-timer-play-outline"></v-icon>
                </template>
                <v-list-item-title>{{ rent.startDate }}</v-list-item-title>
                <v-tooltip activator="parent" location="top">Start date</v-tooltip>
              </v-list-item>
              <v-list-item color="primary" variant="plain">
                <template v-slot:prepend>
                  <v-icon icon="mdi-timer-cancel-outline"></v-icon>
                </template>
                <v-list-item-title>{{ rent.finishDate ?? '-' }}</v-list-item-title>
                <v-tooltip activator="parent" location="top">End date</v-tooltip>
              </v-list-item>
              <v-list-item color="primary" variant="plain">
                <template v-slot:prepend>
                  <v-icon icon="mdi-cash"></v-icon>
                </template>
                <v-list-item-title>{{ rent.pricePerDay }} €</v-list-item-title>
                <v-tooltip activator="parent" location="top">Price per day</v-tooltip>
              </v-list-item>
            </v-list>
          </v-col>

          <v-col md="3" sm="6" xs="12">
            <v-list>
              <v-list-subheader>Vehicle information</v-list-subheader>
              <v-list-item color="primary" variant="plain">
                <template v-slot:prepend>
                  <v-icon icon="mdi-crown-circle"></v-icon>
                </template>
                <v-list-item-title>{{ rent.vehicle.brand }}</v-list-item-title>
                <v-tooltip activator="parent" location="top">Brand</v-tooltip>
              </v-list-item>
              <v-list-item color="primary" variant="plain">
                <template v-slot:prepend>
                  <v-icon icon="mdi-text-recognition"></v-icon>
                </template>
                <v-list-item-title>{{ rent.vehicle.model }}</v-list-item-title>
                <v-tooltip activator="parent" location="top">Model</v-tooltip>
              </v-list-item>
              <v-list-item color="primary" variant="plain">
                <template v-slot:prepend>
                  <v-icon icon="mdi-cake"></v-icon>
                </template>
                <v-list-item-title>{{ rent.vehicle.year }}</v-list-item-title>
                <v-tooltip activator="parent" location="top">Year</v-tooltip>
              </v-list-item>
            </v-list>
          </v-col>

          <v-col md="3" sm="6" xs="12">
            <v-list>
              <v-list-subheader>Vehicle information</v-list-subheader>
              <v-list-item color="primary" variant="plain">
                <template v-slot:prepend>
                  <v-icon icon="mdi-id-card"></v-icon>
                </template>
                <v-list-item-title v-if="isCarRent(rent)">{{ rent.car.vin }}</v-list-item-title>
                <v-list-item-title v-else-if="isBikeRent(rent)"
                  >{{ rent.bike.vin }}
                </v-list-item-title>
                <v-tooltip activator="parent" location="top">Number plate</v-tooltip>
              </v-list-item>
              <v-list-item color="primary" variant="plain">
                <template v-slot:prepend>
                  <v-icon icon="mdi-counter"></v-icon>
                </template>
                <v-list-item-title v-if="isCarRent(rent)"
                  >{{ rent.car.mileage }} KM
                </v-list-item-title>
                <v-list-item-title v-else-if="isBikeRent(rent)"
                  >{{ rent.bike.mileage }} KM
                </v-list-item-title>
                <v-tooltip activator="parent" location="top">Mileage</v-tooltip>
              </v-list-item>
              <v-list-item color="primary" variant="plain">
                <template v-slot:prepend>
                  <v-icon icon="mdi-fuel"></v-icon>
                </template>
                <v-list-item-title v-if="isCarRent(rent)">{{ rent.car.fuel }}</v-list-item-title>
                <v-list-item-title v-else-if="isBikeRent(rent)"
                  >{{ rent.bike.fuel }}
                </v-list-item-title>
                <v-tooltip activator="parent" location="top">Fuel type</v-tooltip>
              </v-list-item>
            </v-list>
          </v-col>
        </v-row>
      </v-container>

      <v-card-title> {{ rent.idnp }}</v-card-title>

      <v-card-subtitle> {{ rent.tel }} {{ rent.email }}</v-card-subtitle>

      <v-card-actions>
        <v-btn
          variant="tonal"
          color="orange-lighten-2"
          text="End rent"
          @click="endRentAction"
          :disabled="!!rent.finishDate"
        />

        <v-spacer></v-spacer>

        <v-btn
          :icon="rent.showDetails ? 'mdi-chevron-up' : 'mdi-chevron-down'"
          @click="rent.showDetails = !rent.showDetails"
        ></v-btn>
      </v-card-actions>

      <v-expand-transition>
        <div v-show="rent.showDetails">
          <v-divider></v-divider>

          <v-card-text>
            <pre>{{ rent }}</pre>
          </v-card-text>
        </div>
      </v-expand-transition>
    </v-card>
  </v-row>
</template>

<style scoped></style>
