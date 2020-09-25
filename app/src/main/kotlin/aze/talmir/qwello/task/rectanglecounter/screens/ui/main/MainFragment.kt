package aze.talmir.qwello.task.rectanglecounter.screens.ui.main

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.widget.LinearLayoutCompat
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import aze.talmir.qwello.task.rectanglecounter.R
import aze.talmir.qwello.task.rectanglecounter.databinding.MainFragmentBinding
import com.google.android.material.checkbox.MaterialCheckBox
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import org.koin.androidx.viewmodel.ext.android.viewModel

@ExperimentalCoroutinesApi
class MainFragment : Fragment() {

    companion object {
        private const val ROW_COUNT = 8
        private const val COL_COUNT = 8

        fun newInstance() = MainFragment()
    }

    init {
        // https://github.com/Kotlin/kotlinx.coroutines/issues/1933#issuecomment-618283098
        lifecycleScope.launchWhenResumed {
            viewModel.rectCounter.onEach { binding.totalRectCount = it }.launchIn(this)
            viewModel.selectedCounter.onEach { binding.totalSelected = it }.launchIn(this)
        }
    }

    private lateinit var binding: MainFragmentBinding

    // Inject viewModel instance using Koin.
    private val viewModel: MainViewModel by viewModel()

    @SuppressLint("ClickableViewAccessibility")
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View {

        fun generateCheckableItem(rIndex: Int, cIndex: Int): MaterialCheckBox =
            MaterialCheckBox(requireContext()).apply {
                tag = "$rIndex,$cIndex"
                setButtonDrawable(R.drawable.cell_selector)
//                checkBox.setPadding(toPx(4))
                layoutParams = ViewGroup.LayoutParams(
                    LinearLayoutCompat.LayoutParams.WRAP_CONTENT,
                    LinearLayoutCompat.LayoutParams.WRAP_CONTENT
                )
                setOnCheckedChangeListener { _, isChecked ->
                    if (isChecked)
                        viewModel.addPointTo(rIndex, cIndex)
                    else
                        viewModel.removePointFrom(rIndex, cIndex)
                }
            }

        return MainFragmentBinding.inflate(inflater).apply {
            binding = this

            for (i in 0 until ROW_COUNT) {
                val checkBoxesHolder = LinearLayoutCompat(requireContext())
                checkBoxesHolder.orientation = LinearLayoutCompat.HORIZONTAL
                checkBoxesHolder.layoutParams = LinearLayoutCompat.LayoutParams(
                    LinearLayoutCompat.LayoutParams.WRAP_CONTENT,
                    LinearLayoutCompat.LayoutParams.WRAP_CONTENT
                )

                for (j in 0 until COL_COUNT)
                    checkBoxesHolder.addView(generateCheckableItem(i, j))

                checkboxHolderRoot.addView(checkBoxesHolder)
            }

            reset.setOnClickListener {
                viewModel.reset()
            }
        }.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) =
        viewModel.initMatrix(ROW_COUNT, COL_COUNT)
}
