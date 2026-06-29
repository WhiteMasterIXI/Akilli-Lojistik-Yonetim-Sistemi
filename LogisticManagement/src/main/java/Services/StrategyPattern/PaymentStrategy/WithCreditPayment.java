package Services.StrategyPattern.PaymentStrategy;

import Services.SingletonPattern.LogService;

//import Utils.LogRecorder;

public class WithCreditPayment
implements IPaymentStrategy {

    @Override
    public boolean Pay(double miktar) {

    	String metin = miktar
                + " TL kredi kartı ile ödendi.";
        System.out.println(
        		metin
        );

        
        LogService.getInstance().log(metin);

        return true;
    }
}