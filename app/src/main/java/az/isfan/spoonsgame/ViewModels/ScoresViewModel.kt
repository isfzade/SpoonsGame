package az.isfan.spoonsgame.ViewModels

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import az.isfan.spoonsgame.Data.Db.Repos.GameDbRepoInterface
import az.isfan.spoonsgame.Data.Models.GameData
import az.isfan.spoonsgame.General.Cavab
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

@HiltViewModel
class ScoresViewModel @Inject constructor(
    private val repo: GameDbRepoInterface
): ViewModel() {
    private val TAG = "isf_ScoresViewModel"

    private val _games = MutableStateFlow<Cavab<List<GameData>>>(Cavab.StandBy)
    val games = _games.asStateFlow()


    init {
        Log.i(TAG, "init: ")
        loadGames()
    }

    private fun loadGames() {
        viewModelScope.launch {
            _games.update {
                Cavab.Loading
            }
            val loadedGames = withContext(Dispatchers.IO) {
                repo.getAllFinishedGames()
            }

            _games.update {
                Cavab.Success(loadedGames)
            }
        }
    }
}