package com.example.multiple.ui.quizgame


import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import com.example.multiple.data.*

class QuizGameViewModel : ViewModel() {
    var score = 0
    var currentQuizCount = 1
    var isGameOver = false
    var question = questions.shuffled()

    private val _uiState = MutableStateFlow(QuizGameUiState(
        currentQuiz = question[currentQuizCount],
        choice = question[currentQuizCount].choice.shuffled(),
        score = 0,
        currentQuizCount = 1,
        isGameOver = isGameOver,

        ))


    val uiState: StateFlow<QuizGameUiState> = _uiState.asStateFlow()

    fun getQuestion() {
        if (currentQuizCount <= 10) {
            currentQuizCount += 1
        }
        val currentQuestion = question[currentQuizCount]
        val choice = currentQuestion.choice.shuffled()
        val newState = QuizGameUiState(currentQuiz = currentQuestion, choice = choice, score = score, currentQuizCount = currentQuizCount, isGameOver = false)
        _uiState.value = newState
    }

    fun checkAnswer(input: String) {
        if (input == question[currentQuizCount].answer) {
            score += 1
        }
    }

    fun reset(status: Boolean) {
        score = 0
        currentQuizCount = 1
        isGameOver = false
        question = questions.shuffled()
        getQuestion()
    }
}