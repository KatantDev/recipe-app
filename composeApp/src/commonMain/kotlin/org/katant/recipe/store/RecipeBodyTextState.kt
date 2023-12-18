package org.katant.recipe.store

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.compose.runtime.snapshots.SnapshotStateList
import org.katant.recipe.Database
import org.katant.recipe.interfaces.RecipeBodyStateInterface
import java.util.*

class RecipeBodyTextState(
    id: Long,
    value: String = "",
    private val database: Database,
    override val recipeBodyState: SnapshotStateList<RecipeBodyStateInterface>,
    isEditable: Boolean = false
): RecipeBodyStateInterface {
    override var id by mutableStateOf(id)
    var value by mutableStateOf(value)
    var isEditable by mutableStateOf(isEditable)

    fun saveChanges() {
        isEditable = false
        database.recipeBodyQueries.updateBodyById(body = value, id = id)
    }

    override fun changePositionTo(nextId: Long, currentIndex: Int, nextIndex: Int) {
        val currentPosition = database.recipeBodyQueries.selectById(id = id).executeAsOne().position
        val nextPosition = database.recipeBodyQueries.selectById(id = nextId).executeAsOne().position
        database.recipeBodyQueries.updatePosition(
            nextPosition = nextPosition,
            currentId = id
        )
        database.recipeBodyQueries.updatePosition(
            nextPosition = currentPosition,
            currentId = nextId
        )
        Collections.swap(
            recipeBodyState,
            currentIndex,
            nextIndex
        )
    }

    override fun delete() {
        database.recipeBodyQueries.updatePositionToLower(id = id)
        database.recipeBodyQueries.deleteById(id = id)
        recipeBodyState.remove(this)
    }

    init {
        recipeBodyState.add(this)
    }
}

fun createRecipeBodyTextState(
    database: Database,
    recipeId: Long,
    recipeBodyState: SnapshotStateList<RecipeBodyStateInterface>
) {
    database.recipeBodyQueries.insert(
        recipeId = recipeId,
        type = "text",
        body = "",
        position = database.recipeBodyQueries.selectMaxPosition(recipeId = recipeId).executeAsOne().MAX?.plus(1) ?: 0
    )
    RecipeBodyTextState(
        id = database.recipeBodyQueries.selectLastId(recipeId = recipeId).executeAsOne().MAX!!,
        database = database,
        recipeBodyState = recipeBodyState,
    )
}