package pl.flathumor.vehicleum.shared;

public abstract class VehicleumException extends RuntimeException {

  public VehicleumException(final String message) {
    super(message);
  }
}
