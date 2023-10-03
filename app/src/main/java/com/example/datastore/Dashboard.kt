package com.example.datastore

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController

@Composable
fun Dashboard(navController: NavController, viewModel: MainViewModel) {
    val detailStorage = DetailStorage(LocalContext.current)
    val result by detailStorage.getInfo().collectAsState(initial = StudentDetail())

    Column(
        modifier = Modifier
            .fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        result.let { studentDetail ->
            Text("Hi, ${studentDetail.name} !")
            Text("You are ${studentDetail.age} years old right ?")
        }

        Spacer(modifier = Modifier.size(30.dp))

        Button(
            onClick = {
                navController.navigate("form_page")
                viewModel.loggedIn = false
            }
        ) {
            Text(text = "Delete Details")
        }

    }
}
