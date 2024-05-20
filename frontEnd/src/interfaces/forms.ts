interface VehicleForm {
    vin: string,
    year: number,
    brand: string,
    model: string,
    mileage: number,
    engine: string,
    fuel: string,
    power: number,
    type: string,
    photo: string,
    rent: boolean,
    price: number,
    seats: number,
}

interface CarForm extends VehicleForm {
    transmission: string,
    drivetrain: string,
    seats: number
}