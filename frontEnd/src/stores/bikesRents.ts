import {defineStore} from 'pinia'
import {fetchBikeRentList} from "@/api/api";
import type {BikeRent} from "@/stores/Interfaces";

export const useBikeRentStore = defineStore('bikeRent', {

    state: () => ({
        bikeRents: [] as Array<BikeRent>,
        loading: false,
        error: null as string | null
    }),
    actions: {
        async fetchBikeRentList() {
            this.loading = true
            this.error = null
            try {
                const response = await fetchBikeRentList()
                this.bikeRents = response.data
            } catch (err: any) {
                this.error = err.message || 'Failed to fetch bike rents'
            } finally {
                this.loading = false
            }
        }
    }
})
