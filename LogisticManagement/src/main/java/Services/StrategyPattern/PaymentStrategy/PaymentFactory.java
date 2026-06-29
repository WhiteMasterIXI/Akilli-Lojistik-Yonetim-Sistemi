package Services.StrategyPattern.PaymentStrategy;

public class PaymentFactory {
	
	public static IPaymentStrategy setStrategy(PaymentType type) {
        return switch (type) {

        case CREDIT_CARD -> new WithCreditPayment();

        case BANK_TRANSFER -> new WithAccountPayment();

        case CRYPTO -> new WithCryptoPayment();
    };
	}

}
