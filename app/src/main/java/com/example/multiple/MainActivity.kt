package com.example.multiple

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.navigation.NavController
import androidx.navigation.compose.NavHost
import androidx.compose.ui.unit.*
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.multiple.ui.numberguessinggame.NumberGuessingGame
import com.example.multiple.ui.quizgame.GameScreen_Quiz
import com.example.multiple.ui.quizgame.QuizGameViewModel
import com.example.multiple.ui.theme.MultipleTheme
import com.example.multiple.ui.theme.Orange
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
        Spacer(modifier = Modifier.height(40.dp))
        Text("Multiple Games",  fontSize = 35.sp, fontWeight = FontWeight.ExtraBold)
        Spacer(modifier = Modifier.height(10.dp))
        Text("An integer is randomized within a known range. The player will try to guess the number. " +
                "If the guess is incorrect, the game tells the player whether the guess was too high or too low.",
            fontSize = 20.sp,
            modifier = Modifier.padding(horizontal = 16.dp))
        Spacer(modifier = Modifier.height(10.dp))
        Button(
            colors = ButtonDefaults.buttonColors(
                backgroundColor = Orange,
                contentColor = Color.White
            ),
            shape = RoundedCornerShape(5.dp),
            onClick = { navController.navigate("Number Guessing Game") },
            modifier = modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp)
        ) {
            Text(
                "Number Guessing Game",
                fontSize = 20.sp
            )
        }
        Spacer(modifier = Modifier.height(10.dp))
        Text("Quiz is a type of game in which players are asked questions about different " +
                "topics and they have to get as many correct answers as possible.",
            fontSize = 20.sp
            ,modifier = Modifier.padding(horizontal = 16.dp))
        Spacer(modifier = Modifier.height(10.dp))
        Button(
            colors = ButtonDefaults.buttonColors(
                backgroundColor = Orange,
                contentColor = Color.White
            ),
            shape = RoundedCornerShape(5.dp),
            onClick = { navController.navigate("Quiz Game") },
            modifier = modifier
                .fillMaxWidth()
                .padding(16.dp)
        ) {
            Text(
                "Quiz Game",
                fontSize = 20.sp
            )
        }
        Spacer(modifier = Modifier.height(10.dp))
        Text("A game in which two players seek in alternate turns " +
                "to complete a row, a column, or a diagonal with either three O's or three " +
                "X's drawn in the spaces of a grid of nine squares.",
            fontSize = 20.sp,
            modifier = Modifier.padding(horizontal = 16.dp))
        Spacer(modifier = Modifier.height(10.dp))
        Button(
            colors = ButtonDefaults.buttonColors(
                backgroundColor = Orange,
                contentColor = Color.White
            ),
            shape = RoundedCornerShape(5.dp),
            onClick = { navController.navigate("Tic Tac Toe Game") },
            modifier = modifier
                .fillMaxWidth()
                .padding(16.dp)
        ) {
            Text(
                "Tic Tac Toe Game",
                fontSize = 20.sp)
        }
    }
}