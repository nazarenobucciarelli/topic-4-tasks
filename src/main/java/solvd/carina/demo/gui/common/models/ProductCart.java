package solvd.carina.demo.gui.common.models;

public class ProductCart extends Product{

    private final Integer amount;

    public ProductCart(String title, Float price, Integer amount) {
        super(title, price);
        this.amount = amount;
    }

    public Integer getAmount() {
        return amount;
    }
}
