package aze.talmir.qwello.task.rectanglecounter.screens

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import aze.talmir.qwello.task.rectanglecounter.R
import aze.talmir.qwello.task.rectanglecounter.screens.ui.main.MainFragment

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.main_activity)
        if (savedInstanceState == null) {
            supportFragmentManager.beginTransaction()
                .replace(R.id.container, MainFragment.newInstance())
                .commitNow()
        }
    }
}
