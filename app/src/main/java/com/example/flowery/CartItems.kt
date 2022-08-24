package com.example.flowery

import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.*
import androidx.compose.runtime.Composable
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
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.flowery.ui.theme.FloweryTheme

class CartItems : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            FloweryTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background
                ) {
                    FlowerCartItemsScreen()
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun CartItemsPreview() {
    FloweryTheme {
        FlowerCartItemsScreen()
    }
}

@Composable
fun FlowerCartItemsScreen() {
    Scaffold(
        backgroundColor = colorResource(R.color.colorPrimary),
        topBar = { CartItemsTopBar() },
        content = { FlowerCartItemsScreenContent() },
        bottomBar = { FlowerBottomAppBar() },
    )
}

@Composable
fun CartItemsTopBar() {
    TopAppBar(
        contentColor = Color.White,
        backgroundColor = colorResource(R.color.colorPrimary),
        elevation = 0.dp,
        title = {
            Text(
                text = stringResource(R.string.cart_items),
                modifier = Modifier.fillMaxWidth(),
                textAlign = TextAlign.Center,
                fontSize = 24.sp,
            )
        }
    )
}

@Composable
fun FlowerCartItemsScreenContent() {
    val context = LocalContext.current
    val focusManager = LocalFocusManager.current
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
            Items()
            Items()
            Text(
                text = "Apply Coupon",
                fontSize = 20.sp,
            )
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(0.dp, 16.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                OutlinedTextField(
                    value = "",
                    onValueChange = {},
                    modifier = Modifier.width(220.dp),
                    placeholder = { Text(text = "Enter Code") },
                    shape = RoundedCornerShape(12.dp),
                    colors = TextFieldDefaults.outlinedTextFieldColors(
                        backgroundColor = Color.White,
                        cursorColor = colorResource(id = R.color.colorPrimary),
                        focusedBorderColor = colorResource(id = R.color.colorPrimary)
                    ),
                    keyboardOptions = KeyboardOptions.Default.copy(
                        keyboardType = KeyboardType.Text,
                        imeAction = ImeAction.Done
                    ),
                    keyboardActions = KeyboardActions(onDone = { focusManager.clearFocus() })
                )
                Button(
                    onClick = {},
                    modifier = Modifier
                        .width(150.dp)
                        .padding(20.dp, 0.dp, 0.dp, 0.dp)
                        .clip(RoundedCornerShape(10.dp)),
                    colors = ButtonDefaults.buttonColors(backgroundColor = colorResource(id = R.color.colorPrimary))
                ) {
                    Text(
                        text = "Apply",
                        color = Color.White,
                        modifier = Modifier.padding(8.dp),
                    )
                }
            }
            PriceDetails()
            Spacer(modifier = Modifier.height(50.dp))
        }
    }
}

@Composable
fun Items() {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(0.dp, 0.dp, 0.dp, 16.dp),
        shape = RoundedCornerShape(20.dp)
    ) {
        Row(
            modifier = Modifier.padding(10.dp)
        ) {
            Image(
                painter = painterResource(id = R.drawable.ic_pink_rose_bouquet),
                contentDescription = "null",
                modifier = Modifier.size(100.dp)
            )
            Column {
                Text(
                    text = "Angle Flower Bouquet",
                    fontSize = 20.sp,
                )
                Text(
                    text = "$599.00",
                    fontSize = 20.sp,
                    color = colorResource(id = R.color.colorPrimary)
                )
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(
                        text = "Quantity : ",
                        modifier = Modifier.padding(0.dp, 0.dp, 36.dp, 0.dp),
                        fontSize = 20.sp,
                        color = Color.Gray
                    )
                    CartInDButton()
                }
            }
        }
    }
}

@Composable
fun CartInDButton() {
    Row(
        modifier = Modifier
            .size(116.dp, 36.dp)
            .background(
                colorResource(id = R.color.offWhite),
                RoundedCornerShape(8.dp)
            ),
        horizontalArrangement = Arrangement.Center,
    ) {
//        Card(
//            backgroundColor = colorResource(id = R.color.colorPrimary),
//            modifier = Modifier.size(24.dp)
//                .clip( RoundedCornerShape(8.dp))
//            ) {
            IconButton(onClick = { /*TODO*/ }) {
                Icon(
                    painter = painterResource(R.drawable.ic_baseline_remove_24),
                    contentDescription = "Remove",
                    tint = colorResource(id = R.color.offWhite),
                    modifier = Modifier
                        .background(
                            colorResource(id = R.color.colorPrimary),
                            RoundedCornerShape(6.dp)
                        )
                )
            }
//        }
        Text(
            text = "1",
            modifier = Modifier.size(28.dp, 24.dp),
            textAlign = TextAlign.Center,
            fontWeight = FontWeight.Bold,
            fontSize = 24.sp,
            color = colorResource(id = R.color.colorPrimary)
        )
        IconButton(onClick = { /*TODO*/ }) {
            Icon(
                painter = painterResource(R.drawable.ic_baseline_add_24),
                contentDescription = "Add",
                tint = colorResource(id = R.color.offWhite),
                modifier = Modifier
                    .background(
                        colorResource(id = R.color.colorPrimary),
                        RoundedCornerShape(6.dp)
                    )

            )
        }
    }
}


@Composable
fun PriceDetails() {
    val context = LocalContext.current
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(0.dp, 0.dp, 0.dp, 24.dp),
        shape = RoundedCornerShape(20.dp)
    ) {
        Column(
            modifier = Modifier.padding(10.dp)
        ) {
            Text(
                text = "Price Details",
                fontSize = 20.sp,
                modifier = Modifier.padding(2.dp, 16.dp)
            )
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(2.dp, 8.dp)
            ) {
                Text(
                    text = "Angle Flower Bouquet",
                    textAlign = TextAlign.Start,
                    modifier = Modifier.weight(1f)
                )
                Text(
                    text = "1X$567",
                    textAlign = TextAlign.End,
                    modifier = Modifier.weight(1f)
                )
            }
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(2.dp, 8.dp)
            ) {
                Text(
                    text = "Rose Flower Bouquet",
                    textAlign = TextAlign.Start,
                    modifier = Modifier.weight(1f)
                )
                Text(
                    text = "1X$567",
                    textAlign = TextAlign.End,
                    modifier = Modifier.weight(1f)
                )
            }
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(2.dp, 8.dp)
            ) {
                Text(
                    text = "Delivery Charge",
                    textAlign = TextAlign.Start,
                    modifier = Modifier.weight(1f)
                )
                Text(
                    text = "$50",
                    textAlign = TextAlign.End,
                    modifier = Modifier.weight(1f)
                )
            }
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(2.dp, 8.dp)
            ) {
                Text(
                    text = "Coupon Discount",
                    textAlign = TextAlign.Start,
                    modifier = Modifier.weight(1f)
                )
                Text(
                    text = "$56",
                    textAlign = TextAlign.End,
                    modifier = Modifier.weight(1f)
                )
            }
            Divider(modifier = Modifier.padding(0.dp, 4.dp))
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(2.dp, 8.dp)
            ) {
                Text(
                    text = "Total Amount Payable ",
                    textAlign = TextAlign.Start,
                    modifier = Modifier.weight(1f),
                    fontWeight = FontWeight.Bold,
                )
                Text(
                    text = "$1000",
                    textAlign = TextAlign.End,
                    modifier = Modifier.weight(1f),
                    fontWeight = FontWeight.Bold,
                    color = colorResource(id = R.color.colorPrimary)
                )
            }
            Button(
                onClick = {
                    context.startActivity(Intent(context, CheckoutScreen::class.java))

                },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(0.dp, 28.dp, 0.dp, 0.dp)
                    .clip(RoundedCornerShape(16.dp)),
                colors = ButtonDefaults.buttonColors(backgroundColor = colorResource(id = R.color.colorPrimary))
            ) {
                Text(
                    text = "Checkout",
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

