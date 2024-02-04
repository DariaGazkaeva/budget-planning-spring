package ru.kpfu.itis.dariagazkaeva.budgetplanning.utils;

public class CurrencyRate {
    private String currency1;
    private String currency2;
    private String rates;

    public CurrencyRate(String currency1, String currency2, String rates) {
        this.currency1 = currency1;
        this.currency2 = currency2;
        this.rates = rates;
    }

    public CurrencyRate() {

    }

    public String getCurrency1() {
        return currency1;
    }

    public void setCurrency1(String currency1) {
        this.currency1 = currency1;
    }

    public String getCurrency2() {
        return currency2;
    }

    public void setCurrency2(String currency2) {
        this.currency2 = currency2;
    }

    public String getRates() {
        return rates;
    }

    public void setRates(String rates) {
        this.rates = rates;
    }
}
