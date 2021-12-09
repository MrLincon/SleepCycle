package com.whitespace.sleepcycle;

public class SliderItem {
    private String type,age,hours;

    public SliderItem(String type, String age, String hours) {
        this.type = type;
        this.age = age;
        this.hours = hours;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getAge() {
        return age;
    }

    public void setAge(String age) {
        this.age = age;
    }

    public String getHours() {
        return hours;
    }

    public void setHours(String hours) {
        this.hours = hours;
    }
}
