package org.katant.recipe.presentation.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.List
import androidx.compose.material.icons.outlined.ShoppingCart
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import org.katant.recipe.enums.WindowsEnum
import org.katant.recipe.store.CreateRecipeState
import org.katant.recipe.store.WindowState

@Composable
fun MainHeader(
    windowState: WindowState,
    createRecipeState: CreateRecipeState? = null
) {
    Surface(
        color = MaterialTheme.colors.surface,
        modifier = Modifier.padding(8.dp)
    ) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.Center,
        ) {
            Box(modifier = Modifier.clickable {
                createRecipeState?.reset()
                windowState.updateWindow(WindowsEnum.CART)
            }) {
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Icon(
                        Icons.Outlined.ShoppingCart,
                        contentDescription = "Cart Icon",
                        modifier = Modifier.padding(end = 8.dp)
                    )
                    Text(text = "Cart", style = MaterialTheme.typography.h5)
                }
            }

            Spacer(modifier = Modifier.width(32.dp))

            Box(modifier = Modifier.clickable {
                createRecipeState?.reset()
                windowState.updateWindow(WindowsEnum.RECIPES_LIST)
            }) {
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Icon(
                        Icons.Outlined.List,
                        contentDescription = "Recipes Icon",
                        modifier = Modifier.padding(end = 8.dp)
                    )
                    Text(text = "Recipes", style = MaterialTheme.typography.h5)
                }
            }
        }
    }
}