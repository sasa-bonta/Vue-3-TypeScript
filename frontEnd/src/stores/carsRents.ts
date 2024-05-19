// stores/carStore.ts
import {defineStore} from 'pinia'
import {fetchCarList} from "@/api/api";
import type {CarRent} from "@/stores/Interfaces";

export const useCarStore = defineStore('car', {

    state: () => ({
        carRents: [] as Array<CarRent>,
        loading: false,
        error: null as string | null
    }),
    actions: {
        async fetchCarList() {
            this.loading = true
            this.error = null
            try {
                const response = await fetchCarList()
                this.carRents = response.data
            } catch (err: any) {
                this.error = err.message || 'Failed to fetch car rents'
            } finally {
                this.loading = false
            }
        }
    }
})
