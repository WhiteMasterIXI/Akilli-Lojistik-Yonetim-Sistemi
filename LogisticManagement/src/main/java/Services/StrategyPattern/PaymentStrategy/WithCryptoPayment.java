package Services.StrategyPattern.PaymentStrategy;

import Services.SingletonPattern.LogService;

//import Utils.LogRecorder;

public class WithCryptoPayment
implements IPaymentStrategy {

    @Override
    public boolean Pay(double miktar) {
    	
    	String metin = miktar
                + " TL değerinde kripto ödeme yapıldı.";
        System.out.println(
        		metin
        );

        LogService.getInstance().log(metin);

        return true;
    }
}