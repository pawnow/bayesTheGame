package app.model;

import java.io.Serializable;
import java.util.Objects;

/**
 * Created by Acer Aspire V5 on 01.01.2017.
 */
public class Event implements Serializable{
    private String description;

    public Event(String description) {
        this.description = description;
    }

    public Event() {
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Event event = (Event) o;
        return Objects.equals(description, event.description);
    }

    @Override
    public int hashCode() {
        return Objects.hash(description);
    }
}
