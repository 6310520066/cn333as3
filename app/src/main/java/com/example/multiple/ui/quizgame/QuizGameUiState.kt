package com.example.multiple.ui.quizgame

import com.example.multiple.data.*

data class QuizGameUiState(
    val currentQuiz: Question,
    val choice: List<String>,
    val currentQuizCount: Int,
    val score: Int,
    val isGameOver: Boolean,
)