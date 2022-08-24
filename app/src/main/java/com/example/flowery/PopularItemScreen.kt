package com.example.flowery

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.flowery.Datas.FlowerData
import com.example.flowery.Models.FlowerModel
import com.example.flowery.ui.theme.FloweryTheme

class PopularItemScreen : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            FloweryTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background
                ) {
                    FlowerPopularItems()
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun FlowerPopularItemPreview() {
    FloweryTheme {
        FlowerPopularItems()
    }
}

@Composable
fun FlowerPopularItems() {
    Scaffold(
        backgroundColor = colorResource(R.color.colorPrimary),
        topBar = { PopularTopBar()},
        content = {
            Column(modifier = Modifier.fillMaxSize()
                .background(colorResource(R.color.lightGray), RoundedCornerShape(topStart = 40.dp, topEnd = 40.dp))
            ) {
                Column(modifier = Modifier.padding(10.dp)) {
                    FlowerGrid(flowerLists = FlowerData().flowerLists())
                }
            }
        }
    )
}

@Composable
fun PopularTopBar() {
    TopAppBar(
        contentColor = Color.White,
        backgroundColor = colorResource(R.color.colorPrimary),
        elevation = 0.dp,
        navigationIcon = {
            IconButton(onClick = {/* Do Something*/ }) {
                Icon(painterResource(R.drawable.ic_baseline_arrow_back_ios_24),null)
            }
        },
        title = {
            Text(
                text = stringResource(id = R.string.popular_items),
                modifier = Modifier.fillMaxWidth(),
                textAlign = TextAlign.Center,
                fontSize = 24.sp,
                fontStyle = FontStyle.Italic,
                fontFamily = FontFamily.SansSerif,
            )
        },
        actions = {
            IconButton(onClick = {/* Do Something*/ }) {
                Icon(painterResource(id = R.drawable.ic_baseline_search_24), null)
            }
        }
    )
}

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun FlowerGrid(flowerLists: List<FlowerModel>) {
    LazyVerticalGrid(cells = GridCells.Fixed(2)){
        items(flowerLists) { item ->
            LazyColumnItemCard(flower = item)
        }
    }
}

@Composable
fun LazyColumnItemCard(flower: FlowerModel) {
    Card(
        shape = RoundedCornerShape(16.dp),
        backgroundColor = Color.White,
        modifier = Modifier
            .padding(8.dp)
            .width(175.dp)
    ) {
        Column {
            Image(painter = painterResource(flower.imageId),
                contentDescription = stringResource(flower.imageNameId),
                modifier = Modifier
                    .size(150.dp)
                    .align(Alignment.CenterHorizontally)
            )
            Row(modifier = Modifier.padding(20.dp)) {
                Column(modifier = Modifier.weight(1f)) {
                    Text(
                        text = stringResource(flower.imageNameId),
                        fontSize = 16.sp
                    )
                    Text(
                        text = stringResource(flower.priceId),
                        fontSize = 16.sp
                    )
                }
                IconButton(
                    onClick = { },
                    modifier = Modifier.background(
                        colorResource(id = R.color.colorPrimary),
                        RoundedCornerShape(12.dp)
                    )
                ) {
                    Icon(Icons.Default.Add, contentDescription = "add", tint = Color.White)
                }
            }
        }
    }
}