// stores/carStore.ts
import {defineStore} from 'pinia'
import {fetchCarRentList} from "@/api/api";
import type {CarRent} from "@/interfaces/ws";

export const useCarRentsStore = defineStore('carRents', {

    state: () => ({
        carRents: [] as Array<CarRent>,
        loading: false,
        error: null as string | null
    }),
    actions: {
        async fetchCarRentsList() {
            this.loading = true
            this.error = null
            try {
                const response = await fetchCarRentList()
                this.carRents = response.data
            } catch (err: any) {
                this.error = err.message || 'Failed to fetch car rents'
            } finally {
                this.loading = false
            }
        }
    }
})
