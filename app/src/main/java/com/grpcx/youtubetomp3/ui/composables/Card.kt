package com.grpcx.youtubetomp3.ui.composables

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.grpcx.youtubetomp3.R
import com.grpcx.youtubetomp3.data.Thumbnail

@Preview
@Composable
fun DefaultPreview() {

}

@Composable
fun DefaultCard(
    thumbnail: Thumbnail
) {
    
    Column(
        modifier = Modifier
            .background(Color()),
        verticalArrangement = Arrangement.spacedBy(14.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {

        AsyncImage(
            modifier = Modifier
                .fillMaxWidth()
                .height(156.dp)
                .clip(RoundedCornerShape(8.dp))
            ,
            contentScale = ContentScale.Inside,
            model = thumbnail.imageUrl,
            contentDescription = "thumbnail"
        )

        Text(text = thumbnail.videoTitle)

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .wrapContentHeight(),
            horizontalArrangement = Arrangement.spacedBy(32.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            TextIcon(
                iconModifier = Modifier.size(16.dp),
                text = "${thumbnail.videoViews} Views",
                icon = R.drawable.views)
            TextIcon(
                iconModifier = Modifier.size(16.dp),
                text = "${thumbnail.videoLikes} Likes",
                icon = R.drawable.likes)
        }

    }
    
}

