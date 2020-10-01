package com.ruazosa.rockpaperscissors.view

import android.app.AlertDialog
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.navigation.Navigation
import com.ruazosa.rockpaperscissors.R
import com.ruazosa.rockpaperscissors.utils.Utils
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.fragment_game.*
import kotlinx.android.synthetic.main.gamestart_popup.*
import kotlinx.android.synthetic.main.gamestart_popup.view.*

/**
 * A simple [Fragment] subclass.
 * Use the [GameFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class GameFragment : Fragment() {

    private var gameStarted = false
    private var playerScore: Int = 0
    private var cpuScore: Int = 0
    //private val dialogView = LayoutInflater.from(context).inflate(R.layout.gamestart_popup, null)
    private lateinit var alertDialogBuilder: AlertDialog.Builder

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_game, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        if (Utils.firstPlay){
            val dialogView = LayoutInflater.from(context).inflate(R.layout.gamestart_popup, null)
            alertDialogBuilder = AlertDialog.Builder(context)
            alertDialogBuilder.apply {
                setCancelable(false)
                setView(dialogView)
            }

            val alertDialog = alertDialogBuilder.create()
            dialogView.gotItButton.setOnClickListener { alertDialog.dismiss() }
            alertDialog.show()
            Utils.firstPlay = false
        }

        rockCardView.setOnClickListener { roundHandler(it.id) }
        paperCardView.setOnClickListener { roundHandler(it.id) }
        scissorsCardView.setOnClickListener { roundHandler(it.id) }
    }


    private fun roundHandler(buttonId: Int){
        val random: Int = (1..3).shuffled().first()
        val playerChoice =Utils.buttonMapping[buttonId]
        Log.d("RANDOM_TAG", random.toString())
        val res = Utils.determineOutcome(buttonId, random)

        when(res){
            0 -> {
                roundResultText.text = "It's a draw"
            }
            1 -> {
                playerScore++
                if(Utils.buttonMapping[buttonId] == 3){
                    roundResultText.text = Utils.choiceMapping[3] + " beat " + Utils.choiceMapping[random]
                }else{ roundResultText.text = Utils.choiceMapping[playerChoice] + " beats " + Utils.choiceMapping[random] }
            }
            -1 -> {
                cpuScore++
                if(random == 3){
                    roundResultText.text = Utils.choiceMapping[3] + " beat " + Utils.choiceMapping[playerChoice]
                }else{ roundResultText.text = Utils.choiceMapping[random] + " beats " + Utils.choiceMapping[playerChoice] }
            }
        }

        if(playerScore == 5 || cpuScore == 5){
            val result = if(playerScore==5) 1 else -1
            val action = GameFragmentDirections.gameOverDirection(result)
            fragment.view?.let { Navigation.findNavController(it).navigate(action) }
        }

        if (!gameStarted){
            textDescription.visibility = View.INVISIBLE
            scoreText.visibility = View.VISIBLE
            roundResultText.visibility = View.VISIBLE
            gameStarted=true
        }

        scoreText.text = "Player    $playerScore : $cpuScore        Cpu"
    }

}