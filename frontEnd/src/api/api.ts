import axios from "axios";

const api = axios.create({
    baseURL: "http://localhost:5173/api/"
})

export const fetchBikeList = () => api.get("bike/getAll")
export const fetchBikeRentList = () => api.get("rent/bike/getAll")

export const fetchCarList = () => api.get("car/getAll")
export const fetchCarRentList = () => api.get("rent/car/getAll")
export const deleteCarById = (id: number) => axios.delete(`http://localhost:8080/car/delete/${id}`)