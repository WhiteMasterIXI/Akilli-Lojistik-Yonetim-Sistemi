package Services.StrategyPattern.Shipments;

import BuildingSystem.Supplier;

public class ArasShippingStrategy implements IShippingStrategy {

    @Override
    public double calculatePrice(Supplier supplier, double weight,
                                 double distance, boolean insurance,
                                 boolean fragile) {

        double price =
                supplier.getBasePrice()
                + (weight * supplier.getPricePerKg())
                + (distance * supplier.getDistanceMultiplier());

        return price;
    }

    @Override
    public String generateTrackingNumber(Supplier supplier) {
        return "ARAS-" + System.currentTimeMillis();
    }
}