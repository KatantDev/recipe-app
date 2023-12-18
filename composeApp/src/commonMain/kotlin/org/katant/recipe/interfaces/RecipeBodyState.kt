package org.katant.recipe.interfaces

import androidx.compose.runtime.snapshots.SnapshotStateList

interface RecipeBodyStateInterface {
    var id: Long
    val recipeBodyState: SnapshotStateList<RecipeBodyStateInterface>
    fun changePositionTo(nextId: Long, currentIndex: Int, nextIndex: Int)
    fun delete()
}