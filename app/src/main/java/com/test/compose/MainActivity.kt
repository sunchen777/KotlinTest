package com.test.compose

import android.content.res.Configuration
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.test.compose.ui.theme.ComposeTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ComposeTheme {
                MessageCard(Message("1234567890","abcdefghijklmn"))
            }
        }
    }


}

data class Message(val title: String, val text: String)

@Composable
fun MessageCard(msg: Message) {
    Column {
        Column {
            Text(text = "垂直排列")
            Text(text = msg.title)
            Text(text = msg.text)
        }
        Row {
            Text(text = "水平排列")
            Text(text = msg.title)
            Text(text = msg.text)
        }
        Box {
            Text(text = "堆叠排列")
            Text(text = msg.title)
            Text(text = msg.text)
        }
        Row(modifier = Modifier.padding(all = 8.dp)) {
            Image(
                painter = painterResource(R.drawable.ic_launcher_round),
                contentDescription = "Contact profile picture",
                modifier = Modifier
                    .size(40.dp)
                    .clip(CircleShape)
                    .border(1.5.dp, MaterialTheme.colors.secondary, CircleShape)
            )
            Spacer(modifier = Modifier.width(8.dp))
            Column {
                Text(
                    text = msg.title,
                    color = MaterialTheme.colors.secondaryVariant,
                    style = MaterialTheme.typography.subtitle1
                )
                Spacer(modifier = Modifier.height(4.dp))
                Surface(shape = MaterialTheme.shapes.medium, elevation = 3.dp, color = MaterialTheme.colors.secondary) {
                    Text(
                        text = msg.text,
                        style = MaterialTheme.typography.body1
                    )
                }

            }
        }
    }
}

@Preview(
    name = "Light Mode"
)
@Preview(
    uiMode = Configuration.UI_MODE_NIGHT_YES,
    showBackground = true,
    name = "Dark Mode"
)
@Composable
fun DefaultPreview() {
    ComposeTheme {
        Surface {
            MessageCard(msg = Message("1234567890","abcdefghijklmn"))
        }
    }
}