package aze.talmir.qwello.task.rectanglecounter.di

import aze.talmir.qwello.task.rectanglecounter.screens.ui.main.MainViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

private val appModule = module {
    viewModel { MainViewModel() }
}

/**
 *
 */
val appComponents = listOf(
    appModule
)
