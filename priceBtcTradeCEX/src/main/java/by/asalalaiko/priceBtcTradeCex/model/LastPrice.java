package by.asalalaiko.priceBtcTradeCex.model;

public class LastPrice {
    private String curr1;
    private String curr2;
    private Double lprice;

    public LastPrice() {
    }

    public String getCurr1() {
        return curr1;
    }

    public void setCurr1(String curr1) {
        this.curr1 = curr1;
    }

    public String getCurr2() {
        return curr2;
    }

    public void setCurr2(String curr2) {
        this.curr2 = curr2;
    }

    public Double getLprice() {
        return lprice;
    }

    public void setLprice(Double lprice) {
        this.lprice = lprice;
    }
}
