interface VehicleForm {
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
  seats: number
}

interface CarForm extends VehicleForm {
  transmission: string
  drivetrain: string
  seats: number
}

interface BikeForm extends VehicleForm {
  streetLegal: boolean
  backSeat: boolean
}

interface RentForm {
  numberOfDays: number
  pricePerDay: number
  idnp: string
  tel: string
  email: string
  firstName: string
  lastName: string
}

interface CarRentForm extends RentForm {
  car: { id: number }
}

interface BikeRentForm extends RentForm {
  bike: { id: number }
}
