package org.katant.recipe.enums

enum class RecipeDifficultyEnum (val value: String) {
    EASY("Легко"),
    MEDIUM("Средне"),
    HARD("Сложно");

    companion object {
        fun from(s: String): RecipeDifficultyEnum? = entries.find { it.value == s }
    }

}
