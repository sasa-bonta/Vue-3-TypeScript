import { defineStore } from 'pinia'
import { fetchBikeList } from '@/api/api'
import type { Bike } from '@/interfaces/api'
import { sleep } from '@/services'

interface State {
  items: Bike[]
  loading: boolean
  error: string | null
}

export const useBikesStore = defineStore('bike', {
  state: (): State => ({
    items: [],
    loading: false,
    error: null
  }),
  actions: {
    async fetchList() {
      this.loading = true
      this.error = null
      try {
        await sleep(1000)
        const response = await fetchBikeList()
        this.items = response.data
      } catch (err: any) {
        this.error = err.message || 'Failed to fetch bikes'
      } finally {
        this.loading = false
      }
    }
  }
})
