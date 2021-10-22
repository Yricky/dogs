package com.yricky.dogs.ui.activity

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.yricky.dogs.DogApp
import com.yricky.dogs.ui.activity.ui.theme.DogsTheme

class DogEditActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            DogsTheme {
                // A surface container using the 'background' color from the theme
                Surface(color = MaterialTheme.colors.background) {
                    Content()
                }
            }
        }
    }
}

@Composable
fun Content() {
    Column(Modifier.fillMaxWidth()) {
        Edit(hint = "Name", text = "${DogApp.app.currEditDog?.name}", onValueChange = {
            DogApp.app.currEditDog?.name = it
        })

        Edit(hint = "Description", text = "${DogApp.app.currEditDog?.description}", onValueChange = {
            DogApp.app.currEditDog?.description = it
        })

    }

}
@Composable
fun Edit(hint:String, text:String, onValueChange:(String)->Unit){
    var et by remember { mutableStateOf(text) }
    OutlinedTextField(
        value = et,
        onValueChange = {
            et = it
            onValueChange.invoke(it)
        },
        label = { Text(text = hint) },
        modifier = Modifier.fillMaxWidth()
    )
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    DogsTheme {
        Content()
    }
}