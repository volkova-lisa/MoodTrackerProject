package com.example.moodtrackerproject.ui.home

import android.os.Bundle
import android.view.*
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.example.moodtrackerproject.MainActivity
import com.example.moodtrackerproject.R
import com.example.moodtrackerproject.data.DataBaseRepository
import com.example.moodtrackerproject.databinding.FragmentHomeBinding
import com.example.moodtrackerproject.ui.mood.list.HomeNotesAdapter
import com.example.moodtrackerproject.ui.mood.list.MoodAdapter
import com.example.moodtrackerproject.utils.PreferenceManager

class HomeFragment : Fragment() {

    private lateinit var binding: FragmentHomeBinding
    private val todayMoodsAdapter = MoodAdapter()
    private val todayNotesAdapter = HomeNotesAdapter()

    private val viewModel: HomeViewModel by lazy {
        ViewModelProvider(this).get(HomeViewModel::class.java)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentHomeBinding.inflate(layoutInflater, container, false)
        viewModel.fetchLists()
        val toolbar = binding.toolbar
        (activity as AppCompatActivity?)!!.setSupportActionBar(toolbar)
        setHasOptionsMenu(true)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.emojiList.adapter = todayMoodsAdapter
        binding.notesList.adapter = todayNotesAdapter
        viewModel.liveData.observe(viewLifecycleOwner, {
            render(it)
        })
    }

    private fun render(state: HomeViewState) {
        state.action?.let { handleAction(it) }
        todayMoodsAdapter.setList(state.listOfMoods)
        todayNotesAdapter.setList(state.listOfNotes)
        binding.angerInclude.angerBar.progress = DataBaseRepository.angerResults
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.home_menu, menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.logOut -> {
                viewModel.logOut()
                PreferenceManager.setInitUser(false)
            }
        }
        return super.onOptionsItemSelected(item)
    }

    private fun handleAction(homeAction: HomeAction) {
        when (homeAction) {
            is HomeAction.LogOut -> {
                (requireActivity() as MainActivity).router.openWelcome()
            }
        }
    }
}
