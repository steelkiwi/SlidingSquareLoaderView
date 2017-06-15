package steelkiwi.com.slidingsquaresloaderview

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import com.steelkiwi.library.SlidingSquareLoaderView

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val view = findViewById(R.id.view) as SlidingSquareLoaderView

        findViewById(R.id.button1).setOnClickListener {
            view.show()
        }

        findViewById(R.id.button2).setOnClickListener {
            view.hide()
        }
    }
}
