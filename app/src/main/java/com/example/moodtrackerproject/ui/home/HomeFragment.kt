package com.example.moodtrackerproject.ui.home

import android.os.Bundle
import android.view.*
import androidx.appcompat.app.AppCompatActivity
import com.example.moodtrackerproject.MainActivity
import com.example.moodtrackerproject.R
import com.example.moodtrackerproject.data.DataBaseRepository
import com.example.moodtrackerproject.databinding.FragmentHomeBinding
import com.example.moodtrackerproject.ui.BaseFragment
import com.example.moodtrackerproject.ui.home.HomeProps.HomeAction
import com.example.moodtrackerproject.ui.mood.list.MoodsListAdapter

class HomeFragment : BaseFragment<HomeViewModel, FragmentHomeBinding, HomeProps>(
    HomeViewModel::class.java
) {

    private val moodsAdapter = MoodsListAdapter()
    private val notesAdapter = HomeNotesAdapter()

    private lateinit var props: HomeProps

    override fun getFragmentBinding(
        inflater: LayoutInflater, container: ViewGroup?
    ): FragmentHomeBinding = FragmentHomeBinding.inflate(inflater, container, false)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        (activity as? AppCompatActivity)?.setSupportActionBar(binding?.toolbar)
        setHasOptionsMenu(true)
        binding?.emojiList?.adapter = moodsAdapter
        binding?.notesList?.adapter = notesAdapter
    }

    override fun onResume() {
        super.onResume()
        if (::props.isInitialized) {
            props.fetchListOfMoods()
            props.fetchListOfNotes()
        }
    }

    override fun render(props: HomeProps) {
        this.props = props
        props.action?.let { handleAction(it) }
        notesAdapter.submitList(props.listOfNotesToday)
        moodsAdapter.submitList(props.listOfMoodsToday)
        binding?.run {
            angerJoy.angerBar.progress = DataBaseRepository.stressPoints
            sadHappy.angerBar.progress = DataBaseRepository.anxietyPoints
        }
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.home_menu, menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.logOut -> props.logout()
        }
        return super.onOptionsItemSelected(item)
    }

    private fun handleAction(homeAction: HomeAction) {
        when (homeAction) {
            is HomeAction.LogOut -> (requireActivity() as MainActivity).router.openWelcome()
        }
    }
}
