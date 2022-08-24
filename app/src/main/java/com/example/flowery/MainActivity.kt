package com.example.flowery

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.focus.FocusDirection
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.flowery.ui.theme.FloweryTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            FloweryTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background
                ) {
                    LoginScreen()
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun LoginScreenPreview() {
    FloweryTheme {
        LoginScreen()
    }
}

@Composable
fun LoginScreen() {
    var emailInput by remember { mutableStateOf("") }
    var email = emailInput
    var passwordInput by remember { mutableStateOf("") }
    var password = passwordInput
    val focusManager = LocalFocusManager.current
    val context = LocalContext.current

    Box(
        modifier = Modifier.fillMaxSize()
    ) {
        Box {
            Image(
                painterResource(R.drawable.login_bg),
                contentDescription = "Login Background",
                modifier = Modifier
                    .fillMaxWidth()
                    .height(316.dp)
            )
            Text(
                text = stringResource(id = R.string.app_name),
                fontStyle = FontStyle.Italic,
                fontFamily = FontFamily.SansSerif,
                fontSize = 42.sp,
                color = Color.White,
                modifier = Modifier.align(Alignment.Center)
            )
        }
        Column {
            Spacer(modifier = Modifier.height(250.dp))
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .clip(RoundedCornerShape(topStart = 40.dp, topEnd = 40.dp))
                    .background(colorResource(R.color.lightGray))
                    .padding(20.dp)
                    .verticalScroll(rememberScrollState())

            ) {
                Text(
                    text = stringResource(R.string.login_to_your_account),
                    textAlign = TextAlign.Center,
                    fontSize = 24.sp,
                    modifier = Modifier.fillMaxWidth()
                )
                Spacer(modifier = Modifier.height(28.dp))
                InputField(
                    textId = R.string.email_Address,
                    imageId = R.drawable.ic_baseline_email_24,
                    value = emailInput,
                    onValueChange = { emailInput = it },
                    keyboardOptions = KeyboardOptions.Default.copy(
                        keyboardType = KeyboardType.Password,
                        imeAction = ImeAction.Next
                    ),
                    keyboardActions = KeyboardActions(onNext = {
                        focusManager.moveFocus(
                            FocusDirection.Down
                        )
                    })
                )
                Spacer(modifier = Modifier.height(12.dp))
                InputField(
                    textId = R.string.password,
                    imageId = R.drawable.ic_baseline_lock_24,
                    value = passwordInput,
                    onValueChange = { passwordInput = it },
                    keyboardOptions = KeyboardOptions.Default.copy(
                        keyboardType = KeyboardType.Password,
                        imeAction = ImeAction.Done
                    ),
                    keyboardActions = KeyboardActions(onDone = { focusManager.clearFocus() })
                )
                Text(
                    text = stringResource(R.string.forgot_password),
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(16.dp, 0.dp),
                    textAlign = TextAlign.End
                )
                Spacer(modifier = Modifier.height(40.dp))
                Button(
                    onClick = {
                        context.startActivity(Intent(context, HomeScreen::class.java))

                        Toast.makeText(
                            context,
                            "Hello!  your email address = $email and Password = $password .",
                            Toast.LENGTH_LONG
                        ).show()
                    },
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(12.dp, 0.dp)
                        .clip(RoundedCornerShape(16.dp)),

                    colors = ButtonDefaults
                        .buttonColors(backgroundColor = colorResource(id = R.color.colorPrimary))
                ) {
                    Text(
                        text = stringResource(R.string.login),
                        modifier = Modifier.padding(8.dp),
                        fontSize = 16.sp
                    )
                }
                Spacer(modifier = Modifier.height(32.dp))
                Text(
                    text = stringResource(id = R.string.dont_have_an_account),
                    modifier = Modifier
                        .fillMaxWidth(),
                    textAlign = TextAlign.Center

                )
            }
        }
    }
}

@Composable
private fun InputField(
    @StringRes textId: Int,
    @DrawableRes imageId: Int,
    value: String,
    onValueChange: (String) -> Unit,
    keyboardOptions: KeyboardOptions,
    keyboardActions: KeyboardActions,
) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp, 0.dp)
    ) {
        Text(
            text = stringResource(textId),
            modifier = Modifier.padding(0.dp, 4.dp)
        )
        Row(
            modifier = Modifier
                .border(1.dp, Color.Gray, RoundedCornerShape(16.dp))
        ) {
            Image(
                painterResource(imageId),
                contentDescription = "Email Address",
                modifier = Modifier.padding(8.dp, 16.dp, 6.dp, 16.dp)
            )
            Divider(
                color = Color.Gray,
                modifier = Modifier
                    .height(56.dp)
                    .width(1.dp),
            )
            TextField(
                value = value,
                onValueChange = onValueChange,
                placeholder = { Text(text = stringResource(textId)) },
                modifier = Modifier.background(Color.Transparent),
                colors = TextFieldDefaults.textFieldColors(
                    backgroundColor = colorResource(id = R.color.lightGray),
                    unfocusedIndicatorColor = Color.Transparent,
                    focusedIndicatorColor = Color.Transparent
                ),
                keyboardOptions = keyboardOptions,
                keyboardActions = keyboardActions,
                singleLine = true,
            )
        }
    }
}

