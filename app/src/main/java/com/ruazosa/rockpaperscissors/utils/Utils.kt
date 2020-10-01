package com.ruazosa.rockpaperscissors.utils

import com.ruazosa.rockpaperscissors.R
import kotlinx.android.synthetic.main.fragment_game.view.*

object Utils {
    val buttonMapping = mapOf(R.id.rockCardView to 1, R.id.paperCardView to 2, R.id.scissorsCardView to 3)
    val choiceMapping = mapOf(1 to "Rock", 2 to "Paper", 3 to "Scissors")
    val outcome = mapOf("player_wins" to 1, "draw" to 0, "cpu_wins" to -1)
    var firstPlay = true

    fun determineOutcome(buttonId : Int, cpuChoice : Int): Int? {
        val playerChoice = buttonMapping[buttonId]
        when((playerChoice?.minus(cpuChoice))){
            0 -> { return outcome["draw"] }
            1 -> { return outcome["player_wins"] }
            -1 -> { return outcome["cpu_wins"] }
            2 -> { return outcome["cpu_wins"] }
            else -> { return outcome["player_wins"] }
        }
    }

}