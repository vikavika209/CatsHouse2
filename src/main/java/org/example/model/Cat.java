package org.example.model;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

public class Cat {

    private String id;

    @NotBlank(message = "Name is required")
    @Pattern(regexp = "^[A-Za-zА-Яа-яЁё]+$", message = "Invalid string format")
    private String name;

    @NotBlank(message = "Breed is required")
    @Pattern(regexp = "^[A-Za-zА-Яа-яЁё]+$", message = "Invalid string format")
    private String breed;

    private int age;

    public Cat() {
    }

    public Cat(String id, String name, String breed, int age) {
        this.id = id;
        this.name = name;
        this.breed = breed;
        this.age = age;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getBreed() {
        return breed;
    }

    public void setBreed(String breed) {
        this.breed = breed;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
}
