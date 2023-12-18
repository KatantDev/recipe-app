package org.katant.recipe.enums

enum class LoadingEdamamStatus (val value: String) {
    LOADING("Загружаем список рецептов, яндекс переводчик мы в тебя верим"),
    EDAMAM_REQUEST("Яндекс переводчик справился. Идём искать подходящие рецепты"),
    EDAMAM_LOADED("Мы нашли подходящие рецепты, но они на английском. Переводим..."),
    TRANSLATING_RECIPE("Переводим данные о рецепте"),
    LOADED("LOADED"),
}