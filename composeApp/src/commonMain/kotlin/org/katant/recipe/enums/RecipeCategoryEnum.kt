package org.katant.recipe.enums

enum class RecipeCategoryEnum (val value: String) {
    SOUP("Супы"),
    SALAD("Салаты"),
    MAIN_COURSE("Основные блюда"),
    DESSERT("Десерты"),
    DRINK("Напитки");

    companion object {
        fun from(s: String): RecipeCategoryEnum? = entries.find { it.value == s }
    }
}