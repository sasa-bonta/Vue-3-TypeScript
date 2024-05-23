import { defineStore } from 'pinia'
import { type Ref, ref } from 'vue'
import type { Car } from '@/interfaces/api'
import { fetchCarList } from '@/api/api'

export const useCarsStore = defineStore('cars', () => {
  const items: Ref<Array<Car>> = ref([])
  const loading: Ref<boolean> = ref(false)
  const error: Ref<null | string> = ref(null)

  const fetchList = async () => {
    loading.value = true
    error.value = null
    try {
      const response = await fetchCarList()
      items.value = response.data
    } catch (err: any) {
      error.value = err.message || 'Failed to fetch bikes'
    } finally {
      loading.value = false
    }
  }

  return {
    items,
    loading,
    error,
    fetchList
  }
})
