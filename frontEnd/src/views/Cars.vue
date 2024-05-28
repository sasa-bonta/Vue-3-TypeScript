<script setup lang="ts">
import {useCarsStore} from '@/stores/cars'
import {computed, onMounted, reactive} from 'vue'
import {storeToRefs} from 'pinia'

const store = useCarsStore()
const {items, loading, error} = storeToRefs(store)
const {fetchList} = store

onMounted(() => {
  fetchList()
})

const cars = computed(() => {
  return items.value.map((obj) => {
    return reactive({...obj, showDetails: false})
  })
})

const list = [
  {text: 'Real-Time', icon: 'mdi-clock'},
  {text: 'Audience', icon: 'mdi-account'},
  {text: 'Conversions', icon: 'mdi-flag'}
]
</script>

<template>
  <v-container v-if="!loading">
    <v-row v-for="car in cars" :key="car.id" class="my-4">
      <v-card class="mx-auto" width="1280">
        <v-container>
          <v-row>
            <v-col cols="3">
              <v-img width="250" height="200" :src="car.photo" cover class="rounded"></v-img>
            </v-col>
            <v-col cols="3">
              <v-list>
                <v-list-subheader>Technical information</v-list-subheader>

                <v-list-item color="primary" variant="plain">
                  <template v-slot:prepend>
                    <v-icon icon="mdi-car-shift-pattern" ></v-icon>
                  </template>

                  <v-list-item-title>{{ car.transmission }}</v-list-item-title>
                </v-list-item>

                <v-list-item color="primary" variant="plain">
                  <template v-slot:prepend>
                    <v-icon icon="mdi-engine"></v-icon>
                  </template>

                  <v-list-item-title>{{ car.power }} HP</v-list-item-title>
                </v-list-item>

                <v-list-item color="primary" variant="plain">
                  <template v-slot:prepend>
                    <v-icon icon="mdi-car-side"></v-icon>
                  </template>

                  <v-list-item-title>{{ car.type }}</v-list-item-title>
                </v-list-item>
              </v-list>
            </v-col>
            <v-col cols="3">
              <v-list>
                <v-list-subheader>General data</v-list-subheader>

                <v-list-item color="primary" variant="plain">
                  <template v-slot:prepend>
                    <v-icon icon="mdi-id-card"></v-icon>
                  </template>

                  <v-list-item-title>{{ car.vin }}</v-list-item-title>
                </v-list-item>

                <v-list-item color="primary" variant="plain">
                  <template v-slot:prepend>
                    <v-icon icon="mdi-counter"></v-icon>
                  </template>

                  <v-list-item-title>{{ car.mileage }} KM</v-list-item-title>
                </v-list-item>

                <v-list-item color="primary" variant="plain">
                  <template v-slot:prepend>
                    <v-icon icon="mdi-fuel"></v-icon>
                  </template>

                  <v-list-item-title>{{ car.fuel }}</v-list-item-title>
                </v-list-item>
              </v-list>
            </v-col>
            <v-col cols="3">
              <v-list>
                <v-list-subheader>Additional information</v-list-subheader>

                <v-list-item color="primary" variant="plain">
                  <template v-slot:prepend>
                    <v-icon icon="mdi-seat-passenger"></v-icon>
                  </template>

                  <v-list-item-title>{{ car.seats }}</v-list-item-title>
                </v-list-item>

                <v-list-item color="primary" variant="plain">
                  <template v-slot:prepend>
                    <v-icon icon="mdi-calendar"></v-icon>
                  </template>

                  <v-list-item-title>{{ car.year }}</v-list-item-title>
                </v-list-item>

                <v-list-item color="primary" variant="plain">
                  <template v-slot:prepend>
                    <v-icon icon="mdi-cash"></v-icon>
                  </template>

                  <v-list-item-title>{{ car.price }} €</v-list-item-title>
                </v-list-item>
              </v-list>
            </v-col>
          </v-row>
        </v-container>

        <v-card-title> {{ car.brand }} {{ car.model }}</v-card-title>

        <v-card-subtitle> 1,000 miles of wonder</v-card-subtitle>

        <v-card-actions>
          <v-btn color="orange-lighten-2" text="Rent now"></v-btn>

          <v-btn color="deep-purple-lighten-2" text="View rent history"></v-btn>

          <v-spacer></v-spacer>

          <v-btn
              :icon="car.showDetails ? 'mdi-chevron-up' : 'mdi-chevron-down'"
              @click="car.showDetails = !car.showDetails"
          ></v-btn>
        </v-card-actions>

        <v-expand-transition>
          <div v-show="car.showDetails">
            <v-divider></v-divider>

            <v-card-text>
              <pre>{{ car }}</pre>
            </v-card-text>
          </div>
        </v-expand-transition>
      </v-card>
    </v-row>
  </v-container>

  <v-container v-else>
    <v-row v-for="n in 3" :key="n" class="my-4">
      <v-card class="mx-auto" width="1280">
        <v-container>
          <v-row>
            <v-col cols="3">
              <v-skeleton-loader
                  width="250"
                  height="330"
                  type="card, subtitle, actions"
              ></v-skeleton-loader>
            </v-col>
            <v-col cols="3" v-for="i in 3" :key="i">
              <v-skeleton-loader
                  max-width="300"
                  type="list-item"
              ></v-skeleton-loader>
              <v-skeleton-loader
                  v-for="i in 3" :key="i"
                  max-width="300"
                  type="list-item-avatar"
              ></v-skeleton-loader>
            </v-col>
          </v-row>
        </v-container>
      </v-card>

    </v-row>
  </v-container>
</template>

<style scoped></style>
