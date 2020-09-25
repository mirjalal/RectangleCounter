package aze.talmir.qwello.task.rectanglecounter

import aze.talmir.qwello.task.rectanglecounter.utils.getMatrixIndexes
import aze.talmir.qwello.task.rectanglecounter.utils.isPerfectSquare
import org.junit.Assert.assertEquals
import org.junit.Assert.assertNotEquals
import org.junit.Test

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
class ExtensionsKtTest {

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

            // loop breaks where first 0 encounters
            if (input[m][j] == 0) {
                rowFlag = 0
                break
            }

            // pass because already processed
            if (input[m][j] == 4126)
                continue

            for (n in j until y) {
                lastN = n

                if (input[m][n] == 0) {
                    columnFlag = 0
                    break
                }

                // fill rectangle elements with any
                // number so that we can exclude next time
                input[m][n] = 4126
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

                    index += 1
                    findEnd(i, j, input, output, index)
                }
            }
        }

        return output.size
    }

    @Test
    fun test1() {
        var matrix =
            listOf(
//                mutableListOf(0, 0, 0, 0, 0, 0, 0, 0),
//                mutableListOf(0, 0, 0, 0, 0, 0, 0, 0),
//                mutableListOf(0, 0, 0, 0, 0, 0, 0, 0),
//                mutableListOf(0, 0, 0, 0, 0, 0, 0, 0),
//                mutableListOf(0, 0, 0, 0, 0, 0, 0, 0),
                mutableListOf(0, 0, 0/*, 0, 0, 0, 0, 0*/),
                mutableListOf(0, 0, 0/*, 0, 0, 0, 0, 0*/),
                mutableListOf(0, 0, 0/*, 0, 0, 0, 0, 0*/)
            )

        matrix[0][0] = 1
        getRectCount(
            matrix
        ).run { println(this) }
        matrix[0][0] = 1
        matrix[2][2] = 1
        val c = getRectCount(matrix)
        println(c)
    }

    @Test
    fun isPerfectSquare_isCorrect_returnsFalse() =
        assertNotEquals(true, 33.isPerfectSquare())

    @Test
    fun isPerfectSquare_isCorrect_returnsTrue() =
        assertEquals(true, 49.isPerfectSquare())

    @Test
    fun getMatrixIndexes_fails() = // should return false positive result
        assertEquals(12 to 98, "1,298".getMatrixIndexes())

    @Test
    fun getMatrixIndexes_succeeds() =
        assertEquals(67 to 43, "67,43".getMatrixIndexes())
}
