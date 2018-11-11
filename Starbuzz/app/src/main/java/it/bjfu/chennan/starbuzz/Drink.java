package it.bjfu.chennan.starbuzz;

public class Drink {
    private String name;
    private String description;
    private int imageResourceId;

    public static final Drink[] drinks = new Drink[]{
            new Drink("Lattle", "A couple of espresso with steamed milk", R.drawable.latte),
            new Drink("cappuccino", "Espresso, hot milk, and a steamed milk foam.", R.drawable.cappuccino),
            new Drink("filter", "Highest quality beans roasted and brewed fresh.", R.drawable.filter)
    };

    @Override
    public String toString() {
        return this.name;
    }

    private Drink(String name, String description, int imageResourceId) {
        this.name = name;
        this.description = description;
        this.imageResourceId = imageResourceId;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public int getImageResourceId() {
        return imageResourceId;
    }
}

