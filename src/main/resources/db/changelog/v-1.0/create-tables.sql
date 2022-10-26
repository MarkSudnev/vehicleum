CREATE TABLE vehicleum.public.driver (
  id UUID PRIMARY KEY,
  name VARCHAR(255) NOT NULL,
  birth_date DATE NOT NULL,
  phone VARCHAR(32) NOT NULL,
  email VARCHAR(128) NOT NULL,
  licence VARCHAR(64) NOT NULL,
  status VARCHAR(16) NOT NULL,
  create_date TIMESTAMP NOT NULL,
  modify_date TIMESTAMP NOT NULL
);

CREATE TABLE vehicleum.public.vehicle (
  id UUID PRIMARY KEY,
  brand VARCHAR(32) NOT NULL,
  model VARCHAR(32) NOT NULL,
  production_year INT NOT NULL,
  plate VARCHAR(16) NOT NULL,
  vehicle_state VARCHAR(16) NOT NULL,
  status VARCHAR(16) NOT NULL,
  create_date TIMESTAMP NOT NULL,
  modify_date TIMESTAMP NOT NULL
);

CREATE TABLE vehicleum.public.driver_vehicle_assignation (
  id UUID PRIMARY KEY,
  driver_id UUID NOT NULL
    CONSTRAINT fk_driver_vehicle_assignation_driver REFERENCES driver(id),
  vehicle_id UUID NOT NULL
    CONSTRAINT fk_driver_vehicle_assignation_driver_vehicle REFERENCES vehicle(id),
  start_date TIMESTAMP NOT NULL,
  finish_date TIMESTAMP,
  note VARCHAR(1024),
  status VARCHAR(16) NOT NULL,
  create_date TIMESTAMP NOT NULL,
  modify_date TIMESTAMP NOT NULL
);

CREATE TABLE vehicleum.public.duty (
  id UUID PRIMARY KEY,
  driver_id UUID NOT NULL
    CONSTRAINT fk_duty_driver REFERENCES driver(id),
  start_date TIMESTAMP NOT NULL,
  finish_date TIMESTAMP,
  duty_type VARCHAR(16) NOT NULL,
  status VARCHAR(16) NOT NULL,
  create_date TIMESTAMP NOT NULL,
  modify_date TIMESTAMP NOT NULL
);

CREATE INDEX driver_vehicle_assignation_driver_id_idx
  ON vehicleum.public.driver_vehicle_assignation (driver_id);
