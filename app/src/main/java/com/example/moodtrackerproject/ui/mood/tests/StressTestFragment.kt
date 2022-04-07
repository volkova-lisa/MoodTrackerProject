package com.example.moodtrackerproject.ui.mood.tests

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.example.moodtrackerproject.data.DataBaseRepository
import com.example.moodtrackerproject.databinding.FragmentStressTestBinding

class StressTestFragment : Fragment() {

    private lateinit var binding: FragmentStressTestBinding
    private val testAdapter = TestAdapter()
    val viewModel: StressTestViewModel by lazy {
        ViewModelProvider(this).get(StressTestViewModel::class.java)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentStressTestBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel.liveData.value?.setQuestion?.invoke(0)
        binding.optionList.adapter = testAdapter
        viewModel.liveData.observe(viewLifecycleOwner, {
            render(it)
        })
    }

    private fun render(state: StressTestState) {
        binding.run {

            question.text = state.question.text
            testAdapter.setList(DataBaseRepository.lisOfOptions)

            Log.d("1111111", state.question.text)

//            o1.root.setOnClickListener {
//                o1.card.setBackgroundResource(R.drawable.stress_bg)
//                o1.option.setTextColor(resources.getColor(R.color.white))
//            }
        }
    }
}
