package org.katant.recipe.store

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.compose.runtime.snapshots.SnapshotStateList
import org.katant.recipe.Database

class RecipeIngredientState (
    id: Long,
    measure: String? = "",
    quantity: Double? = null,
    foodName: String = "",
    private val database: Database,
    private val ingredientsState: SnapshotStateList<RecipeIngredientState>,
    isEditable: Boolean = false
) {
    var id by mutableStateOf(id)
    var measure by mutableStateOf(measure ?: "")
    var quantity by mutableStateOf(quantity)
    var foodName by mutableStateOf(foodName)
    var isEditable by mutableStateOf(isEditable)

    fun saveChanges() {
        isEditable = false
        database.recipeIngredientQueries.updateValueById(
            measure = measure,
            quantity = quantity,
            foodName = foodName,
            id = id
        )
    }

    fun delete() {
        database.recipeBodyQueries.deleteById(id = id)
        ingredientsState.remove(this)
    }

    init {
        ingredientsState.add(this)
    }
}

fun createIngredientState(
    database: Database,
    recipeId: Long,
    ingredientsState: SnapshotStateList<RecipeIngredientState>
) {
    database.recipeIngredientQueries.insert(
        recipeId = recipeId,
        foodName = "",
        measure = "",
        quantity = null
    )
    RecipeIngredientState(
        id =  database.recipeIngredientQueries.selectLastId(recipeId = recipeId).executeAsOne().MAX!!,
        database = database,
        ingredientsState = ingredientsState,
    )
}