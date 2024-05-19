import axios from "axios";

const api = axios.create({
    baseURL: "http://localhost:5173/api/"
})

export const fetchCarList = () => api.get("car/getAll")
export const fetchBikeList = () => api.get("bike/getAll")
export const fetchCarRentList = () => api.get("rent/car/getAll")
export const fetchBikeRentList = () => api.get("rent/bike/getAll")