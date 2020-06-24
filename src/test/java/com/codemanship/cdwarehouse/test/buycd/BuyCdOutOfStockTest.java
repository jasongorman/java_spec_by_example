package com.codemanship.cdwarehouse.test.buycd;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

public class BuyCdOutOfStockTest {

    @Rule
    public ExpectedException exception = ExpectedException.none();

    @Test
    public void throwsOutOfStockError(){
        exception.expect(OutOfStockException.class);
        exception.expectMessage("We currently do not have stock available of So by Peter Gabriel. " +
                "Please contact sales@mycdwarehouse.biz to order a copy.");
        new CD("So", "Peter Gabriel", 0, 0.0, null, null).buy(null);
    }
}
