package com.example.multiple.ui.numberguessinggame

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.material.TopAppBar
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import kotlin.random.Random

@Composable
fun NumberGuessingGame(
    name: String,
    navController: NavController
) {
    var targetNumber by remember { mutableStateOf(generateRandomNumber()) }
    var guess by remember { mutableStateOf("") }
    var numGuesses by remember { mutableStateOf(0) }
    var hint by remember { mutableStateOf("") }
    var gameOver by remember { mutableStateOf(false) }

    Box(modifier = Modifier.fillMaxSize()) {
        // Top app bar
        TopAppBar(
            title = { Text(text = "Number Guessing Game") },
            modifier = Modifier.align(Alignment.TopCenter)
        )

        Text(
            text = "Try to guess the number I'm thinking of from 1 - 1000!",
            fontSize = 20.sp,
            fontWeight = FontWeight.Bold,
            textAlign = TextAlign.Center,
            modifier = Modifier
                .padding(10.dp)
                .paddingFromBaseline(top = 80.dp)
                .align(Alignment.TopCenter)
        )

        Column(
            modifier = Modifier.fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            Text(
                text = "Your Guess: $guess",
                fontSize = 18.sp
            )
            Spacer(modifier = Modifier.height(16.dp))
            if (hint.isNotEmpty()) {
                Text(
                    text = hint,
                    fontSize = 16.sp,
                )
                Spacer(modifier = Modifier.height(16.dp))
            }
            if (gameOver) {
                Text(
                    text = "Congratulations! \n You guessed correctly  in $numGuesses times.",
                    fontSize = 20.sp,
                    textAlign = TextAlign.Center,
                    modifier = Modifier.padding(10.dp)
                )
            } else {
                TextField(
                    value = guess,
                    onValueChange = { guess = it },
                    modifier = Modifier.width(200.dp),
                    maxLines = 1,
                    keyboardOptions = KeyboardOptions.Default.copy(
                        imeAction = ImeAction.Done,
                        keyboardType = KeyboardType.Number
                    ),
                    keyboardActions = KeyboardActions(
                        onDone = {
                            if (guess.isNotEmpty()) {
                                val guessNumber = guess.toInt()
                                if (guessNumber > 1000) {
                                    hint = "Hint: The number is between 1 and 1000."
                                } else if (guessNumber < 1) {
                                    hint = "Hint: The number is between 1 and 1000."
                                } else {
                                    numGuesses++
                                    if (guessNumber == targetNumber) {
                                        hint = ""
                                        gameOver = true
                                    } else if (guessNumber < targetNumber) {
                                        hint = "Hint: It's higher!"
                                    } else {
                                        hint = "Hint: It's lower!"
                                    }
                                }
                            } else {
                                hint = ""
                            }
                            if (!gameOver) {
                                guess = ""
                            }
                        }
                    )
                )
            }
        }
        Button(
            modifier = Modifier
                .align(Alignment.BottomCenter)
                .padding(bottom = 40.dp),
            onClick = {
                guess = ""
                numGuesses = 0
                hint = ""
                gameOver = false
                targetNumber = generateRandomNumber()
            }
        ) {
            Text(text = "PLAY AGAIN")
        }

    }
}

fun generateRandomNumber(): Int {
    val random = Random(System.currentTimeMillis())
    return random.nextInt(1, 1001)
}