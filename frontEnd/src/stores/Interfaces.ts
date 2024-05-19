interface Interfaces {
    id: number,
    vin: string,
    year: number,
    brand: string,
    model: string,
    mileage: number,
    state: { available: boolean },
    engine: string,
    fuel: string,
    power: number,
    type: string,
    photo: string,
    rent: boolean,
    price: number,
    deleted: boolean,
    transmission: string,
    available: boolean
}

interface Car extends Interfaces {
    drivetrain: string,
    seats: number,
}

interface Bike extends Interfaces{
    streetLegal: boolean
    backSeat: boolean
}

export type {
    Car, Bike
}

