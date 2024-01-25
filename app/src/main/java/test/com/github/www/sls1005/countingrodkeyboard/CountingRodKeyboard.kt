package test.com.github.www.sls1005.countingrodkeyboard

import android.view.View
import android.widget.ImageButton
import android.inputmethodservice.InputMethodService
import android.content.res.Configuration.ORIENTATION_LANDSCAPE
import android.view.KeyEvent
import android.view.KeyEvent.ACTION_DOWN
import android.view.KeyEvent.ACTION_UP
import android.view.KeyEvent.FLAG_SOFT_KEYBOARD
import android.view.KeyEvent.KEYCODE_DEL
import android.view.KeyEvent.KEYCODE_ENTER

class CountingRodKeyboard : InputMethodService() {
    override fun onCreateInputView(): View {
        val v = layoutInflater.inflate(
            when(resources.configuration.orientation) {
                ORIENTATION_LANDSCAPE -> R.layout.keyboard_horizontal
                else -> R.layout.keyboard_vertical
            }, null)
        listOf(
            R.id.button0,
            R.id.button1,
            R.id.button2,
            R.id.button3,
            R.id.button4,
            R.id.button5,
            R.id.button6,
            R.id.button7,
            R.id.button8,
            R.id.button9
        ).forEachIndexed { idx, id ->
            v.findViewById<ImageButton>(id).setOnClickListener {
                currentInputConnection.commitText("$idx", 1)
            }
        }
        v.findViewById<ImageButton>(R.id.button0).setOnLongClickListener {
            switchKeyboard(this@CountingRodKeyboard)
            (true)
        }
        listOf(
            Pair(R.id.enterKey, KEYCODE_ENTER),
            Pair(R.id.deleteKey, KEYCODE_DEL)
        ).forEach { it ->
            val (id, code) = it
            v.findViewById<ImageButton>(id).apply {
                setOnClickListener {
                    with(currentInputConnection) {
                        listOf(ACTION_DOWN, ACTION_UP).forEach { action ->
                            sendKeyEvent(
                                KeyEvent.changeFlags(KeyEvent(action, code), FLAG_SOFT_KEYBOARD)
                            )
                        }
                    }
                }
            }
        }
        return v
    }
}