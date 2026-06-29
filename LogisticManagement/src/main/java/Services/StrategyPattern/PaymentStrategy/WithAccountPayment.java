package Services.StrategyPattern.PaymentStrategy;

import Services.SingletonPattern.LogService;

public class WithAccountPayment
implements IPaymentStrategy {

    @Override
    public boolean Pay(double miktar) {
    	
    	String metin = miktar
                + " TL hesap bakiyesi ile ödendi.";
        System.out.println(
                miktar
                + " TL hesap bakiyesi ile ödendi."
        );
        
        LogService.getInstance().log(metin);

        return true;
    }
}