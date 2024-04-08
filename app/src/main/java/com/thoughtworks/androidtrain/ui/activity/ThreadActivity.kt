package com.thoughtworks.androidtrain.ui.activity
import android.os.AsyncTask
import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import com.thoughtworks.androidtrain.R

class ThreadActivity : AppCompatActivity() {
    private lateinit var countButton: Button
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_thread_layout)
        countButton = findViewById(R.id.thread_counter)
        countButton.text = "0"

        countButton.setOnClickListener{startingCounterTask()}
    }

    private fun startingCounterTask() {
        val countingTask = CountingTask()
        countingTask.execute()
    }

    inner class CountingTask : AsyncTask<Void, Int, Void>() {
        override fun doInBackground(vararg params: Void?): Void? {
            var count = 0

            while (count < 10) {
                Thread.sleep(1000)
                count++
                publishProgress(count)
            }

            return null
        }

        override fun onProgressUpdate(vararg values: Int?) {
            super.onProgressUpdate(*values)
            val count = values[0] ?: 0
            countButton.text = count.toString()
        }

        override fun onPostExecute(result: Void?) {
            super.onPostExecute(result)
        }
    }
}