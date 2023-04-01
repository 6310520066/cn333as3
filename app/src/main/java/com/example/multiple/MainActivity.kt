package com.example.multiple

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.*
import androidx.compose.material.Button
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.navigation.NavController
import androidx.navigation.compose.NavHost
import androidx.compose.ui.unit.*
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.multiple.ui.numberguessinggame.NumberGuessingGame
import com.example.multiple.ui.quizgame.GameScreen_Quiz
import com.example.multiple.ui.quizgame.QuizGameViewModel
import com.example.multiple.ui.theme.MultipleTheme
import com.example.multiple.ui.tictactoegame.GameScreen_TicTacToe
import com.example.multiple.ui.tictactoegame.TicTacToeGameViewModel



class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MultipleTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background
                ) {
                    val navController = rememberNavController()
                    NavHost(navController, startDestination = Screen.Home.name) {
                        composable(Screen.Home.name) {
                            HomeScreen(navController = navController)
                        }
                        composable("Number Guessing Game") {
                            NumberGuessingGame(name = "Number Guessing Game", navController = navController)
                        }
                        composable("Quiz Game") {
                            GameScreen_Quiz(name = "Quiz Game", quizGameViewModel = QuizGameViewModel(), navController = navController)
                        }
                        composable("Tic Tac Toe Game") {
                            GameScreen_TicTacToe(name = "Tic Tac Toe Game", viewModel = TicTacToeGameViewModel(), navController = navController)
                        }
                    }
                }
            }
        }
    }
}

enum class Screen {
    Home,
}


@Composable
fun HomeScreen(navController: NavController, modifier: Modifier = Modifier) {
    Column(
        Modifier
            .fillMaxWidth()
            .padding(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Spacer(modifier = Modifier.height(50.dp))
        Button(
            onClick = { navController.navigate("Number Guessing Game") },
            modifier = modifier
                .fillMaxWidth()
                .padding(20.dp)
        ) {
            Text(
                "Number Guessing Game",
                fontSize = 20.sp
            )
        }
        Button(
            onClick = { navController.navigate("Quiz Game") },
            modifier = modifier
                .fillMaxWidth()
                .padding(20.dp)
        ) {
            Text(
                "Quiz Game",
                fontSize = 20.sp
            )
        }
        Button(onClick = { navController.navigate("Tic Tac Toe Game") },
            modifier = modifier
                .fillMaxWidth()
                .padding(20.dp)
        ) {
            Text(
                "Tic Tac Toe Game",
                fontSize = 20.sp)
        }
    }
}