<script setup lang="ts">

import {onMounted} from 'vue'
import {storeToRefs} from "pinia";
import {useBikeRentStore} from "@/stores/bikesRents";

const store = useBikeRentStore()
const {bikeRents, loading, error} = storeToRefs(store)

onMounted(() => {
  store.fetchBikeRentList()
})

</script>

<template>
  <v-container id="container">

    <div class="bg-surface-variant mb-4 px-4 py-4" v-for="rent in bikeRents" :key="rent.id">
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

#container {
  width: 1280px;
}

.button-border {
  border-width: 3px;
}

</style>