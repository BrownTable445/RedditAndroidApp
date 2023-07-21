package com.example.reddit.ui.theme.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Share
import androidx.compose.material3.Card
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.example.reddit.data.SubRedditPost
import com.example.reddit.ui.theme.RedditTheme
import com.example.reddit.R

@Composable
fun SubRedditPostBlock(
    subRedditPost: SubRedditPost,
    modifier: Modifier = Modifier
) {
    Column(Modifier.background(Color.Black)) {
        Column(
            Modifier
                .fillMaxWidth()
                .padding(vertical = 10.dp),
        ) {
            Row {
                AsyncImage(
                    model = ImageRequest.Builder(LocalContext.current)
                        .data(subRedditPost.iconURL)
                        .crossfade(true)
                        .build(),
                    contentScale = ContentScale.Crop,
                    modifier = Modifier
                        .clip(CircleShape)
                        .size(40.dp),
                    contentDescription = ""
                )
                Column(
                    modifier = Modifier
                        .align(Alignment.CenterVertically)
                        .padding(start = 10.dp)
                ) {
                    Text(
                        text = "r/" + subRedditPost.subRedditName,
                        color = Color.White,
                        fontSize = 15.sp
                    )
                }
            }
            Text(
                text = subRedditPost.postName,
                modifier = Modifier.padding(top = 5.dp, bottom = 5.dp),
                fontSize = 20.sp,
                color = Color.White
            )
            Text(
                text = subRedditPost.description,
                fontSize = 13.sp,
                lineHeight = 15.sp,
                color = Color.White,
                maxLines = 4,
                overflow = TextOverflow.Ellipsis,
            )
            if (subRedditPost.imageUrl != null) {
                Card {
                    AsyncImage(
                        model = ImageRequest.Builder(LocalContext.current)
                            .data(subRedditPost.imageUrl)
                            .crossfade(true)
                            .build(),
                        contentScale = ContentScale.Crop,
                        modifier = Modifier
                            .align(Alignment.CenterHorizontally)
                            .clip(RectangleShape)
                            .fillMaxWidth()
                            .heightIn(min = 0.dp, max = 600.dp),
                        contentDescription = ""
                    )
                }
            }
            SubRedditPostBottom(netUpVotes = subRedditPost.netVoteCount)
        }
    }
}

@Preview(showBackground = true)
@Composable
fun subRedditPostPreview() {
    RedditTheme {
        val subRedditPost = SubRedditPost(postName = "Why are adults so hooked on alcohol?",
            description = "I'm looking to have a LazyColumn that initially wraps content height, but if it gets to a certain height (232dp) then it fixes its height at this. In XML I would have used maxHeight combined with wrapContent, but I cant find anything similar in Compose?123",
            iconURL = "https://images.dog.ceo//breeds//sheepdog-shetland//n02105855_7846.jpg",
            subRedditName = "NoStupidQuestions",
            id = 123,
            imageUrl = null,
            netVoteCount = 5
        )
        SubRedditPostBlock(
            subRedditPost
        )
    }
}

@Preview(showBackground = true)
@Composable
fun subRedditPostPreview_withImage() {
    RedditTheme {
        val subRedditPost = SubRedditPost(postName = "Scooter Stolen Please Help",
            description = "",
            iconURL = "https://styles.redditmedia.com/t5_2rcax/styles/communityIcon_7bo27nux4ve71.jpg?format=pjpg&s=f554ab6449c1c943d671a360007ab8ae2310eb2e",
            subRedditName = "UCDavis",
            id = 123,
            imageUrl = "https://preview.redd.it/gsu5phsxr6db1.jpg?width=741&format=pjpg&auto=webp&s=7a1f140509aba382ef451a4cd5d996f26d24ac7b",
            netVoteCount = 9876
        )
        SubRedditPostBlock(
            subRedditPost
        )
    }
}

@Preview()
@Composable
fun SubRedditPostBottomPreview() {
    RedditTheme() {
        SubRedditPostBottom(netUpVotes = 3700)
    }
}

@Composable
fun SubRedditPostBottom(netUpVotes: Int) {
    Row(
        Modifier.fillMaxWidth()
            .height(20.dp)
    ) {
        var upVoted by remember { mutableStateOf(false) }
        var downVoted by remember { mutableStateOf(false) }
        IconButton(
            onClick = {
                upVoted = !upVoted
                downVoted = false
            },
            modifier = Modifier.padding(start = 10.dp)
        ) {
            Icon(
                painter = painterResource(id = R.drawable.arrow_upward_48px),
                contentDescription = null,
                tint = if (upVoted) Color(0xFFFFA500) else Color.White,
            )
        }
        Text(
            text = if (netUpVotes > 1000) (netUpVotes / 1000.0).toString() + "k" else netUpVotes.toString(),
            color = Color.White,
            fontSize = 15.sp,
            modifier = Modifier.align(Alignment.CenterVertically)
        )
        IconButton(
            onClick = {
                downVoted = !downVoted
                upVoted = false;
            },
            modifier = Modifier.padding(end = 40.dp)
        ) {
            Icon(
                painter = painterResource(id = R.drawable.arrow_downward_48px),
                contentDescription = null,
                tint = if (downVoted) Color(0xFFFFA500) else Color.White,
            )
        }
        Icon(
            painter = painterResource(id = R.drawable.chat_bubble_48px),
            contentDescription = null,
            tint = Color.White,
            modifier = Modifier.padding(end = 40.dp)
        )
        Icon(
            imageVector = Icons.Outlined.Share,
            contentDescription = null,
            tint = Color.White,
            modifier = Modifier.size(48.dp)
        )
    }
}