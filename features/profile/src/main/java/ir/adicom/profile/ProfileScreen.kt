package ir.adicom.profile

import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.Text
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
                contentDescription ="Cart button icon",
                modifier = Modifier.size(20.dp))

            Text(text = "Add to cart",Modifier.padding(start = 10.dp))
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun ProfilePrev() {
    ProfileScreen()
}