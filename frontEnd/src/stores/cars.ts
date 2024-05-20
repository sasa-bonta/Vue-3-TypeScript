// stores/carStore.ts
import {defineStore} from 'pinia'
import {fetchCarList} from "@/api/api";
import type {Car} from "@/interfaces/ws";

export const useCarStore = defineStore('car', {

    state: () => ({
        cars: [] as Array<Car>,
        loading: false,
        error: null as string | null
    }),
    actions: {
        async fetchCarList() {
            this.loading = true
            this.error = null
            try {
                const response = await fetchCarList()
                this.cars = response.data
            } catch (err: any) {
                this.error = err.message || 'Failed to fetch cars'
            } finally {
                this.loading = false
            }
        }
    }
})
