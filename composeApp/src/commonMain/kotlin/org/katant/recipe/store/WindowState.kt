package org.katant.recipe.store

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import org.katant.recipe.enums.WindowsEnum

class WindowState {
    private val defaultWindow = WindowsEnum.RECIPES_LIST;
    var currentWindow by mutableStateOf(defaultWindow)
        private set

    fun updateWindow(newWindow: WindowsEnum) {
        currentWindow = newWindow
    }

    fun reset() {
        currentWindow = defaultWindow
    }
}