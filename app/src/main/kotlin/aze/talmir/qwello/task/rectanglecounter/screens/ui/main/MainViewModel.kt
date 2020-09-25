package aze.talmir.qwello.task.rectanglecounter.screens.ui.main

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

@ExperimentalCoroutinesApi
class MainViewModel : ViewModel() {

    companion object {
        private const val RECTANGLE_IDENTIFIER = 4126
    }

    private val matrix = mutableListOf<MutableList<Int>>()

    // private mutable state flow
    private val _selectedCounter = MutableStateFlow(0)
    private val _rectCounter = MutableStateFlow(0)

    // publicly exposed as read-only state flow
    val selectedCounter: StateFlow<Int> = _selectedCounter
    val rectCounter: StateFlow<Int> = _rectCounter

    fun initMatrix(rowsCount: Int, columnsCount: Int) {
        for (i in 0 until rowsCount) {
            matrix.add(
                mutableListOf<Int>().apply {
                    for (j in 0 until columnsCount)
                        add(0)
                }
            )
        }
    }

    private fun findEnd(i: Int, j: Int, input: List<MutableList<Int>>, output: MutableList<Any>, index: Int) {
        val x = input.size
        val y = input[0].size

        // flag to check column edge case, initializing with 0
        var columnFlag = 0
        // flag to check row edge case, initializing with 0
        var rowFlag = 0

        var lastM = 0
        var lastN = 0

        for (m in i until x) {
            lastM = m

            // loop breaks where first 1 encounters
            if (input[m][j] == 0) {
                rowFlag = 0
                break
            }

            // pass because already processed
            if (input[m][j] == RECTANGLE_IDENTIFIER)
                continue

            for (n in j until y) {
                lastN = n

                if (input[m][n] == 0) {
                    columnFlag = 0
                    break
                }

                // fill rectangle elements with any
                // number so that we can exclude next time
                input[m][n] = RECTANGLE_IDENTIFIER
            }
        }

        if (rowFlag == 0)
            output[index] = lastM - 1
        else // when end point touch the boundary
            output[index] = lastM

        if (columnFlag == 0)
            output[index] = lastN - 1
        else // when end point touch the boundary
            output[index] = lastN
    }

    private fun getRectCount(input: List<MutableList<Int>>): Int {
        val sizeOfInput = input.size

        val output = mutableListOf<Any>()
        var index = -1

        for (i in 0 until sizeOfInput) {
            for (j in input[i].indices) {
                if (input[i][j] == 1) {
                    output.add(listOf(i, j))
                    println(output)
                    index += 1
                    findEnd(i, j, input, output, index)
                }
            }
        }

        // remove 4126s' from matrix
        for (i in matrix.indices)
            for (j in matrix[i].indices)
                if (matrix[i][j] == RECTANGLE_IDENTIFIER)
                    matrix[i][j] = 1

        return output.size
    }

    fun addPointTo(rowIndex: Int, colIndex: Int) {
        _selectedCounter.value += 1
        modifyMatrixAndReCalc(rowIndex, colIndex, 1)
    }

    fun removePointFrom(rowIndex: Int, colIndex: Int) {
        _selectedCounter.value -= 1
        modifyMatrixAndReCalc(rowIndex, colIndex, 0)
    }

    private fun modifyMatrixAndReCalc(rowIndex: Int, colIndex: Int, value: Int) {
        matrix[rowIndex][colIndex] = value
        _rectCounter.value = getRectCount(matrix)
    }

    fun reset() {
        matrix.forEach {
            it.indices.forEach { i ->
                it[i] = 0
            }
        }
        _selectedCounter.value = 0
        _rectCounter.value = 0
    }
}
