interface Vehicle {
  id: number
  vin: string
  year: number
  brand: string
  model: string
  mileage: number
  state: { available: boolean }
  engine: string
  fuel: string
  power: number
  type: string
  photo: string
  rent: boolean
  price: number
  deleted: boolean
  transmission: string
  available: boolean
}

interface Car extends Vehicle {
  drivetrain: string
  seats: number
}

interface Bike extends Vehicle {
  streetLegal: boolean
  backSeat: boolean
}

interface Rent {
  id: number
  startDate: string
  finishDate: string | null
  numberOfDays: number
  pricePerDay: number
  idnp: string
  tel: string
  email: string
}

interface CarRent extends Rent {
  car: Car
}

interface BikeRent extends Rent {
  bike: Bike
}

export type { Car, Bike, CarRent, BikeRent }
