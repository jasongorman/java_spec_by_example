package com.codemanship.cdwarehouse.test.buycd;

public class OutOfStockException extends RuntimeException {
    public OutOfStockException(String artist, String title) {
        super("We currently do not have stock available of " + title + " by " + artist +
                ". Please contact sales@mycdwarehouse.biz to order a copy.");
    }
}
