interface Vehicle {
  id: number
  vin: string
  year: number
  brand: string
  model: string
  mileage: number
  engine: string
  fuel: string
  power: number
  type: string
  photo: string
  rent: boolean
  price: number
  deleted: boolean
  available: boolean
  showDetails?: boolean
  vehicleType?: string
}

interface Car extends Vehicle {
  transmission: string
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
  type?: string
  showDetails?: boolean
}

interface CarRent extends Rent {
  car: Car
}

interface BikeRent extends Rent {
  bike: Bike
}

export type { Vehicle, Car, Bike, Rent, CarRent, BikeRent }
