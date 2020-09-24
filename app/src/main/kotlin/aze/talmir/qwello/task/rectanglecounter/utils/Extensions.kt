package aze.talmir.qwello.task.rectanglecounter.utils

import androidx.fragment.app.Fragment
import kotlin.math.floor
import kotlin.math.sqrt

fun Int.isPerfectSquare(): Boolean {
    val sqrt = sqrt(toDouble())
    return sqrt - floor(sqrt) == 0.00
}

fun Fragment.toPx(dp: Int): Int {
    val scale = resources.displayMetrics.density
    return (dp * scale + 0.5f).toInt()
}

fun Any.getMatrixIndexes(): Pair<Int, Int> {
    val matrixIndexes = toString().split(',')
    require(matrixIndexes.size == 2) {
        "Input format is not correct!"
    }
    return matrixIndexes[0].toInt() to matrixIndexes[1].toInt()
}
