package aze.talmir.qwello.task.rectanglecounter.di

import aze.talmir.qwello.task.rectanglecounter.screens.ui.main.MainViewModel
import kotlinx.coroutines.ExperimentalCoroutinesApi
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

@ExperimentalCoroutinesApi
private val appModule = module {
    viewModel { MainViewModel() }
}

/**
 *
 */
@ExperimentalCoroutinesApi
val appComponents = listOf(
    appModule
)
