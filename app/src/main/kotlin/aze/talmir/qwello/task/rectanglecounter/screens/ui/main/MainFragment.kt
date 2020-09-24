package aze.talmir.qwello.task.rectanglecounter.screens.ui.main

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.widget.LinearLayoutCompat
import androidx.core.view.setPadding
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import aze.talmir.qwello.task.rectanglecounter.R
import aze.talmir.qwello.task.rectanglecounter.databinding.MainFragmentBinding
import aze.talmir.qwello.task.rectanglecounter.utils.getMatrixIndexes
import aze.talmir.qwello.task.rectanglecounter.utils.toPx
import com.google.android.material.checkbox.MaterialCheckBox
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.collect
import org.koin.androidx.viewmodel.ext.android.viewModel

@ExperimentalCoroutinesApi
class MainFragment : Fragment() {

    companion object {
        fun newInstance() = MainFragment()
    }

    init {
        lifecycleScope.launchWhenResumed {
            viewModel.counter.collect {
                binding.count.text = it.toString()
            }
        }
    }

    private lateinit var binding: MainFragmentBinding
    private val viewModel: MainViewModel by viewModel()

    @SuppressLint("ClickableViewAccessibility")
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View {
        binding = MainFragmentBinding.inflate(inflater)

        viewModel.initMatrix(8, 8)

        for (i in 0 until 8) {
            val checkBoxesHolder = LinearLayoutCompat(requireContext())
            checkBoxesHolder.orientation = LinearLayoutCompat.HORIZONTAL
            checkBoxesHolder.layoutParams = LinearLayoutCompat.LayoutParams(
                LinearLayoutCompat.LayoutParams.WRAP_CONTENT,
                LinearLayoutCompat.LayoutParams.WRAP_CONTENT
            )

            for (j in 0 until 8) {
                val checkBox = MaterialCheckBox(requireContext())
                checkBox.tag = "$i,$j"
                checkBox.setButtonDrawable(R.drawable.cell_selector)
                checkBox.setPadding(toPx(4))
                checkBox.layoutParams = ViewGroup.LayoutParams(
                    LinearLayoutCompat.LayoutParams.WRAP_CONTENT,
                    LinearLayoutCompat.LayoutParams.WRAP_CONTENT
                )
                checkBox.setOnCheckedChangeListener { _, isChecked ->
                    val (rowIndex, colIndex) = checkBox.tag.getMatrixIndexes()
                    println("$rowIndex, $colIndex")
                    if (isChecked)
                        viewModel.addPointTo(rowIndex, colIndex)
                    else
                        viewModel.removePointFrom(rowIndex, colIndex)
                }

                checkBoxesHolder.addView(checkBox)
            }

            binding.checkboxHolderRoot.addView(checkBoxesHolder)
        }

        binding.reset.setOnClickListener {
            viewModel.reset()
        }

        return binding.root
    }
}
