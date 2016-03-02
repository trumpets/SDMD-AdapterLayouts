package gr.academic.city.sdmd.adapterlayouts;

/**
 * Created by trumpets on 3/2/16.
 */
public class Item {

    private String name;
    private int quantity;
    private int color;

    public Item(String name, int quantity, int color) {
        this.name = name;
        this.quantity = quantity;
        this.color = color;
    }

    public String getName() {
        return name;
    }

    public int getQuantity() {
        return quantity;
    }

    public int getColor() {
        return color;
    }

    // Necessary because Android's ArrayAdapter knows how to render strings only
    public String toString() {
        return this.name;
    }
}
