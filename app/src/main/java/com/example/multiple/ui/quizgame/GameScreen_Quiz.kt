package com.example.multiple.ui.quizgame

import android.app.Activity
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import androidx.compose.foundation.background
import androidx.compose.material.*
import com.example.multiple.ui.theme.Orange
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.material.TextButton
import androidx.compose.material.AlertDialog
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import com.example.multiple.ui.theme.GrayBackground


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
                .background(color = Color(0xFFFF835C))
        ) {
            TopAppBar(
                title = { Text(text = "Quiz Game") },
                backgroundColor = GrayBackground,
                elevation = 0.dp,
                navigationIcon = {
                    IconButton(onClick = { navController.popBackStack() }) {
                        Icon(Icons.Filled.ArrowBack, contentDescription = "Back")
                    }
                },
                modifier = Modifier.fillMaxWidth()
            )
        }
        Spacer(modifier = Modifier.height(10.dp))
        Row() {
            Text(
                text = "Quiz $count out of 10",
                fontSize = 20.sp
            )
            Spacer(modifier = Modifier.width(150.dp))
            Text(
                text = "Score: $score",
                fontSize = 20.sp
            )
        }
        Spacer(modifier = Modifier.height(10.dp))
        Text(
            modifier = Modifier
                .fillMaxWidth()
                .padding(25.dp),
            text = question.question,
            fontSize = 30.sp,
            textAlign = TextAlign.Center
        )
        Spacer(modifier = Modifier.height(10.dp))
        Button(
            colors = ButtonDefaults.buttonColors(
                backgroundColor = Orange,
                contentColor = Color.White
            ),
            onClick = {
                quizGameViewModel.checkAnswer(choice[0])
                quizGameViewModel.getQuestion()
            },
            modifier = Modifier
                .fillMaxWidth()
                .padding(10.dp),
            shape = RoundedCornerShape(5.dp)
        ) {
            Text(
                text = choice[0],
                fontSize = 20.sp
            )
        }
        Button(
            colors = ButtonDefaults.buttonColors(
                backgroundColor = Orange,
                contentColor = Color.White
            ),
            onClick = {
                quizGameViewModel.checkAnswer(choice[1])
                quizGameViewModel.getQuestion()
            },
            modifier = Modifier
                .fillMaxWidth()
                .padding(10.dp),
            shape = RoundedCornerShape(5.dp)
        ) {
            Text(
                text = choice[1],
                fontSize = 20.sp
            )
        }
        Button(
            colors = ButtonDefaults.buttonColors(
                backgroundColor = Orange,
                contentColor = Color.White
            ),
            onClick = {
                quizGameViewModel.checkAnswer(choice[2])
                quizGameViewModel.getQuestion()
            },
            modifier = Modifier
                .fillMaxWidth()
                .padding(10.dp),
            shape = RoundedCornerShape(5.dp)
        ) {
            Text(
                text = choice[2],
                fontSize = 20.sp
            )
        }
        Button(
            colors = ButtonDefaults.buttonColors(
                backgroundColor = Orange,
                contentColor = Color.White
            ),
            onClick = {
                quizGameViewModel.checkAnswer(choice[3])
                quizGameViewModel.getQuestion()
            },
            modifier = Modifier
                .fillMaxWidth()
                .padding(10.dp),
            shape = RoundedCornerShape(5.dp)
        ) {
            Text(
                text = choice[3],
                fontSize = 20.sp
            )
        }
        Spacer(modifier = Modifier.height(100.dp))

        Column(
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier.fillMaxSize()) {
            Button(
                colors = ButtonDefaults.buttonColors(
                    backgroundColor = Orange,
                    contentColor = Color.White
                ),
                shape = RoundedCornerShape(5.dp),
                onClick = { quizGameViewModel.reset(true) }) {
                Text(
                    text = "Restart",
                    fontSize = 20.sp
                )
            }
            Spacer(modifier = Modifier.width(40.dp))
            Button(
                colors = ButtonDefaults.buttonColors(
                    backgroundColor = Orange,
                    contentColor = Color.White
                ),
                shape = RoundedCornerShape(5.dp),
                onClick = { navController.popBackStack() }) {
                Text(
                    text = "Select Game",
                    fontSize = 20.sp
                )
            }
        }
        if (gameUiState.currentQuizCount == 11) {
        FinalScoreDialog(
            score = gameUiState.score,
            onPlayAgain = { quizGameViewModel.reset(true) }
        )
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
                Text(text = "Exit!")
            }},
        confirmButton = {
            TextButton(onClick = onPlayAgain) {
                Text(text = "Restart")
            }
        }
    )
}