package com.example.gdsc_hackerton.domain;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;

public enum Category {
    C("C"), JS("JS"), PYTHON("PYTHON"), JAVA("JAVA"), CPP("CPP");

    private final String category;

    Category(String category) {
        this.category = category;
    }

    @JsonCreator
    public static Category from(String value) {
        for (Category category : Category.values()) {
            if (category.getCategory().equals(value)) {
                return category;
            }
        }
        return null;
    }

    @JsonValue
    public String getCategory() {
        return category;
    }
}
