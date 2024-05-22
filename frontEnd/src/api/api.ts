import axios, { type AxiosInstance, type AxiosResponse } from 'axios'
import type { Bike, Car, CarRent } from '@/interfaces/api'

const api: AxiosInstance = axios.create({
  baseURL: 'http://localhost:8080/'
})

export const fetchBikeList = (): Promise<AxiosResponse<Bike[]>> => api.get('bike/getAll')
export const createBike = (form: BikeForm): Promise<AxiosResponse<Bike>> =>
  api.post('bike/create', form)
export const deleteBikeById = (id: number): Promise<AxiosResponse<void>> =>
  api.delete(`bike/delete/${id}`)

export const fetchBikeRentList = () => api.get('rent/bike/getAllRents')
export const createBikeRent = (form: BikeRentForm) => api.post('rent/bike/createRent', form)

export const fetchCarList = (): Promise<AxiosResponse<Car[]>> => api.get('car/getAll')
export const createCar = (form: CarForm): Promise<AxiosResponse<Car>> =>
  api.post('car/create', form)
export const deleteCarById = (id: number): Promise<AxiosResponse<void>> =>
  api.delete(`car/delete/${id}`)

export const fetchCarRentList = (): Promise<AxiosResponse<CarRent[]>> =>
  api.get('rent/car/getAllRents')
export const createCarRent = (form: CarRentForm): Promise<AxiosResponse<CarRent>> =>
  api.post('rent/car/createRent', form)
