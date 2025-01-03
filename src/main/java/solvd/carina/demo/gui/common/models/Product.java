package solvd.carina.demo.gui.common.models;

import java.util.Objects;

public class Product {
    private String title;

    private Float price;

    public Product(String title, Float price) {
        this.title = title;
        this.price = price;
    }

    public String getTitle() {
        return title;
    }

    public Float getPrice() {
        return price;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        Product other = (Product) obj;
        return Double.compare(price, other.price) == 0 && Objects.equals(title, other.title);
    }
}
