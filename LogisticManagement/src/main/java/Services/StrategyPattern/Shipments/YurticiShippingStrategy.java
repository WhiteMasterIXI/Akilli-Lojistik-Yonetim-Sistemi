package Services.StrategyPattern.Shipments;

import java.util.UUID;

import BuildingSystem.Supplier;

public class YurticiShippingStrategy implements IShippingStrategy {

    @Override
    public double calculatePrice(Supplier supplier, double weight,
                                 double distance, boolean insurance,
                                 boolean fragile) {

        double price =
                supplier.getBasePrice()
                + (weight * supplier.getPricePerKg())
                + (distance * supplier.getDistanceMultiplier());

        if (insurance) price += 50;
        if (fragile) price += 25;

        return price;
    }

    @Override
    public String generateTrackingNumber(Supplier supplier) {
        return "YURTICI-" + UUID.randomUUID();
    }
}
