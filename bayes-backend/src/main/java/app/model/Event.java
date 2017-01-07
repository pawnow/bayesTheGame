package app.model;

import java.io.Serializable;
import java.util.Objects;

/**
 * Created by Acer Aspire V5 on 01.01.2017.
 */
public class Event implements Serializable{
    private String description;
    private int moneyChange;

    public Event(String description, int moneyChange) {
        this.description = description;
        this.moneyChange = moneyChange;
    }

    public Event() {
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getMoneyChange() {
        return moneyChange;
    }

    public void setMoneyChange(int moneyChange) {
        this.moneyChange = moneyChange;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Event event = (Event) o;
        return moneyChange == event.moneyChange &&
                Objects.equals(description, event.description);
    }

    @Override
    public int hashCode() {
        return Objects.hash(description, moneyChange);
    }
}
