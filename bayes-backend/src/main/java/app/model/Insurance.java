package app.model;

import java.util.Objects;

/**
 * Created by Acer Aspire V5 on 31.12.2016.
 */
public class Insurance {

    public Insurance() {
    }

    public Insurance(String name, int price) {
        this.name = name;
        this.price = price;
    }

    private String name;
    private int price;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Insurance insurance = (Insurance) o;
        return price == insurance.price &&
                Objects.equals(name, insurance.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, price);
    }
}
