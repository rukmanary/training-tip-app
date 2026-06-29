package com.rkmn.tipapp

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.CornerSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Remove
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Slider
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
import com.rkmn.tipapp.components.RoundIconButton
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
    BillForm() { billAmt ->
        Log.d("AMT", "MainContent: $billAmt")
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

@Composable
fun BillForm(
    modifier: Modifier = Modifier,
    onValChange: (String) -> Unit = {}
) {
    val totalBill = remember {
        mutableStateOf("")
    }
    val isTotalValid = remember(totalBill.value) {
        totalBill.value.trim().isNotEmpty()
    }
    val sliderPosistion = remember {
        mutableStateOf(0f)
    }

    val keyboardController = LocalSoftwareKeyboardController.current

    Surface(
        modifier = Modifier
            .padding(2.dp)
            .fillMaxSize(),
        shape = RoundedCornerShape(8.dp),
        border = BorderStroke(width = 1.dp, color = Color.LightGray)
    ) {
        Column(
            modifier = modifier.padding(6.dp),
            verticalArrangement = Arrangement.Top,
            horizontalAlignment = Alignment.Start
        ) {
            TextInput(
                textValue = totalBill,
                labelId = "Enter Bill",
                enabled = true,
                singleLine = true,
                onAction = KeyboardActions {
                    if (!isTotalValid) return@KeyboardActions
                    onValChange(totalBill.value.trim())
                    keyboardController?.hide()
                }
            )

            if (isTotalValid) {
                Row(
                    modifier = modifier.padding(3.dp),
                    horizontalArrangement = Arrangement.Start
                ) {
                    Text(
                        text = "Split",
                        modifier = modifier.align(
                            alignment = Alignment.CenterVertically
                        )
                    )
                    Spacer(modifier = modifier.width(120.dp))
                    Row(
                        modifier = modifier.padding(horizontal = 3.dp),
                        horizontalArrangement = Arrangement.End
                    ) {
                        RoundIconButton(
                            imageVector = Icons.Default.Remove,
                            onClick = { /*TODO*/ }
                        )
                        Text(
                            text = "3",
                            modifier = modifier
                                .align(Alignment.CenterVertically)
                                .padding(
                                    start = 9.dp, end = 9.dp
                                )
                        )
                        RoundIconButton(
                            imageVector = Icons.Default.Add,
                            onClick = { /*TODO*/ }
                        )
                    }
                }

                // Tip Row
                Row(
                    modifier = modifier.padding(horizontal = 3.dp, vertical = 12.dp)
                ) {
                    Text(
                        text = "Tip",
                        modifier = modifier.align(alignment = Alignment.CenterVertically)
                    )
                    Spacer(modifier = modifier.width(200.dp))
                    Text(
                        text = "$10",
                        modifier = modifier.align(alignment = Alignment.CenterVertically)
                    )
                }

                Column(
                    verticalArrangement = Arrangement.Center,
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Text(text = "$10")
                    Spacer(modifier = modifier.height(14.dp))

                    //Slider
                    Slider(
                        value = sliderPosistion.value,
                        onValueChange = { newVal ->
                            sliderPosistion.value = newVal
                            Log.d("AMT", "BillForm: $newVal")
                        },
                        modifier = modifier.padding(start = 16.dp, end = 16.dp),
                        steps = 5,
                        onValueChangeFinished = {
                            Log.d("AMT", "BillForm: Finished")
                        }
                    )
                }
            } else {
                Box() {}
            }
        }
    }
}