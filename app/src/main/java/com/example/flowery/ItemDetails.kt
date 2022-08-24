package com.example.flowery

import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.flowery.Datas.FlowerData
import com.example.flowery.ui.theme.FloweryTheme
import com.google.accompanist.pager.ExperimentalPagerApi
import com.google.accompanist.pager.HorizontalPager
import com.google.accompanist.pager.HorizontalPagerIndicator
import com.google.accompanist.pager.rememberPagerState

class ItemDetails : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            FloweryTheme {

//                  1
//                window?.setStatusBarColor(Color.Yellow.toArgb())

//                 2
//                val systemUiController = rememberSystemUiController()
//                val useDarkIcons = MaterialTheme.colors.isLight
//                SideEffect {
//                    systemUiController.setStatusBarColor(
//                        color = Color.Red,
//                        darkIcons = useDarkIcons
//                    )
//                }

//                  3
//                this.window.statusBarColor = ContextCompat.getColor(this,R.color.black)

                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background
                ) {
                    FlowerItemDetails()
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun ItemDetailsPreview() {
    FloweryTheme {
        FlowerItemDetails()
    }
}

@Composable
fun FlowerItemDetails() {
    Scaffold(
        backgroundColor = colorResource(R.color.lightGray),
        topBar = { ItemDetailsTopBar() },
        content = { ItemDetailsContent() }
    )
}

@Composable
fun ItemDetailsTopBar() {
    TopAppBar(
        contentColor = Color.Black,
        backgroundColor = colorResource(id = R.color.lightGray),
        elevation = 0.dp,
        navigationIcon = {
            IconButton(onClick = { }) {
                Icon(
                    painterResource(R.drawable.ic_baseline_arrow_back_ios_24), null
                )
            }
        },
        title = {}
    )
}

@Composable
fun ItemDetailsContent() {
    val context = LocalContext.current
    Box {
        FlowerImages()
        Column {
            Spacer(modifier = Modifier.height(280.dp))
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .background(
                        colorResource(R.color.colorPrimary),
                        RoundedCornerShape(topStart = 40.dp, topEnd = 40.dp)
                    )
            ) {
                Column(
                    modifier = Modifier.padding(20.dp)
                ) {
                    Spacer(modifier = Modifier.height(20.dp))


                    Text(
                        text = stringResource(R.string.jannien_bouquet),
                        fontSize = 24.sp,
                        color = Color.White
                    )
                    Text(
                        text = stringResource(R.string.jannien),
                        fontSize = 14.sp,
                        color = colorResource(R.color.Gray)
                    )
                    Row(
                        modifier = Modifier.padding(0.dp, 10.dp),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Text(
                            text = "$599",
                            fontSize = 24.sp,
                            color = Color.White
                        )
                        Spacer(modifier = Modifier.weight(.1f))
                        InDButton()
                    }
                    Divider(
                        modifier = Modifier
                            .padding(0.dp, 8.dp)
                            .height(1.dp)
                            .align(Alignment.CenterHorizontally),
                        color = colorResource(R.color.lightGray),
                    )
                    Text(
                        text = stringResource(R.string.about),
                        fontSize = 24.sp,
                        color = Color.White,
                        modifier = Modifier.padding(0.dp, 12.dp)
                    )
                    Text(
                        text = stringResource(R.string.lorem),
                        fontSize = 14.sp,
                        color = colorResource(R.color.Gray)
                    )
                    Spacer(modifier = Modifier.height(30.dp))
                    Button(
                        onClick = {
                            context.startActivity(Intent(context,CartItems::class.java))
                        },
                        modifier = Modifier
                            .fillMaxWidth()
                            .clip(RoundedCornerShape(16.dp)),
                        colors = ButtonDefaults.buttonColors(backgroundColor = colorResource(id = R.color.lightGray))
                    ) {
                        Text(
                            text = "Go to Cart",
                            color = colorResource(id = R.color.colorPrimary),
                            modifier = Modifier.padding(10.dp,10.dp,0.dp,10.dp,),
                            textAlign = TextAlign.Center,
                            fontSize = 20.sp
                        )
                        Icon(
                            painterResource(id = R.drawable.ic_baseline_arrow_forward_24),
                            contentDescription = null,
                            modifier = Modifier.padding(0.dp,2.dp,0.dp,0.dp),
                            tint = colorResource(id = R.color.colorPrimary)
                        )
                    }
                }
            }
        }
        Box(
            modifier = Modifier
                .padding(0.dp, 254.dp, 40.dp, 0.dp)
                .align(Alignment.TopEnd)
        ) {
            IconButton(
                onClick = { /*TODO*/ },
                modifier = Modifier
                    .size(50.dp)
                    .background(Color.White, RoundedCornerShape(50))
            ) {
                Icon(
                    painter = painterResource(id = R.drawable.ic_baseline_favorite_24),
                    contentDescription = null,
                    tint = colorResource(id = R.color.Gray20)
                )
            }
        }
    }
}

@OptIn(ExperimentalPagerApi::class)
@Composable
fun FlowerImages() {
    Column {
        val items = FlowerData().flowerLists()
        val pagerState = rememberPagerState()
        HorizontalPager(
            count = items.size,
            state = pagerState,
        ) { currentPage ->
            Image(
                painter = painterResource(items[currentPage].imageId),
                contentDescription = null,
                contentScale = ContentScale.FillBounds,
                modifier = Modifier
                    .size(240.dp)
                    .padding(20.dp)
            )
        }
        HorizontalPagerIndicator(
            pagerState = pagerState,
            modifier = Modifier
                .padding(0.dp, 4.dp, 0.dp, 28.dp)
                .align(Alignment.CenterHorizontally)
        )
    }
}

@Composable
fun InDButton() {
    Row(
        modifier = Modifier
            .size(120.dp, 36.dp)
            .background(
                colorResource(id = R.color.lightGreen),
                RoundedCornerShape(8.dp)
            ),
        horizontalArrangement = Arrangement.Center,
    ) {
        IconButton(onClick = { /*TODO*/ }) {
            Icon(
                painter = painterResource(R.drawable.ic_baseline_remove_24),
                contentDescription = "Remove",
                tint = colorResource(id = R.color.lightGreen),
                modifier = Modifier
                    .background(
                        Color.White,
                        RoundedCornerShape(6.dp)
                    )

            )
        }
        Text(
            text = "1",
            modifier = Modifier.size(28.dp, 24.dp),
            textAlign = TextAlign.Center,
            fontWeight = FontWeight.Bold,
            fontSize = 24.sp,
            color = Color.White
        )
        IconButton(onClick = { /*TODO*/ }) {
            Icon(
                painter = painterResource(R.drawable.ic_baseline_add_24),
                contentDescription = "Add",
                tint = colorResource(id = R.color.lightGreen),
                modifier = Modifier
                    .background(
                        Color.White,
                        RoundedCornerShape(6.dp)
                    )

            )
        }
    }
}
