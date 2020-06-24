package com.codemanship.cdwarehouse.test.buycd;

public class PaymentRejectedException extends RuntimeException {
    public PaymentRejectedException(){
        super("Your payment has been rejected. Please check with your bank, or try with another card.");
    }
}
