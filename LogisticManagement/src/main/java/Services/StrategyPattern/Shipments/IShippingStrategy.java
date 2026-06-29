package Services.StrategyPattern.Shipments;

import BuildingSystem.Supplier;

public interface IShippingStrategy {

    double calculatePrice(Supplier supplier,
                          double weight,
                          double distance,
                          boolean insurance,
                          boolean fragile);

    String generateTrackingNumber(Supplier supplier);
}