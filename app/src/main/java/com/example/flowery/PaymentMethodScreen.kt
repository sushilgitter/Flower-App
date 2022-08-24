package com.example.flowery

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.relocation.BringIntoViewRequester
import androidx.compose.foundation.relocation.bringIntoViewRequester
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.material.icons.filled.KeyboardArrowUp
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.focus.FocusDirection
import androidx.compose.ui.focus.onFocusEvent
import androidx.compose.ui.graphics.Color
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
import kotlinx.coroutines.launch

class PaymentMethodScreen : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
//        window.setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_RESIZE);
        setContent {
            FloweryTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background
                ) {
                    FlowerPaymentMethodScreen()
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun PaymentMethodPreview() {
    FloweryTheme {
        FlowerPaymentMethodScreen()
    }
}

@Composable
fun FlowerPaymentMethodScreen() {
    Scaffold(
        topBar = { PaymentMethodTopBar() },
        content = { PaymentMethodContent() },
        backgroundColor = colorResource(R.color.colorPrimary)
    )
}

@Composable
fun PaymentMethodTopBar() {
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
                text = stringResource(R.string.payment_method),
                modifier = Modifier.fillMaxWidth(),
                textAlign = TextAlign.Center,
                fontSize = 24.sp,
            )
        }
    )
}

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun PaymentMethodContent() {
    val focusManager = LocalFocusManager.current

    val selectedModeOfPayment = remember { mutableStateOf("") }
    val card = "Card"
    val payOnDelivery = "PayOnDelivery"

    var cardHolderInput by remember { mutableStateOf("") }
//    var cardHolderName = cardHolderInput
    var cardNumberInput by remember { mutableStateOf("") }
//    var cardNumberName = cardNumberInput
    var cvvInput by remember { mutableStateOf("") }
//    var cvv = cvvInput


    //key board
    val bringIntoViewRequester = remember { BringIntoViewRequester() }
    val coroutineScope = rememberCoroutineScope()


    // drop down menu Month
    var mExpanded by remember { mutableStateOf(false) }
    val months = listOf("1","2","3","4","5","6","7","8","9","10","11","12")
    var selectedMonth by remember { mutableStateOf("10") }
    val iconM = if (mExpanded)
        Icons.Filled.KeyboardArrowUp
    else
        Icons.Filled.KeyboardArrowDown

    // drop down menu Year
    var yExpanded by remember { mutableStateOf(false) }
    val years = listOf("2022","2023","2024","2025","2026","2027","2028","2029","2030","2031","2032")
    var selectedYear by remember { mutableStateOf("2023") }
    val iconY = if (yExpanded)
        Icons.Filled.KeyboardArrowUp
    else
        Icons.Filled.KeyboardArrowDown


    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(
                colorResource(R.color.lightGray),
                RoundedCornerShape(topStart = 40.dp, topEnd = 40.dp)
            ),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Column(
            modifier = Modifier
                .padding(16.dp, 16.dp, 16.dp, 0.dp)
                .verticalScroll(rememberScrollState())
        ) {
            Text(
                text = stringResource(R.string.select_your_mode_of_payment),
                fontWeight = FontWeight.Bold,
                fontSize = 24.sp,
                modifier = Modifier.padding(0.dp, 8.dp)
            )
            Row(
                modifier = Modifier.padding(0.dp, 8.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = "Credit Card/Debit Card",
                    fontSize = 24.sp,
                    modifier = Modifier.padding(0.dp, 0.dp, 100.dp, 0.dp)
                )
                RadioButton(
                    selected = selectedModeOfPayment.value == card,
                    onClick = { selectedModeOfPayment.value = card },
                    colors = RadioButtonDefaults.colors(selectedColor = colorResource(R.color.colorPrimary))
                )
            }

            Card(
                modifier = Modifier
                    .fillMaxWidth()
                    .clip(RoundedCornerShape(20.dp))
            ) {
                Column(
                    modifier = Modifier.padding(12.dp)
                ) {
                    Text(
                        text = "Card Holder Name",
                        modifier = Modifier.padding(0.dp, 8.dp)
                    )
                    OutlinedTextField(
                        value = cardHolderInput,
                        onValueChange = { cardHolderInput = it },
                        placeholder = { Text(text = "Name") },
                        shape = RoundedCornerShape(12.dp),
                        colors = TextFieldDefaults.outlinedTextFieldColors(
                            backgroundColor = Color.White,
                            cursorColor = colorResource(id = R.color.colorPrimary),
                            focusedBorderColor = colorResource(id = R.color.colorPrimary)
                        ),
                        keyboardOptions = KeyboardOptions.Default.copy(
                            keyboardType = KeyboardType.Text,
                            imeAction = ImeAction.Next
                        ),
                        keyboardActions = KeyboardActions(onNext = { focusManager.moveFocus(FocusDirection.Down) }),
                        modifier = Modifier
                            .fillMaxWidth()
//                            .bringIntoViewRequester(bringIntoViewRequester)
//                            .onFocusEvent { focusState ->
//                                if (focusState.isFocused) {
//                                    coroutineScope.launch {
//                                        bringIntoViewRequester.bringIntoView()
//                                    }
//                                }
//                            }
                    )

                    Row(
                        verticalAlignment = Alignment.Bottom,
                        modifier = Modifier
                            .padding(0.dp, 4.dp)
                            .fillMaxWidth()
                    ) {
                        Text(
                            text = "Card Number",
                            modifier = Modifier.padding(0.dp, 0.dp, 176.dp, 0.dp)
                        )
                        Image(
                            painter = painterResource(id = R.drawable.visa_credit_card),
                            contentDescription = null,
                            modifier = Modifier
                                .size(36.dp, 28.dp)
                                .padding(0.dp, 0.dp, 2.dp, 0.dp)
                        )
                        Image(
                            painter = painterResource(id = R.drawable.mastercard),
                            contentDescription = null,
                            modifier = Modifier.size(36.dp, 28.dp)
                        )
                    }
                    OutlinedTextField(
                        value = cardNumberInput,
                        onValueChange = { cardNumberInput = it },
                        placeholder = { Text(text = " **** **** **** *108 ") },
                        shape = RoundedCornerShape(12.dp),
                        colors = TextFieldDefaults.outlinedTextFieldColors(
                            backgroundColor = Color.White,
                            cursorColor = colorResource(id = R.color.colorPrimary),
                            focusedBorderColor = colorResource(id = R.color.colorPrimary)
                        ),
                        keyboardOptions = KeyboardOptions.Default.copy(
                            keyboardType = KeyboardType.Number,
                            imeAction = ImeAction.Next
                        ),
                        keyboardActions = KeyboardActions(onNext = { focusManager.clearFocus() }),
                        modifier = Modifier
                            .fillMaxWidth()
//                            .bringIntoViewRequester(bringIntoViewRequester)
//                            .onFocusEvent { focusState ->
//                                if (focusState.isFocused) {
//                                    coroutineScope.launch {
//                                        bringIntoViewRequester.bringIntoView()
//                                    }
//                                }
//                            }
                    )
                    Text(
                        text = "Expiry Date",
                        modifier = Modifier.padding(0.dp, 8.dp)
                    )
                    Row {
                        OutlinedTextField(
                            value =  selectedMonth,
                            onValueChange = { selectedMonth = it},
                            readOnly = true,
                            shape = RoundedCornerShape(12.dp),
                            colors = TextFieldDefaults.outlinedTextFieldColors(
                                backgroundColor = Color.White,
                                cursorColor = colorResource(id = R.color.colorPrimary),
                                focusedBorderColor = colorResource(id = R.color.colorPrimary)
                            ),
                            trailingIcon = {
                                Icon(iconM,"contentDescription",
                                    Modifier.clickable { mExpanded = !mExpanded })
                            },
                            modifier = Modifier
                                .width(135.dp)
                                .padding(0.dp,0.dp,10.dp,0.dp)
//                            .bringIntoViewRequester(bringIntoViewRequester)
//                            .onFocusEvent { focusState ->
//                                if (focusState.isFocused) {
//                                    coroutineScope.launch {
//                                        bringIntoViewRequester.bringIntoView()
//                                    }
//                                }
//                            }
                        )
                        DropdownMenu(
                            expanded = mExpanded,
                            onDismissRequest = { mExpanded = false },
                        ) {
                            months.forEach { label ->
                                DropdownMenuItem(onClick = {
                                    selectedMonth = label
                                    mExpanded = false
                                }) {
                                    Text(text = label)
                                }
                            }
                        }

                        OutlinedTextField(
                            value = selectedYear,
                            onValueChange = {selectedYear = it},
                            placeholder = { Text(text = " 2022 ") },
                            readOnly = true,
                            shape = RoundedCornerShape(12.dp),
                            colors = TextFieldDefaults.outlinedTextFieldColors(
                                backgroundColor = Color.White,
                                cursorColor = colorResource(id = R.color.colorPrimary),
                                focusedBorderColor = colorResource(id = R.color.colorPrimary)
                            ),
                            trailingIcon = {
                                Icon(iconY,"contentDescription",
                                    Modifier.clickable { yExpanded = !yExpanded })
                            },
                            modifier = Modifier
                                .width(125.dp)
//                            .bringIntoViewRequester(bringIntoViewRequester)
//                            .onFocusEvent { focusState ->
//                                if (focusState.isFocused) {
//                                    coroutineScope.launch {
//                                        bringIntoViewRequester.bringIntoView()
//                                    }
//                                }
//                            }
                        )
                    }
                    DropdownMenu(
                        expanded = yExpanded,
                        onDismissRequest = { yExpanded = false },
                    ) {
                        years.forEach { label ->
                            DropdownMenuItem(onClick = {
                                selectedYear = label
                                yExpanded = false
                            }) {
                                Text(text = label)
                            }
                        }
                    }

                    Text(
                        text = "Security Code",
                        modifier = Modifier.padding(0.dp, 8.dp)
                    )
                    OutlinedTextField(
                        value = cvvInput,
                        onValueChange = { cvvInput = it },
                        placeholder = { Text(text = "CVV") },
                        shape = RoundedCornerShape(12.dp),
                        colors = TextFieldDefaults.outlinedTextFieldColors(
                            backgroundColor = Color.White,
                            cursorColor = colorResource(id = R.color.colorPrimary),
                            focusedBorderColor = colorResource(id = R.color.colorPrimary)
                        ),
                        keyboardOptions = KeyboardOptions.Default.copy(
                            keyboardType = KeyboardType.Number,
                            imeAction = ImeAction.Done
                        ),
                        keyboardActions = KeyboardActions(onDone = { focusManager.clearFocus() }),
                        modifier = Modifier
                            .width(125.dp)
//                            .bringIntoViewRequester(bringIntoViewRequester)
//                            .onFocusEvent { focusState ->
//                                if (focusState.isFocused) {
//                                    coroutineScope.launch {
//                                        bringIntoViewRequester.bringIntoView()
//                                    }
//                                }
//                            }
                    )
                }
            }

            Row(
                modifier = Modifier.padding(0.dp, 8.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = "Pay at the Delivery",
                    fontSize = 24.sp,
                    modifier = Modifier.padding(0.dp, 0.dp, 144.dp, 0.dp)
                )
                RadioButton(
                    selected = selectedModeOfPayment.value == payOnDelivery,
                    onClick = { selectedModeOfPayment.value = payOnDelivery },
                    colors = RadioButtonDefaults.colors(selectedColor = colorResource(R.color.colorPrimary))
                )
            }
            Spacer(modifier = Modifier.height(32.dp))
            Button(
                onClick = {},
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(12.dp, 0.dp)
                    .clip(RoundedCornerShape(16.dp)),
                colors = ButtonDefaults.buttonColors(backgroundColor = colorResource(id = R.color.colorPrimary))
            ) {
                Text(
                    text = "Pay Now",
                    color = Color.White,
                    modifier = Modifier.padding(8.dp),
                )
                Icon(
                    painter = painterResource(R.drawable.ic_baseline_arrow_forward_24),
                    contentDescription = "forward Arrow",
                    tint = Color.White
                )
            }
        }
    }
}