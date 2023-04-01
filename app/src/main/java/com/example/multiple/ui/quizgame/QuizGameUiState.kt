package com.example.multiple.ui.quizgame

data class QuizGameUiState(
    val currentQuiz: List<String> = listOf(),
    val currentQuizCount: Int = 1,
    val score: Int = 0,
    val isGuessedWordWrong: Boolean = false,
    val isGameOver: Boolean = false
)