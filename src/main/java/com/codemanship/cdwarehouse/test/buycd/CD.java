package com.codemanship.cdwarehouse.test.buycd;

public class CD {
    private final String title;
    private final String artist;
    private int stock;
    private final double price;
    private final Charts charts;
    private final MarketIntel intel;

    public CD(String title, String artist, int stock, double price, Charts charts, MarketIntel intel) {
        this.title = title;
        this.artist = artist;
        this.stock = stock;
        this.price = price;
        this.charts = charts;
        this.intel = intel;
    }

    public int getStock() {
        return stock;
    }

    public void buy(CreditCard creditCard) {
        if(stock == 0)
            throw new OutOfStockException(artist, title);

        creditCard.pay(calculateAmount());

        stock--;

        charts.notifySale("sales: 1, album: " + artist + " - " + title);
    }

    private double calculateAmount() {
        double amount = price;
        int chartPosition = charts.getPosition(title, artist);
        if(chartPosition <= 100){
            double lowestPrice = intel.lowestCompetitorPrice(artist, title);
            amount = lowestPrice - 1.0;
        }
        return amount;
    }

}
