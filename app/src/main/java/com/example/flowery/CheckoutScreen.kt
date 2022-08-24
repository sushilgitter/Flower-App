package com.example.flowery

import android.app.DatePickerDialog
import android.content.Intent
import android.os.Bundle
import android.widget.DatePicker
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.annotation.StringRes
import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.focus.FocusDirection
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.flowery.ui.theme.FloweryTheme
import java.util.*

class CheckoutScreen : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            FloweryTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background
                ) {
                    FlowerCheckoutScreen()
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun CheckoutPreview() {
    FloweryTheme {
        FlowerCheckoutScreen()
    }
}

@Composable
fun FlowerCheckoutScreen() {
    Scaffold(
        modifier = Modifier.fillMaxSize(),
        topBar = { CheckoutTopBar() },
        content = { CheckoutContent() },
        backgroundColor = colorResource(R.color.colorPrimary)
    )
}

@Composable
fun CheckoutTopBar() {
    TopAppBar(
        contentColor = Color.White,
        backgroundColor = colorResource(R.color.colorPrimary),
        elevation = 0.dp,
        navigationIcon = {
            IconButton(onClick = {/* Do Something*/ }) {
                Icon(painterResource(R.drawable.ic_baseline_arrow_back_ios_24), null)
            }
        },
        title = {
            Text(
                text = stringResource(id = R.string.checkout),
                modifier = Modifier.fillMaxWidth(),
                textAlign = TextAlign.Center,
                fontSize = 24.sp,
            )
        }
    )
}

@Composable
fun CheckoutContent() {

    val focusManager = LocalFocusManager.current

    // sender data
    var senderInput by remember { mutableStateOf("") }
//    var senderName = senderInput
    var senderAddressInput by remember { mutableStateOf("") }
//    var senderAddress = senderAddressInput
    var messageInput by remember { mutableStateOf("") }
//    var message = messageInput

    //receiver data
    var receiverInput by remember { mutableStateOf("") }
//    var receiverName = receiverInput
    var receiverAddressInput by remember { mutableStateOf("") }
//    var receiverAddress = receiverAddressInput
    var contactInput by remember { mutableStateOf("") }
//    var contact = contactInput

    //  date picker
    val context = LocalContext.current
    val cal = Calendar.getInstance()
    val year: Int = cal.get(Calendar.YEAR)
    val month: Int = cal.get(Calendar.MONTH)
    val day: Int = cal.get(Calendar.DAY_OF_MONTH)
    cal.time = Date()
    val date = remember { mutableStateOf("") }
    val datePickerDialog = DatePickerDialog(
        context,
        { _: DatePicker, mYear: Int, mMonth: Int, mDayOfMonth: Int ->
            date.value = "$mDayOfMonth/${mMonth + 1}/$mYear"
        }, year, month, day
    )
    Column(
        modifier = Modifier
            .fillMaxSize()
            .clip(RoundedCornerShape(topStart = 40.dp, topEnd = 40.dp))
            .background(colorResource(R.color.lightGray))
            .padding(16.dp,16.dp,16.dp,0.dp)
            .verticalScroll(rememberScrollState())
    )
    {
        Text(
            text = stringResource(R.string.fill_the_information_below),
            fontWeight = FontWeight.Bold,
            fontSize = 24.sp,
            modifier = Modifier.padding(1.dp, 16.dp, 0.dp, 20.dp)
        )
        InformationField(
            information = R.string.sender_Information,
            nameId = R.string.sender_name,
            valueName = senderInput,
            onValueChangeName = { senderInput = it },
            addressId = R.string.sender_address,
            valueAddress = senderAddressInput,
            onValueChangeAddress = { senderAddressInput = it },
            infoId = R.string.message_to_receiver,
            valueInfo = messageInput,
            onValueChangeInfo = { messageInput = it },
            placeholderInfo = R.string.write_your_message_here,
            keyboardOptions = KeyboardOptions.Default.copy(
                keyboardType = KeyboardType.Text,
                imeAction = ImeAction.Next
            ),
            keyboardActions = KeyboardActions(onNext = { focusManager.moveFocus(FocusDirection.Down) }),
            keyboardOptionsInfo = KeyboardOptions.Default.copy(
                keyboardType = KeyboardType.Text,
                imeAction = ImeAction.Next
            ),
            modifier = Modifier
                .fillMaxWidth()
                .height(100.dp)
        )
        Spacer(modifier = Modifier.height(24.dp))
        InformationField(
            information = R.string.receiver_information,
            nameId = R.string.receiver_name,
            valueName = receiverInput,
            onValueChangeName = { receiverInput = it },
            addressId = R.string.receiver_address,
            valueAddress = receiverAddressInput,
            onValueChangeAddress = { receiverAddressInput = it },
            infoId = R.string.receiver_contact_number,
            valueInfo = contactInput,
            onValueChangeInfo = { contactInput = it },
            placeholderInfo = R.string.receiver_contact_number,
            keyboardOptions = KeyboardOptions.Default.copy(
                keyboardType = KeyboardType.Text,
                imeAction = ImeAction.Next
            ),
            keyboardActions = KeyboardActions(onNext = { focusManager.moveFocus(FocusDirection.Down) }),
            keyboardOptionsInfo = KeyboardOptions.Default.copy(
                keyboardType = KeyboardType.Phone,
                imeAction = ImeAction.Next
            ),
            modifier = Modifier.fillMaxWidth()
        )
        Spacer(modifier = Modifier.height(24.dp))
        Card(
            modifier = Modifier
                .fillMaxWidth()
                .clip(RoundedCornerShape(20.dp))
        ) {
            Column(
                modifier = Modifier.padding(12.dp)
            ) {
                Text(
                    text = stringResource(R.string.delivery_information),
                    fontWeight = FontWeight.Bold,
                    fontSize = 20.sp
                )
                Text(
                    text = stringResource(R.string.date_of_delivery),
                    modifier = Modifier.padding(0.dp, 10.dp),
                )
                OutlinedTextField(
                    value = date.value,
                    onValueChange = { date.value = it },
                    modifier = Modifier.fillMaxWidth(),
                    placeholder = { Text(text = "dd/mm/yyyy") },
                    colors = TextFieldDefaults.outlinedTextFieldColors(
                        backgroundColor = Color.White,
                        cursorColor = colorResource(id = R.color.colorPrimary),
                        focusedBorderColor = colorResource(id = R.color.colorPrimary)
                    ),
                    readOnly = true,
                    shape = RoundedCornerShape(12.dp),
                    singleLine = true,
                    trailingIcon = {
                        IconButton(onClick = { datePickerDialog.show() }) {
                            Icon(
                                painter = painterResource(R.drawable.ic_baseline_calendar_month_24),
                                contentDescription = "location",
                                tint = colorResource(id = R.color.colorPrimary)
                            )
                        }
                    },
                    keyboardOptions = KeyboardOptions.Default.copy(
                        keyboardType = KeyboardType.Text,
                        imeAction = ImeAction.Done
                    ),
                    keyboardActions = KeyboardActions(onDone = { focusManager.clearFocus() })
                )

            }
        }
        Spacer(modifier = Modifier.height(24.dp))
        Button(
            onClick = {
                context.startActivity(Intent(context, PaymentMethodScreen::class.java))
            },
            modifier = Modifier
                .fillMaxWidth()
                .padding(12.dp)
                .clip(RoundedCornerShape(16.dp)),
            colors = ButtonDefaults.buttonColors(backgroundColor = colorResource(id = R.color.colorPrimary)),
        ) {
            Text(
                text = "Next  ",
                color = Color.White,
                modifier = Modifier.padding(8.dp)
            )
            Icon(
                painter = painterResource(R.drawable.ic_baseline_arrow_forward_24),
                contentDescription = "forward Arrow",
                tint = Color.White
            )
        }
    }
}


@Composable
fun InformationField(
    @StringRes information: Int,
    @StringRes nameId: Int,
    valueName: String,
    onValueChangeName: (String) -> Unit,
    @StringRes addressId: Int,
    valueAddress: String,
    onValueChangeAddress: (String) -> Unit,
    @StringRes infoId: Int,
    valueInfo: String,
    onValueChangeInfo: (String) -> Unit,
    @StringRes placeholderInfo: Int,
    keyboardOptions: KeyboardOptions,
    keyboardActions: KeyboardActions,
    keyboardOptionsInfo: KeyboardOptions,
    modifier: Modifier = Modifier
) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .clip(RoundedCornerShape(20.dp))
    ) {
        Column(
            modifier = Modifier.padding(12.dp)
        ) {
            Text(
                text = stringResource(information),
                fontWeight = FontWeight.Bold,
                fontSize = 20.sp
            )
            Text(
                text = stringResource(nameId),
                modifier = Modifier.padding(0.dp, 10.dp),
            )
            OutlinedTextField(
                value = valueName,
                onValueChange = onValueChangeName,
                modifier = Modifier.fillMaxWidth(),
                placeholder = { Text(text = stringResource(nameId)) },
                colors = TextFieldDefaults.outlinedTextFieldColors(
                    backgroundColor = Color.White,
                    cursorColor = colorResource(id = R.color.colorPrimary),
                    focusedBorderColor = colorResource(id = R.color.colorPrimary)
                ),
                shape = RoundedCornerShape(12.dp),
                keyboardOptions = keyboardOptions,
                keyboardActions = keyboardActions
            )
            Text(
                text = stringResource(addressId),
                modifier = Modifier.padding(0.dp, 10.dp)
            )
            OutlinedTextField(
                value = valueAddress,
                onValueChange = onValueChangeAddress,
                modifier = Modifier.fillMaxWidth(),
                placeholder = { Text(text = stringResource(addressId)) },
                colors = TextFieldDefaults.outlinedTextFieldColors(
                    backgroundColor = Color.White,
                    cursorColor = colorResource(id = R.color.colorPrimary),
                    focusedBorderColor = colorResource(id = R.color.colorPrimary)
                ),
                shape = RoundedCornerShape(12.dp),
                trailingIcon = {
                    Icon(
                        painter = painterResource(R.drawable.ic_baseline_location_on_24),
                        contentDescription = "location",
                        tint = colorResource(id = R.color.colorPrimary)
                    )
                },
                keyboardOptions = keyboardOptions,
                keyboardActions = keyboardActions
            )
            Text(
                text = stringResource(infoId),
                modifier = Modifier.padding(0.dp, 10.dp)
            )
            OutlinedTextField(
                value = valueInfo,
                onValueChange = onValueChangeInfo,
                placeholder = { Text(text = stringResource(placeholderInfo)) },
                colors = TextFieldDefaults.outlinedTextFieldColors(
                    backgroundColor = Color.White,
                    cursorColor = colorResource(id = R.color.colorPrimary),
                    focusedBorderColor = colorResource(id = R.color.colorPrimary)
                ),
                shape = RoundedCornerShape(12.dp),
                modifier = modifier,
                keyboardOptions = keyboardOptionsInfo,
                keyboardActions = keyboardActions
            )
        }
    }
}