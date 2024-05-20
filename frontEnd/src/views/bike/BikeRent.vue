<script setup lang="ts">
import {computed, type ComputedRef, onMounted, ref} from 'vue'
import {storeToRefs} from "pinia";
import {useBikeRentStore} from "@/stores/bikesRents";
import type {BikeRent} from "@/interfaces/ws";

const store = useBikeRentStore()
const {bikeRents, loading, error} = storeToRefs(store)

onMounted(() => {
  store.fetchBikeRentList()
})

const idnp = ref('');
const phone = ref('');
const email = ref('');
const showCompleted = ref(false)

const filteredBikeRents: ComputedRef<Array<BikeRent>> = computed(() => {
  return bikeRents.value.filter((rent: BikeRent) => {
    const matchesIdnp = idnp.value ? rent.idnp.includes(idnp.value) : true;
    const matchesPhone = phone.value ? rent.tel.includes(phone.value) : true;
    const matchesEmail = email.value ? rent.email.includes(email.value) : true;
    const matchesCompleted = showCompleted.value ? true : rent.finishDate === null;

    return matchesIdnp && matchesPhone && matchesEmail && matchesCompleted;
  });
});

</script>

<template>
  <v-container>

    <div class="mb-6 px-6 pt-6 pb-0 bg-surface-variant">
      <v-row>
        <v-col cols="10">
          <v-row>
            <v-col cols="4">
              <v-text-field
                  v-model="idnp"
                  :counter="10"
                  label="IDNP"
                  hide-details
                  required
              ></v-text-field>
            </v-col>

            <v-col cols="4">
              <v-text-field
                  v-model="phone"
                  :counter="10"
                  label="Phone"
                  hide-details
                  required
              ></v-text-field>
            </v-col>

            <v-col cols="4">
              <v-text-field
                  v-model="email"
                  label="E-mail"
                  hide-details
                  required
              ></v-text-field>
            </v-col>
          </v-row>
        </v-col>

        <v-col cols="2">
          <v-checkbox label="Show completed" v-model="showCompleted"></v-checkbox>
        </v-col>
      </v-row>
    </div>

    <div class="bg-surface-variant mb-4 px-4 py-4" v-for="rent in filteredBikeRents" :key="rent.id">
      <v-row>

        <v-col cols="10">
          <v-row class="ml-3 mr-3">
            <h4>
              <b>{{
                  `Vehicle info: rentId-${rent.id}, startDate-${rent.startDate}, finishDate-${rent.finishDate},
                rent days-${rent.numberOfDays}, price per day-${rent.pricePerDay}, BikeId-${rent.bike.id}`
                }}</b>
            </h4>
          </v-row>

          <v-row class="ml-3 mr-3">
            <h4>
              <b>{{ `Client info: IDNP-${rent.idnp}, tel-${rent.tel}, email-${rent.email}` }}</b>
            </h4>
          </v-row>
        </v-col>

        <v-col cols="2">
          <v-btn variant="outlined" class="mb-2 button-border w-100" color="blue" :disabled="rent.finishDate!=null">
            <h3><b>end rent</b></h3>
          </v-btn>
        </v-col>

      </v-row>
    </div>

  </v-container>

</template>


<style scoped>

.button-border {
  border-width: 3px;
}

</style>