package com.example.flowery

import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import androidx.compose.animation.core.FastOutSlowInEasing
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.ClickableText
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.flowery.Datas.BannerData
import com.example.flowery.Datas.FlowerData
import com.example.flowery.Models.FlowerModel
import com.example.flowery.ui.theme.FloweryTheme
import com.google.accompanist.pager.ExperimentalPagerApi
import com.google.accompanist.pager.HorizontalPager
import com.google.accompanist.pager.HorizontalPagerIndicator
import com.google.accompanist.pager.rememberPagerState
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class HomeScreen : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            FloweryTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background
                ) {
                    FlowerHomeScreen()
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun FlowerHomeScreenPreview() {
    FloweryTheme {
        FlowerHomeScreen()
    }
}

@Composable
fun FlowerHomeScreen() {
    Scaffold(
        backgroundColor = colorResource(id = R.color.colorPrimary),
        topBar = { FlowerTopBar() },
        content = { FlowerHomeScreenContent()},
        bottomBar = { FlowerBottomAppBar()}
    )
}

@Composable
fun FlowerTopBar() {
    TopAppBar(
        contentColor = Color.White,
        backgroundColor = colorResource(R.color.colorPrimary),
        elevation = 0.dp,
        title = {
            Text(
                text = stringResource(id = R.string.app_name),
                modifier = Modifier.fillMaxWidth(),
                textAlign = TextAlign.Center,
                fontSize = 24.sp,
                fontStyle = FontStyle.Italic,
                fontFamily = FontFamily.SansSerif,
            )
        },
        actions = {
            IconButton(onClick = {/* Do Something*/ }) {
                Icon(painter = painterResource(id = R.drawable.ic_baseline_search_24), null)
            }
        }
    )
}

@Composable
fun FlowerHomeScreenContent(){
    val context = LocalContext.current
    Column(
//        modifier = Modifier
//            .fillMaxSize()
//            .background(colorResource(R.color.colorPrimary))
    ) {

        Column(
            modifier = Modifier
                .fillMaxSize()
                .clip(RoundedCornerShape(topStart = 40.dp, topEnd = 40.dp))
                .background(colorResource(R.color.lightGray))
        ) {
            HorizontalBanner()
            Row(modifier = Modifier.padding(8.dp, 8.dp)) {
                ItemCard(R.drawable.ic_chinese_plum_flower, R.string.chinese_plum_flower)
                ItemCard(R.drawable.ic_flat_flower, R.string.flat_flower)
                ItemCard(R.drawable.ic_giftbox, R.string.giftBox)
                ItemCard(R.drawable.ic_wedding_arch, R.string.wedding_arch)
            }
            Row(modifier = Modifier.padding(12.dp, 16.dp)) {
                Text(
                    text = stringResource(R.string.popular_items),
                    fontSize = 24.sp
                )
                Spacer(modifier = Modifier.weight(.1f))
                ClickableText(
                    text = AnnotatedString(stringResource(R.string.view_all)),
                    style = TextStyle(color = colorResource(R.color.colorPrimary)),
                    onClick = {
                        context.startActivity(Intent(context, PopularItemScreen::class.java))
                    }
                )
            }
            FlowerList(FlowerData().flowerLists())
        }
    }

}

@OptIn(ExperimentalPagerApi::class)
@Composable
fun HorizontalBanner() {
    Column {
        val items = BannerData().bannerList()
        val pagerState = rememberPagerState()
        Card(
            modifier = Modifier
                .padding(15.dp)
                .height(175.dp)
                .clip(RoundedCornerShape(30.dp))

        ) {
            HorizontalPager(
                count = items.size,
                state = pagerState,
            )
            { currentPage ->
                Image(
                    painter = painterResource(items[currentPage].imageResourceId),
                    contentDescription = null,
                    contentScale = ContentScale.FillBounds,
                    modifier = Modifier
                        .fillMaxSize()
                        .clip(RoundedCornerShape(30.dp))


                )
            }
        }
        HorizontalPagerIndicator(
            pagerState = pagerState,
            modifier = Modifier
                .padding(0.dp, 8.dp)
                .align(Alignment.CenterHorizontally)

        )

        LaunchedEffect(key1 = pagerState.currentPage) {
            launch {
                delay(2000)
                with(pagerState) {
                    val target = if (currentPage < pageCount - 1) currentPage + 1 else 0

                    tween<Float>(
                        durationMillis = 500,
                        easing = FastOutSlowInEasing
                    )
                    animateScrollToPage(page = target)
                }
            }
        }

    }
}

@Composable
fun ItemCard(@DrawableRes painter: Int, @StringRes contentDescription: Int) {
    Column(modifier = Modifier.padding(6.dp, 0.dp)) {
        Card(
            modifier = Modifier
                .size(80.dp)
                .padding(4.dp, 2.dp)
                .clip(RoundedCornerShape(20.dp))
        ) {
            Image(
                painter = painterResource(painter),
                contentDescription = stringResource(contentDescription),
                modifier = Modifier.padding(20.dp)
            )
        }
    }

}

@Composable
fun FlowerList(flowerLists: List<FlowerModel>) {
    LazyRow {
        items(flowerLists) { item ->
            LazyRowItemCard(flower = item)
        }
    }
}

@Composable
fun LazyRowItemCard(flower: FlowerModel) {
    Card(
        shape = RoundedCornerShape(16.dp),
        backgroundColor = Color.White,
        modifier = Modifier
            .padding(8.dp)
            .width(175.dp)
    ) {
        Column {
            Image(
                painter = painterResource(flower.imageId),
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

@Composable
fun FlowerBottomAppBar() {
    BottomAppBar(
//        contentColor = colorResource(R.color.colorPrimary),
        backgroundColor = Color.White,
        modifier = Modifier
            .fillMaxWidth()
            .padding(12.dp,4.dp)
            .clip(RoundedCornerShape(20.dp))

    )  {
            BottomBarItem(R.drawable.ic_baseline_settings_24)
            BottomBarItem(R.drawable.ic_baseline_location_on_24)
            BottomBarItem(R.drawable.ic_baseline_shopping_bag_24)
            BottomBarItem(R.drawable.ic_baseline_person_24)

    }
}

@Composable
fun BottomBarItem(@DrawableRes painter: Int) {
    Column(modifier = Modifier.padding(10.dp, 0.dp)) {
        IconButton(onClick = {/* Do Something*/ }) {
            Icon(
                painter = painterResource(painter),
                contentDescription = "Settings",
                modifier = Modifier
                    .size(72.dp)
                    .padding(12.dp)
            )
        }
    }
}