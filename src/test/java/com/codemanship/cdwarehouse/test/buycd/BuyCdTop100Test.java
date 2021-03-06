package com.codemanship.cdwarehouse.test.buycd;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import java.util.Arrays;
import java.util.Collection;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.*;

@RunWith(Parameterized.class)
public class BuyCdTop100Test {

    // Inputs
    private final String cdTitle;
    private final String cdArtist;
    private final int chartPosition;
    private final int stock;
    private final double ourPrice;
    private final String ccNumber;
    private final String ccExpires;
    private final String ccSecurityCode;
    private final boolean paymentAccepted;
    private final double lowestCompetitorPrice;

    // Outputs
    private final int endStock;
    private final double charged;
    private final String chartNotification;

    private Payments payments;
    private Charts charts;
    private CD cd;
    private CreditCard creditCard;
    private MarketIntel intel;

    @Parameterized.Parameters
    public static Collection<Object[]> data() {
        return Arrays.asList(new Object[][] {
                { "So", "Peter Gabriel", 100, 10, 9.99, "1234234534564567", "10/21", "817", true, 7.99, 9, 6.99, "sales: 1, album: Peter Gabriel - So" },
                { "Lionheart", "Kate Bush", 87, 5, 8.99, "1234234534564567", "10/21", "817", true, 8.99, 4, 7.99, "sales: 1, album: Kate Bush - Lionheart" }
        });
    }

    public BuyCdTop100Test(
            String cdTitle,
            String cdArtist,
            int chartPosition,
            int stock, double ourPrice,
            String ccNumber,
            String ccExpires,
            String ccSecurityCode,
            boolean paymentAccepted,
            double lowestCompetitorPrice,
            int endStock,
            double charged,
            String chartNotification
    ){

        this.cdTitle = cdTitle;
        this.cdArtist = cdArtist;
        this.chartPosition = chartPosition;
        this.stock = stock;
        this.ourPrice = ourPrice;
        this.ccNumber = ccNumber;
        this.ccExpires = ccExpires;
        this.ccSecurityCode = ccSecurityCode;
        this.paymentAccepted = paymentAccepted;
        this.lowestCompetitorPrice = lowestCompetitorPrice;
        this.endStock = endStock;
        this.charged = charged;
        this.chartNotification = chartNotification;
    }

    @Before
    public void scenario(){
        // Given
        payments = mock(Payments.class);
        when(payments.process(any(), anyDouble())).thenReturn(paymentAccepted);

        creditCard = new CreditCard(ccNumber, ccExpires, ccSecurityCode, payments);

        charts = mock(Charts.class);
        when(charts.getPosition(anyString(), anyString())).thenReturn(chartPosition);

        intel = mock(MarketIntel.class);
        when(intel.lowestCompetitorPrice(anyString(), anyString())).thenReturn(lowestCompetitorPrice);

        cd = new CD(cdTitle, cdArtist, stock, ourPrice, charts, intel);

        // When
        cd.buy(creditCard);
    }

    // Then

    @Test
    public void stockReducedByOne(){
        assertEquals(endStock, cd.getStock());
    }

    @Test
    public void cardChargedOnePoundLessThanLowestCompetitorPrice(){
        verify(payments). process(creditCard, charged);
    }

    @Test
    public void chartsNotifiedOfSale(){
        verify(charts).notifySale(chartNotification);
    }

}
