package com.codemanship.cdwarehouse.test.buycd;

public interface Payments {
    boolean process(CreditCard card, double amount);
}
