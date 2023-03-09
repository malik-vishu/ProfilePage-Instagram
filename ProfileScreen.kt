package com.example.instadmscreen.screens

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Person
import androidx.compose.material.icons.rounded.ArrowBack
import androidx.compose.material.icons.rounded.ArrowDropDown
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.scale
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.instadmscreen.R.drawable
import com.example.instadmscreen.data.DataSource
import com.example.instadmscreen.data.ImageAndText
import com.example.instadmscreen.data.Posts


@Preview(showBackground = true)
@Composable
fun ProfileScreen() {
    var tabSelected by remember {
        mutableStateOf(0)
    }
    Column {
        ProfileTopBar()
        Surface(modifier = Modifier.fillMaxWidth()) {
            Row(verticalAlignment = Alignment.CenterVertically) {
                ProfileImage(
                    modifier = Modifier
                        .size(117.dp)
                        .padding(start = 7.dp)
                )
                Spacer(modifier = Modifier.width(20.dp))
                ColumnsPFF(number = "1200", tag = "Posts")
                ColumnsPFF(number = "200", tag = "Followers")
                ColumnsPFF(number = "100", tag = "Following")
            }

        }
        ProfileDetails()
        Surface(modifier = Modifier.fillMaxWidth()) {
            Row(modifier = Modifier.padding(start = 15.dp, top = 8.dp)) {
                ButtonsFME(alpha = 1f, text = "Follow", textColor = Color.White)
                Spacer(modifier = Modifier.width(7.dp))
                ButtonsFME(colorButton = Color(0xFFd5d5d5), text = "Message")
                Spacer(modifier = Modifier.width(7.dp))
                ButtonsFME(colorButton = Color(0xFFd5d5d5), text = "Email")
            }
        }
        HighLightRow()
        DifferentTabs{
            tabSelected = it
        }
        when (tabSelected) {
            0 -> PostSection()
            1 -> ReelsSection(text = "Upload some Reels")
            2 -> ReelsSection(text = "Upload some Videos")
        }

    }
}


//@Preview(showBackground = true)
@Composable
fun ProfileTopBar() {
    Column {
        TopAppBar(backgroundColor = Color.White, modifier = Modifier.fillMaxWidth()) {
            Row(
                horizontalArrangement = Arrangement.Start,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Icon(
                    modifier = Modifier.padding(3.dp),
                    imageVector = Icons.Rounded.ArrowBack,
                    contentDescription = "Back arrow",
                    tint = Color.Black
                )
                Spacer(modifier = Modifier.width(10.dp))
                Text(
                    text = "ironMan",
                    style = TextStyle(
                        fontWeight = FontWeight.Bold,
                        fontSize = MaterialTheme.typography.h5.fontSize
                    ),
                    textAlign = TextAlign.Center,
                    color = Color.Black
                )
                Icon(imageVector = Icons.Rounded.ArrowDropDown, contentDescription = "Drop down")
                Spacer(modifier = Modifier.width(160.dp))
                Surface(
                    modifier = Modifier
                        .height(25.dp)
                        .width(25.dp),
                    border = BorderStroke(width = 2.dp, color = Color.Black),
                    shape = RoundedCornerShape(20)
                ) {
                    Icon(
                        modifier = Modifier
                            .size(30.dp),
                        painter = painterResource(id = drawable.baseline_add_24),
                        contentDescription = "add",
                        tint = Color.Black
                    )
                }
                Spacer(modifier = Modifier.width(15.dp))
                Icon(
                    modifier = Modifier
                        .size(30.dp),
                    painter = painterResource(id = drawable.baseline_menu_24),
                    contentDescription = "video call",
                    tint = Color.Black
                )
            }


        }


    }

}

@Composable
private fun ColumnsPFF(modifier: Modifier = Modifier, number: String, tag: String) {
    Column(
        modifier = modifier.padding(start = 18.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(text = number, style = TextStyle(fontWeight = FontWeight.SemiBold, fontSize = MaterialTheme.typography.body1.fontSize))
        Text(text = tag, style = TextStyle(fontWeight = FontWeight.SemiBold,fontSize = MaterialTheme.typography.body1.fontSize))
    }
}

//@Preview(showBackground = true)
@Composable
fun ProfileDetails(modifier: Modifier = Modifier) {
    Surface(modifier = modifier.fillMaxWidth()) {
        Column(
            modifier = modifier.padding(start = 20.dp),
            verticalArrangement = Arrangement.Center
        ) {
            Text(text = "Iron Man", fontWeight = FontWeight.SemiBold)
            Text(text = "Rich")
            Text(text = "Businessman")
            Text(text = "more", color = Color.LightGray)
        }

    }
}


//@Preview(showBackground = true)
@Composable
fun ButtonsFME(
    alpha: Float = 0.8f,
    fontWeight: FontWeight = FontWeight.Bold,
    colorButton: Color = Color(0xFF0098fe),
    text: String = "Follow",
    textColor: Color = Color.Black
) {
    Button(
        modifier = Modifier
            .width(98.dp)
            .height(33.dp)
            .alpha(alpha)
            .clip(shape = RoundedCornerShape(30)),
        onClick = { /*TODO*/ },
        colors = ButtonDefaults.buttonColors(backgroundColor = colorButton)
    ) {
        Text(
            text = text,
            color = textColor,
            fontSize = MaterialTheme.typography.caption.fontSize,
            fontWeight = fontWeight
        )
    }
}

//@Preview(showBackground = true)
@Composable
fun HighLightRow(highlights: List<ImageAndText> = DataSource().loadHighlight()) {
    Surface(modifier = Modifier.fillMaxWidth()) {
        LazyRow(modifier = Modifier.padding(start = 15.dp,top = 5.dp)) {
            items(highlights.size) {
                Column(
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.Center,
                    modifier = Modifier
                        .padding(end = 14.dp, top = 2.dp)
                ) {
                    ProfileImage(
                        image = highlights[it].image,
                        modifier = Modifier.size(70.dp).border(1.dp, Color.LightGray,shape = CircleShape)
                    )
                    Text(
                        text = highlights[it].text,
                        textAlign = TextAlign.Center
                    )
                }
            }
        }
    }
}


@Composable
fun DifferentTabs(
    modifier: Modifier = Modifier,
    tabs: List<ImageAndText> = DataSource().differentTabs(),
    onTabSelected: (selectedIndex: Int) -> Unit
) {
    var selectedTabIndex by remember {
        mutableStateOf(0)
    }
    val inactiveColor = Color(0xFF777777)
    Surface{
        TabRow(
            selectedTabIndex = selectedTabIndex,
            backgroundColor = Color.Transparent,
            contentColor = Color.Black,
            modifier = modifier
        ) {
            tabs.forEachIndexed { index, item ->
                Tab(
                    selected = selectedTabIndex == index,
                    selectedContentColor = Color.Black,
                    unselectedContentColor = inactiveColor,
                    onClick = {
                        selectedTabIndex = index
                        onTabSelected(index)
                    }
                ) {
                    Icon(
                        painter = painterResource(id = item.image!!),
                        contentDescription = item.text,
                        tint = if (selectedTabIndex == index) Color.Black else inactiveColor,
                        modifier = Modifier
                            .padding(10.dp)
                            .size(20.dp)
                    )
                }
            }
        }
    }
}

//@Preview(showBackground = true)
@Composable
fun PostSection(
    modifier: Modifier = Modifier,
    posts: List<Posts> = DataSource().posts()
) {
    Surface(modifier = Modifier.fillMaxSize()) {
        LazyVerticalGrid(
            columns = GridCells.Fixed(3),
            modifier = modifier
                .scale(1.01f)
        ) {
            items(posts.size) {
                Image(
                    painter = painterResource(id = posts[it].post),
                    contentDescription = null,
                    contentScale = ContentScale.Crop,
                    modifier = Modifier
                        .aspectRatio(1f)
                        .border(
                            width = 1.dp,
                            color = Color.White
                        )
                )
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun ReelsSection(text: String? = null){
    Surface(modifier = Modifier.fillMaxSize()) {
        Column(horizontalAlignment = Alignment.CenterHorizontally, verticalArrangement = Arrangement.Center) {
            Image(imageVector = Icons.Outlined.Person, contentDescription = "Person Icon", modifier = Modifier.clip(shape = CircleShape).border(2.dp,Color.LightGray).size(80.dp), contentScale = ContentScale.Crop)
            Text(text = text!!, style = TextStyle(fontWeight = FontWeight.SemiBold, fontSize = MaterialTheme.typography.body2.fontSize))
        }
    }
}