package com.codemanship.cdwarehouse.test.buycd;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import static org.mockito.Matchers.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class BuyCdPaymentRejectedTest {

    @Rule
    public ExpectedException exception = ExpectedException.none();

    @Test
    public void throwsPaymentRejectedError(){
        exception.expect(PaymentRejectedException.class);
        exception.expectMessage("Your payment has been rejected. " + "" +
                "Please check with your bank, or try with another card.");

        Charts charts = mock(Charts.class);
        when(charts.getPosition(anyString(), anyString())).thenReturn(101);

        Payments payments = mock(Payments.class);
        when(payments.process(any(), anyDouble())).thenReturn(false);

        CD cd = new CD("Lionheart", "Kate Bush",	5,	8.99, charts, null);
        cd.buy(new CreditCard("", "", "", payments));
    }
}
