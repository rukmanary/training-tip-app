package com.rkmn.tipapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.CornerSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.rkmn.tipapp.components.TextInput
import com.rkmn.tipapp.ui.theme.TipAppTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            App {
                Column() {
                    TopHeader()
                    MainContent()
                }
            }
        }
    }
}

@Preview
@Composable
fun TopHeader(totalPerPerson: Double = 0.0) {
    Surface(
        modifier = Modifier
            .fillMaxWidth()
            .padding(bottom = 16.dp)
            .height(150.dp)
            .clip(
                shape =
                    CircleShape.copy(all = CornerSize(12.dp))
            ), color = Color(0xFFE9D7F7)
    ) {
        Column(
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            val total = "%.2f".format(totalPerPerson)
            Text(
                text = "Total Per Person",
                style = TextStyle(
                    fontSize = 20.sp
                )
            )
            Text(
                text = "$$total",
                style = TextStyle(
                    fontSize = 32.sp
                )
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun MainContent() {
    val totalBill = remember { mutableStateOf("") }
    val isTotalValid = remember(totalBill.value) {
        totalBill.value.trim().isNotEmpty()
    }
    val keyboardController = LocalSoftwareKeyboardController.current
    Surface(
        modifier = Modifier
            .padding(2.dp)
            .fillMaxSize(),
        shape = RoundedCornerShape(8.dp),
        border = BorderStroke(width = 1.dp, color = Color.LightGray)
    ) {
        Column() {
            TextInput(
                textValue = totalBill,
                labelId = "Enter Bill",
                enabled = true,
                singleLine = true,
                onAction = KeyboardActions {
                    if (!isTotalValid) return@KeyboardActions
                    //TODO
                    keyboardController?.hide()
                }
            )
        }
    }
}

@Composable
fun App(content: @Composable () -> Unit) {
    TipAppTheme {
        Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
            Surface(
                color = MaterialTheme.colorScheme.background,
                modifier = Modifier
                    .padding(innerPadding)
                    .padding(16.dp)
            ) {
                content()
            }
        }
    }
}

//@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    TipAppTheme {
        App {
            Text(text = "Hello World")
        }
    }
}