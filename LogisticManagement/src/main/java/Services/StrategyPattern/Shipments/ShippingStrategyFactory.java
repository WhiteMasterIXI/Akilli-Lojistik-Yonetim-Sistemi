package Services.StrategyPattern.Shipments;


public class ShippingStrategyFactory {

    public static IShippingStrategy get(Shipments type) {

        return switch (type) {

            case ArasKargo -> new ArasShippingStrategy();

            case YurticiKargo -> new YurticiShippingStrategy();
            
            default -> new ArasShippingStrategy();
        };
    }
}