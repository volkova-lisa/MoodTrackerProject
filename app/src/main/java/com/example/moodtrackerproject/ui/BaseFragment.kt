package com.example.moodtrackerproject.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.viewbinding.ViewBinding
import com.example.moodtrackerproject.utils.hideKeyboard

abstract class BaseFragment<VIEW_MODEL : BaseViewModel<PROPS>, BINDING : ViewBinding, PROPS>(
    private val modelClass: Class<VIEW_MODEL>
) : Fragment() {

    private var _binding: BINDING? = null
    protected val binding get() = _binding

    private val viewModel: VIEW_MODEL by lazy {
        ViewModelProvider(this)[modelClass]
    }

    abstract fun getFragmentBinding(inflater: LayoutInflater, container: ViewGroup?): BINDING

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = getFragmentBinding(inflater, container)
        return binding?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel.liveData.observe(viewLifecycleOwner, ::render)
    }

    abstract fun render(props: PROPS)

    override fun onStop() {
        super.onStop()
        hideKeyboard()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
