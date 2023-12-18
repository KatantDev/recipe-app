package org.katant.recipe.presentation.components

import androidx.compose.animation.core.animateDpAsState
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.MaterialTheme
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.input.pointer.PointerEventType
import androidx.compose.ui.input.pointer.onPointerEvent
import androidx.compose.ui.unit.dp
import org.katant.recipe.store.WindowState
import org.katant.recipe.enums.WindowsEnum


@OptIn(ExperimentalComposeUiApi::class)
@Composable
fun CreateRecipeCard(windowState: WindowState) {
    var isHovered by remember { mutableStateOf(false) }

    val iconSize by animateDpAsState(if (isHovered) 160.dp else 128.dp)
    Box(
        contentAlignment = Alignment.Center,
        modifier = Modifier.aspectRatio(1f).padding(16.dp)
    ) {
        IconButton(
            modifier = Modifier.border(3.dp, MaterialTheme.colors.onSurface, CircleShape),
            onClick = {
                  windowState.updateWindow(WindowsEnum.CREATE_RECIPE)
            },
        ) {
            Icon(
                Icons.Default.Add,
                contentDescription = "Create Icon",
                modifier = Modifier.size(iconSize)
                    .onPointerEvent(PointerEventType.Enter) {
                        isHovered = true
                    }
                    .onPointerEvent(PointerEventType.Exit) {
                        isHovered = false
                    }
            )
        }
    }
}