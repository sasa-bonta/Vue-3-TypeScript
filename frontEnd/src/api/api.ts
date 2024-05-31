import axios, {type AxiosInstance} from 'axios'

const api: AxiosInstance = axios.create({
  baseURL: 'http://localhost:8080/'
})

export const fetchBikeList = () => api.get('bike/getAll')
export const createBike = (form: BikeForm) => api.post('bike/create', form)

export const fetchCarList = () => api.get('car/getAll')
export const createCar = (form: CarForm) => api.post('car/create', form)

export const deleteVehicleById = (type: string, id: number) => api.delete(`${type}/delete/${id}`)

export const fetchBikeRentList = () => api.get('rent/bike/getAllRents')
export const createBikeRent = (form: BikeRentForm) => api.post('rent/bike/createRent', form)

export const fetchCarRentList = () => api.get('rent/car/getAllRents')
export const createCarRent = (form: CarRentForm) => api.post('rent/car/createRent', form)

export const endRent = (type: string, id: number) => api.put(`rent/${type}/endRent/${id}`)
