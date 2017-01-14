package app.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
@AllArgsConstructor
public class Player {
    private String name;
    private String location;
    private String weather;
    private int age;
    private int money;
}
