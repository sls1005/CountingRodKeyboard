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
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
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

class DocumentationActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val images = listOf(
            Pair(R.drawable.zero, R.drawable.zero),
            Pair(R.drawable.one_vertical, R.drawable.one_horizontal),
            Pair(R.drawable.two_vertical, R.drawable.two_horizontal),
            Pair(R.drawable.three_vertical, R.drawable.three_horizontal),
            Pair(R.drawable.four, R.drawable.four),
            Pair(R.drawable.five_vertical, R.drawable.five_horizontal),
            Pair(R.drawable.six_vertical, R.drawable.six_horizontal),
            Pair(R.drawable.seven_vertical, R.drawable.seven_horizontal),
            Pair(R.drawable.eight_vertical, R.drawable.eight_horizontal),
            Pair(R.drawable.nine_vertical, R.drawable.nine_horizontal)
        )
        setContent {
            CountingRodKeyboardTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    Column(
                        verticalArrangement = Arrangement.Center,
                        horizontalAlignment = Alignment.CenterHorizontally,
                        modifier = Modifier.verticalScroll(rememberScrollState())
                    ) {
                        Text(stringResource(id = R.string.title1), fontSize=24.sp, fontWeight = FontWeight.Bold, modifier = Modifier.padding(10.dp))
                        Text(stringResource(id = R.string.description1), fontSize=20.sp, modifier = Modifier.padding(10.dp))
                        Text(stringResource(id = R.string.title2), fontSize=22.sp, fontWeight = FontWeight.Bold, modifier = Modifier.padding(10.dp))
                        for (i in images.indices) {
                            val (v, h) = images[i]
                            Row(
                                horizontalArrangement = Arrangement.Center,
                                verticalAlignment = Alignment.CenterVertically
                            ) {
                                Icon(painter= painterResource(id = v), null)
                                Icon(painter= painterResource(id = h), null)
                                Text("$i", fontSize=30.sp, modifier = Modifier.padding(20.dp))
                            }
                        }
                        Text(
                            stringResource(id = R.string.hints),
                            fontSize = 24.sp,
                            fontWeight = FontWeight.Bold,
                            modifier = Modifier
                                .padding(10.dp)
                        )
                        Text(
                            stringResource(id = R.string.hint1),
                            fontSize = 20.sp,
                            modifier = Modifier
                                .padding(10.dp)
                        )
                        Text(
                            stringResource(id = R.string.hint2),
                            fontSize = 20.sp,
                            modifier = Modifier
                                .padding(10.dp)
                        )
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
