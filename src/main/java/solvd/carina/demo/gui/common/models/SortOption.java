package solvd.carina.demo.gui.common.models;

public enum SortOption {
    NAME_A_TO_Z("Name (A to Z)"),NAME_Z_TO_A("Name (Z to A)"),
    PRICE_LOW_TO_HIGH("Price (low to high)"),PRICE_HIGH_TO_LOW("Price (high to low)");

    private String name;

    SortOption(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}
