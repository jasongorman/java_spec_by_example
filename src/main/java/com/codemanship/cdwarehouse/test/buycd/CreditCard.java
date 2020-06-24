package com.codemanship.cdwarehouse.test.buycd;

public class CreditCard {
    private final String ccNumber;
    private final String ccExpiry;
    private final String ccSecurityCode;
    private final Payments payments;

    public CreditCard(String ccNumber, String ccExpiry, String ccSecurityCode, Payments payments) {
        this.ccNumber = ccNumber;
        this.ccExpiry = ccExpiry;
        this.ccSecurityCode = ccSecurityCode;
        this.payments = payments;
    }

    public String getCcNumber() {
        return ccNumber;
    }

    public String getCcExpiry() {
        return ccExpiry;
    }

    public String getCcSecurityCode() {
        return ccSecurityCode;
    }

    void pay(double amount) {

        if(!payments.process(this, amount)){
            throw new PaymentRejectedException();
        }
    }
}
