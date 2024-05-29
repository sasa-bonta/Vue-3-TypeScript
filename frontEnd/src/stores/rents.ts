import {defineStore} from 'pinia'
import {ref, type Ref} from 'vue'
import type {BikeRent, CarRent} from '@/interfaces/api'
import {sleep} from '@/services'
import {fetchBikeRentList, fetchCarRentList} from '@/api/api'

export const useRentsStore = defineStore('rents', () => {
  const items: Ref<Array<CarRent | BikeRent>> = ref([])
  const loading: Ref<boolean> = ref(false)
  const error: Ref<null | string> = ref(null)

  const fetchList = async () => {
    loading.value = true
    error.value = null
    try {
      await sleep(1000)
      const [carRents, bikeRents] = await Promise.all([fetchCarRentList(), fetchBikeRentList()])

      if (!Array.isArray(carRents.data) || !Array.isArray(bikeRents.data)) {
        throw new Error('Invalid rent data format')
      }

      const mappedCarRents: CarRent[] = carRents.data.map((carRent: CarRent) => ({
        ...carRent,
        type: 'car'
      }))
      const mappedBikeRents: BikeRent[] = bikeRents.data.map((bikeRent: BikeRent) => ({
        ...bikeRent,
        type: 'bike'
      }))
      items.value = [...mappedCarRents, ...mappedBikeRents]
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
