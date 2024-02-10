package test.com.github.www.sls1005.countingrodkeyboard

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.provider.Settings.ACTION_INPUT_METHOD_SETTINGS
import android.view.inputmethod.InputMethodManager
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import test.com.github.www.sls1005.countingrodkeyboard.ui.theme.CountingRodKeyboardTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            CountingRodKeyboardTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    Column(
                        verticalArrangement = Arrangement.Center,
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        OutlinedButton(
                            onClick = { startActivity(Intent(ACTION_INPUT_METHOD_SETTINGS)) },
                            modifier = Modifier
                                .size(width = 400.dp, height = 100.dp)
                                .padding(10.dp)
                        ) {
                            Text(stringResource(id = R.string.button1_text), fontSize=22.sp)
                        }
                        OutlinedButton(
                            onClick = { switchKeyboard(this@MainActivity) },
                            modifier = Modifier
                                .size(width = 400.dp, height = 100.dp)
                                .padding(10.dp)
                        ) {
                            Text(stringResource(id = R.string.button2_text), fontSize=22.sp)
                        }
                        OutlinedButton(
                            onClick = { startActivity(Intent(this@MainActivity, DocumentationActivity::class.java)) },
                            modifier = Modifier
                                .size(width = 400.dp, height = 100.dp)
                                .padding(10.dp)
                        ) {
                            Text(stringResource(id = R.string.button3_text), fontSize=22.sp)
                        }
                    }
                }
            }
        }
    }
}

internal fun switchKeyboard(ctx: Context) {
    ctx.getSystemService(Context.INPUT_METHOD_SERVICE).also {
        if (it is InputMethodManager) {
            it.showInputMethodPicker()
        }
    }
}
