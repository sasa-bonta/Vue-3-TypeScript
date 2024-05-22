import { defineStore } from 'pinia'
import { fetchBikeList } from '@/api/api'
import type { Bike } from '@/interfaces/api'

export const useBikeStore = defineStore('bike', {
  state: () => ({
    bikes: [] as Array<Bike>,
    loading: false,
    error: null as string | null
  }),
  actions: {
    async fetchBikeList() {
      this.loading = true
      this.error = null
      try {
        const response = await fetchBikeList()
        this.bikes = response.data
      } catch (err: any) {
        this.error = err.message || 'Failed to fetch bikes'
      } finally {
        this.loading = false
      }
    }
  }
})
