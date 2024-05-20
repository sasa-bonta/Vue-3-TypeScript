import axios from 'axios'

const api = axios.create({
  baseURL: 'http://localhost:5173/api/'
})

const apiBack = axios.create({
  baseURL: 'http://localhost:8080/'
})

export const fetchBikeList = () => api.get('bike/getAll')
export const fetchBikeRentList = () => api.get('rent/bike/getAll')
export const createBike = (form: BikeForm) => apiBack.post('bike/create', form)
export const deleteBikeById = (id: number) => apiBack.delete(`bike/delete/${id}`)
export const fetchCarList = () => api.get('car/getAll')
export const fetchCarRentList = () => api.get('rent/car/getAll')
export const createCar = (form: CarForm) => apiBack.post('car/create', form)
export const deleteCarById = (id: number) => apiBack.delete(`car/delete/${id}`)
