package com.example.birthday_card

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.birthday_card.ui.theme.Birthday_CardTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            Birthday_CardTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                      GreetingImage(
                          // Extracting string resource helps in translation into other lang
                          // app -> res -> values -> strings.xml
                          message = stringResource(R.string.happy_birthday_text),
                          from = stringResource(R.string.from_signature_text),
                          modifier = Modifier
                      )
                }
            }
        }
    }
}
/*
        UI elements in Android apps use two different units of measurement:
        1. density-independent pixels (DP), which you use later for the layout, and
        2. scalable pixels (SP).

        Note: By default, the SP unit is the same size as the DP unit, but it
        resizes based on the user's preferred text size under phone settings.
*/
@Composable
fun GreetingText(message: String, from: String, modifier: Modifier = Modifier) {
    Column( // Column is composable component for setting parent child hierarchy
        verticalArrangement = Arrangement.Center,
        modifier = modifier.padding(8.dp)
    ) {
        Text( // Text composable passing in a text message as named argument
            text = message,
            fontSize = 100.sp, // The scalable pixels (SP) is a unit of measure for the font size
            lineHeight = 116.sp,
            textAlign = TextAlign.Center,
            color = Color.Magenta,
            modifier = Modifier
                .alpha(.7F)
        )
        Text(
            text = from,
            fontSize = 36.sp,
            color = Color.Yellow,
            modifier = Modifier
                .padding(16.dp)
                .align(alignment = Alignment.CenterHorizontally)
                .background(color = Color.Black)
        )
    }
}

@Composable
fun GreetingImage(message: String, from: String, modifier: Modifier) {
/* Resources can be accessed with resource IDs that are generated in your project's R class.
   Syntax: R.drawable.graphic
    {
             R = auto generated R class,
      drawable = subdirectory in res folder
       graphic = Resource ID (mostly same as filename)
    }
*/
    val image = painterResource(id = R.drawable.androidparty)
    /*
        Use Box layout to stack elements on top of one another.
        Box layout also lets you configure the specific alignment
        of the elements that it contains.
    */
    Box(modifier) {
        Image(
            painter = image,
            /*
                Content description essential for google talkback for disabled
                Why null? Cuz this image will be in the background
            */
            contentDescription = null,
            contentScale = ContentScale.Crop, // For removing bottom whitespace by cropping
            alpha = 0.5F // opacity of image
        )
        GreetingText(
            message = message,
            from = from,
            modifier = Modifier
                .fillMaxSize() // This will center the whole GreetingText block
                .padding(8.dp)
        )
    }
}

@Preview(showBackground = true)
@Composable
fun BirthdayCardPreview() {
    Birthday_CardTheme {
        GreetingImage(message = "Happy Birthday Eli!", from = "From Tahir", modifier = Modifier)
    }
}