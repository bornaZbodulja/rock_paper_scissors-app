package com.ruazosa.rockpaperscissors.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.Navigation
import com.ruazosa.rockpaperscissors.R
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.fragment_play_again.*

/**
 * A simple [Fragment] subclass.
 * Use the [PlayAgainFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class PlayAgainFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_play_again, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val result = arguments?.getInt("gameResult")

        if(result==1){ resultTextView.text = resources.getString(R.string.game_won) }
        else{ resultTextView.text = resources.getString(R.string.game_lost) }

        playAgainButton.setOnClickListener {
            val action = PlayAgainFragmentDirections.playAgainDirection()
            fragment.view?.let { Navigation.findNavController(it).navigate(action) }
        }
    }

}