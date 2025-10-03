package com.example.aplicacindividircuentasv1

import android.R.attr.checked
import android.R.attr.onClick
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Slider
import androidx.compose.material3.SliderDefaults
import androidx.compose.material3.Switch
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.example.aplicacindividircuentasv1.ui.theme.AppTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp


class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            AppTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    dividirCuentas(
                        modifier = Modifier.padding(innerPadding)
                    )
                }
            }
        }
    }
}

@Composable
fun dividirCuentas(modifier: Modifier = Modifier) {
    var text by remember { mutableStateOf("")
    }
    var text2 by remember { mutableStateOf("")
    }
    var checked by remember { mutableStateOf(true)
    }
    var sliderPosition by remember { mutableFloatStateOf(0f) }
    var cantidadTotal by remember { mutableStateOf("") }
    var cantidadUno by remember { mutableStateOf("") }

    Column(modifier=Modifier.padding(16.dp).fillMaxWidth()){
        TextField(
            value = text,
            onValueChange = { text = it },
            label = { Text(stringResource(R.string.quantity)) },
            modifier = modifier.fillMaxWidth()
        )

        TextField(
            value = text2,
            onValueChange = { text2 = it },
            label = { Text((stringResource(R.string.person))) },
            modifier = Modifier.fillMaxWidth()
        )

        Row(
        Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment= Alignment.CenterVertically) {
            Text(
                text = stringResource(R.string.tip)
            )
            Switch(
                checked = checked,
                onCheckedChange = {
                    checked = it
                }
            )
        }
        Text(
            text=stringResource(R.string.Valoration)
        )

        Slider(
            value = sliderPosition,
            onValueChange = { sliderPosition = it },
            colors = SliderDefaults.colors(
                thumbColor = MaterialTheme.colorScheme.secondary,
                activeTrackColor = MaterialTheme.colorScheme.secondary,
                inactiveTrackColor = MaterialTheme.colorScheme.secondaryContainer,
            ),
            steps = 4,
            valueRange = 0f..5f
        )
        Text(text = sliderPosition.toString())

        Button(onClick = {
            var num:Double =text.toDouble()
            var num2:Int =text2.toInt()
            if(checked){
            when(sliderPosition.toInt()){
                1->num = num+num*0.05
                2->num = num+num*0.10
                3->num = num+num*0.15
                4->num = num+num*0.20
                5->num = num+num*0.25
            }
                cantidadTotal=num.toString()
                cantidadUno=(num/num2).toString()

            }else{
                cantidadTotal=num.toString()
                cantidadUno=(num/num2).toString()
            } },modifier = Modifier.fillMaxWidth()) {
            Row(
                Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.Center
            ) {
                Icon(
                    painter = painterResource(R.drawable.calculate),
                    contentDescription = "Calculate",
                    modifier = Modifier.size(16.dp)
                )

                Text(stringResource(R.string.Calculate))
            }
        }

        Text(
            text=stringResource(R.string.TotalCount)+": $cantidadTotal €"
        )
        Text(
            text=stringResource(R.string.Eachone)+": $cantidadUno €"
        )
    }

}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    AppTheme {
    }
}