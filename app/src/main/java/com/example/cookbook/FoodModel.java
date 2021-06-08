package com.example.cookbook;

public class FoodModel {
    private Integer id;
    private String dish_name, dish_desc, image;

    public FoodModel(String dish_name, String dish_desc, String image) {
        this.dish_name = dish_name;
        this.dish_desc = dish_desc;
        this.image = image;
    }

    public FoodModel(Integer id, String dish_name, String dish_desc, String image) {
        this.id = id;
        this.dish_name = dish_name;
        this.dish_desc = dish_desc;
        this.image = image;
    }

    @Override
    public String toString() {
        return "FoodModel{" +
                "id=" + id +
                ", dish_name='" + dish_name + '\'' +
                ", dish_desc='" + dish_desc + '\'' +
                ", image='" + image + '\'' +
                '}';
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getDish_name() {
        return dish_name;
    }

    public void setDish_name(String dish_name) {
        this.dish_name = dish_name;
    }

    public String getDish_desc() {
        return dish_desc;
    }

    public void setDish_desc(String dish_desc) {
        this.dish_desc = dish_desc;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }
}
