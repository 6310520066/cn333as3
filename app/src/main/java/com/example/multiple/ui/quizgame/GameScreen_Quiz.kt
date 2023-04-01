package com.example.multiple.ui.quizgame

import android.app.Activity
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.AlertDialog
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.material.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.example.multiple.R
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import com.example.multiple.data.questions

@Composable
fun GameScreen_Quiz(
    name: String,
    modifier: Modifier = Modifier,
    quizGameViewModel: QuizGameViewModel,
    navController: NavController
) {
    val gameUiState by quizGameViewModel.uiState.collectAsState()
    val question = gameUiState.currentQuiz
    val choice = gameUiState.choice
    val score = gameUiState.score
    val count = gameUiState.currentQuizCount


    Column(
        Modifier
            .fillMaxSize()
            .padding(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Box(
            Modifier
                .fillMaxWidth()
                .background(color = Color(0xFF4A148C))
        ) {
            Text(
                text = "$name",
                fontSize = 20.sp,
                color = Color.White,
                modifier = Modifier.padding(12.dp)
            )
        }
        Spacer(modifier = Modifier.height(15.dp))
        Row() {
            Text(
                text = "$count out of 10",
                fontSize = 20.sp
            )
            Spacer(modifier = Modifier.width(150.dp))
            Text(
                text = "Score: $score",
                fontSize = 20.sp
            )
        }
        Spacer(modifier = Modifier.height(100.dp))
        Text(
            modifier = Modifier
                .fillMaxWidth()
                .padding(25.dp),
            text = question.question,
            fontSize = 20.sp
        )
        Spacer(modifier = Modifier.height(100.dp))
        Button(
            onClick = {
                quizGameViewModel.checkAnswer(choice[0])
                quizGameViewModel.getQuestion()
            },
            modifier = Modifier
                .fillMaxWidth()
                .padding(10.dp)
        ) {
            Text(
                text = choice[0],
                fontSize = 20.sp
            )
        }
        Button(
            onClick = {
                quizGameViewModel.checkAnswer(choice[1])
                quizGameViewModel.getQuestion()
            },
            modifier = Modifier
                .fillMaxWidth()
                .padding(10.dp)
        ) {
            Text(
                text = choice[1],
                fontSize = 20.sp
            )
        }
        Button(
            onClick = {
                quizGameViewModel.checkAnswer(choice[2])
                quizGameViewModel.getQuestion()
            },
            modifier = Modifier
                .fillMaxWidth()
                .padding(10.dp)
        ) {
            Text(
                text = choice[2],
                fontSize = 20.sp
            )
        }
        Button(
            onClick = {
                quizGameViewModel.checkAnswer(choice[3])
                quizGameViewModel.getQuestion()
            },
            modifier = Modifier
                .fillMaxWidth()
                .padding(10.dp)
        ) {
            Text(
                text = choice[3],
                fontSize = 20.sp
            )
        }
        Spacer(modifier = Modifier.height(45.dp))

        Row {
            Button(onClick = { quizGameViewModel.reset(true) }) {
                Text(
                    text = "RESET",
                    fontSize = 20.sp
                )
            }
            Spacer(modifier = Modifier.width(30.dp))
            Button(onClick = { navController.popBackStack() }) {
                Text(
                    text = "Go Back",
                    fontSize = 20.sp
                )
            }

            if (gameUiState.currentQuizCount == 11) {
                FinalScoreDialog(
                    score = gameUiState.score,
                    onPlayAgain = { quizGameViewModel.reset(true) }
                )
            }

        }
    }

}


@Composable
fun FinalScoreDialog(
    score: Int,
    onPlayAgain: () -> Unit,
) {
    val activity = (LocalContext.current as Activity)

    AlertDialog(
        onDismissRequest = {},
        title = { Text(text = "Congratulations!") },
        text = { Text(text = "Your Score: $score") },
        dismissButton = {
            TextButton(onClick = {activity.finish()}) {
                Text(text = "Exit")
            }},
        confirmButton = {
            TextButton(onClick = onPlayAgain) {
                Text(text = "Reset")
            }
        }
    )
}