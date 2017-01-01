package app.model;

import java.util.Objects;

/**
 * Created by Acer Aspire V5 on 31.12.2016.
 */

public class Player {
    private String name;
    private String location;
    private String weather;
    private int age;
    private int money;

    public Player() {
    }

    public Player(String name, String location, String weather, int age, int money) {
        this.name = name;
        this.location = location;
        this.weather = weather;
        this.age = age;
        this.money = money;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getWeather() {
        return weather;
    }

    public void setWeather(String weather) {
        this.weather = weather;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public int getMoney() {
        return money;
    }

    public void setMoney(int money) {
        this.money = money;
    }

    public Player withName(String name) {
        this.name = name;
        return this;
    }

    public Player withLocation(String location) {
        this.location = location;
        return this;
    }

    public Player withWeather(String weather) {
        this.weather = weather;
        return this;
    }

    public Player withAge(int age) {
        this.age = age;
        return this;
    }

    public Player withMoney(int money) {
        this.money = money;
        return this;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Player player = (Player) o;
        return age == player.age &&
                money == player.money &&
                Objects.equals(name, player.name) &&
                Objects.equals(location, player.location) &&
                Objects.equals(weather, player.weather);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, location, weather, age, money);
    }
}
