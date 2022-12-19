package ir.adicom.myapplication.navigation

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.layout.Column
import androidx.compose.material.BottomNavigation
import androidx.compose.material.BottomNavigationItem
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.sp

@Composable
fun BottomNavigationBar(
    items: List<Destination>,
    currentScreenRoute: String?,
    onItemClick: (Destination) -> Unit
) {
    BottomNavigation {
        items.forEach {
            val selected = it.route == currentScreenRoute
            BottomNavigationItem(selected = selected, onClick = { onItemClick(it) }, icon = {
                Column(horizontalAlignment = Alignment.CenterHorizontally) {
                    Icon(imageVector = it.icon, contentDescription = it.route)
                    AnimatedVisibility(visible = selected) {
                        Text(
                            text = it.route.uppercase(),
                            textAlign = TextAlign.Center,
                            fontSize = 10.sp
                        )
                    }
                }
            })
        }
    }
}