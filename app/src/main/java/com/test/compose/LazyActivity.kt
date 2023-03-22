package com.test.compose

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.animation.animateColorAsState
import androidx.compose.animation.animateContentSize
import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.CornerSize
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch

class LazyActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {

        }
    }
}

@Preview
@Composable
fun PreviewConversation() {
    MaterialTheme{
        Conversation(SampleData.conversationSample)
    }
}

@Composable
fun Conversation(messages: List<Message>) {
    val scaffoldState = rememberScaffoldState()
    val scope = rememberCoroutineScope()
    var isVisable by remember { mutableStateOf(true) }
    Scaffold(
        scaffoldState = scaffoldState,
        topBar = { TopBar(scaffoldState, scope) },
        bottomBar = { BottomBar(scaffoldState, scope) },
        floatingActionButton = { isVisable = FloatingButton(scaffoldState, scope) },
        isFloatingActionButtonDocked = true,
        drawerContent = { Text(text = "111")
                            Divider()}
    ) {
        LazyColumn {
            items(messages) { message ->
                MessageLazyCard(message)
            }
            item { 
                Spacer(modifier = Modifier.height(50.dp))
            }
        }
    }

}

@Composable
fun MessageLazyCard(msg: Message) {
    Column {
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
            var isExpanded by remember { mutableStateOf(false) }
            val surfaceColor by animateColorAsState(
                if (isExpanded) MaterialTheme.colors.primary else MaterialTheme.colors.surface,
            )

            Column(modifier = Modifier.clickable { isExpanded = !isExpanded }) {
                Text(
                    text = msg.title,
                    color = MaterialTheme.colors.secondaryVariant,
                    style = MaterialTheme.typography.subtitle1
                )
                Spacer(modifier = Modifier.height(4.dp))
                Surface(
                    shape = MaterialTheme.shapes.medium,
                    elevation = 3.dp,
                    color = surfaceColor,
                    modifier = Modifier
                        .animateContentSize()
                        .padding(1.dp)) {
                    Text(
                        text = msg.text,
                        modifier = Modifier.padding(all = 4.dp),
                        // If the message is expanded, we display all its content
                        // otherwise we only display the first line
                        maxLines = if (isExpanded) Int.MAX_VALUE else 1,
                        style = MaterialTheme.typography.body1
                    )
                }

            }
        }
    }
}

@Composable
fun TopBar(scaffoldState: ScaffoldState, scope: CoroutineScope) {
    TopAppBar (
        backgroundColor = Color.Yellow,
    ) {
        Row(
            Modifier.padding(10.dp).width(LocalConfiguration.current.screenWidthDp.dp),
            horizontalArrangement = Arrangement.End,
        ) {
            Text(text = "1")
            Text(text = "2")
            Text(text = "3")
            Button(
                onClick = {
                    scope.launch {
                        scaffoldState.drawerState.apply {
                            if (isClosed) open() else close()
                        }
                    }
                },
                contentPadding = PaddingValues(
                    start = 20.dp,
                    end = 20.dp
                )

            ) {
                Icon(
                    Icons.Filled.Favorite,
                    contentDescription = "Favorite",
                    modifier = Modifier.size(ButtonDefaults.IconSize)
                )
                Spacer(Modifier.size(ButtonDefaults.IconSpacing))
                Text("Like")
            }
        }
    }
}

@Composable
fun BottomBar(scaffoldState: ScaffoldState, scope: CoroutineScope) {
    var state = scaffoldState.drawerState.isOpen
    BottomAppBar(
        backgroundColor = if (state) Color.Green else Color.Red,
        cutoutShape = MaterialTheme.shapes.small.copy(
            CornerSize(percent = 50)
        )
    ) {
        Row(
            Modifier.padding(10.dp)
        ) {
            Text(text = "1")
            Text(text = "2")
            Text(text = "3")
        }
    }
}

@Composable
fun FloatingButton(scaffoldState: ScaffoldState, scope: CoroutineScope): Boolean {
    var isVisable = false
    FloatingActionButton(
        onClick = {
            scope.launch {
                val result = scaffoldState.snackbarHostState
                    .showSnackbar(
                        message = "Snackbar",
                        actionLabel = "Action",
                        // Defaults to SnackbarDuration.Short
                        duration = SnackbarDuration.Indefinite
                    )
                when (result) {
                    SnackbarResult.ActionPerformed -> {
                        isVisable =  true
                    }
                    SnackbarResult.Dismissed -> {
                        isVisable = false
                    }
                }
            }
        }
    ) {
        Icon(
            Icons.Filled.Add,
            contentDescription = "Add",
            modifier = Modifier.size(ButtonDefaults.IconSize)
        )
    }
    return isVisable
}



