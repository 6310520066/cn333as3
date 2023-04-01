package com.example.multiple.ui.quizgame

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel

@Composable
fun GameLayout(
    currentQuiz: List<String>,
    modifier: Modifier = Modifier,
    quizGameViewModel: QuizGameViewModel = viewModel(),

    ) {
    val random = remember(quizGameViewModel.uiState.value.currentQuiz) {
        quizGameViewModel.uiState.value.currentQuiz.slice(1..4).shuffled()
    }
    Column(
        verticalArrangement = Arrangement.spacedBy(24.dp)
    ) {
        Text(
            text = (currentQuiz[0]),
            fontSize = 25.sp,
            textAlign = TextAlign.Center,
            modifier = modifier.align(Alignment.CenterHorizontally).padding(2.dp)
        )
        Button(

            modifier = modifier
                .fillMaxWidth()
                .padding(start = 10.dp),
            onClick = {
                quizGameViewModel.updateUserGuess(random[0])
                quizGameViewModel.checkUserGuess() },
            shape = RoundedCornerShape(16.dp)
        ) {
            Text(text = random[0],
                fontSize = 18.sp,)
        }
        Button(
            modifier = modifier
                .fillMaxWidth()
                .padding(start = 10.dp),
            onClick = {
                quizGameViewModel.updateUserGuess(random[1])
                quizGameViewModel.checkUserGuess() },
            shape = RoundedCornerShape(16.dp)
        ) {
            Text(text = random[1],
                fontSize = 18.sp,)
        }
        Button(
            modifier = modifier
                .fillMaxWidth()
                .padding(start = 10.dp),
            onClick = {
                quizGameViewModel.updateUserGuess(random[2])
                quizGameViewModel.checkUserGuess() },
            shape = RoundedCornerShape(16.dp)
        ) {
            Text(text = (random[2]),
                fontSize = 18.sp,)
        }
        Button(
            modifier = modifier
                .fillMaxWidth()
                .padding(start = 10.dp),
            onClick = {
                quizGameViewModel.updateUserGuess(random[3])
                quizGameViewModel.checkUserGuess() },
            shape = RoundedCornerShape(16.dp)
        ) {
            Text(text = (random[3]),
                fontSize = 18.sp,)
        }
    }
}