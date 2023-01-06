package ir.adicom.profile

import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun ProfileScreen() {
    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center,
    ) {
        Text("Profile Screen", fontSize = 20.sp)
        Button(onClick = { /*TODO*/ }) {
            Text("Simple Button")
        }
        Button(
            onClick = {},
            colors = ButtonDefaults.buttonColors(backgroundColor = Color.DarkGray)
        ) {
            Text(text = "Button with gray background", color = Color.White)
        }
        Button(onClick = {
            Log.e("TAG", "clicked".uppercase());
        }) {
            Image(
                painterResource(id = R.drawable.ic_shopping_cart),
                contentDescription = "Cart button icon",
                modifier = Modifier.size(20.dp)
            )

            Text(text = "Add to cart", Modifier.padding(start = 10.dp))
        }

        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(100.dp)
                .background(Color.Red)
        ) {
            Row {
                Box(
                    modifier = Modifier
                        .fillMaxHeight()
                        .background(Color.Green)
                        .weight(1F)
                ) {
                    Text("ICON")
                }
                Box(
                    modifier = Modifier
                        .background(Color.Blue)
                        .weight(3F)
                ) {
                    Text("1")
                }
                Box(
                    modifier = Modifier
                        .background(Color.Yellow)
                        .weight(1F)
                ) {
                    Text("1")
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun ProfilePrev() {
    ProfileScreen()
}