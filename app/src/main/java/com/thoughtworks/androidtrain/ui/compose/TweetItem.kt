package com.thoughtworks.androidtrain.ui.compose

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import com.thoughtworks.androidtrain.model.entity.Tweet

@Composable
fun TweetItem(tweet: Tweet, onCommentSave: (String) -> Unit) {
    val isEditingComment = remember { mutableStateOf(false) }
    val commentText = remember { mutableStateOf("") }

    val isShowAvatarDialog = remember { mutableStateOf(false) }


    Row(modifier = Modifier.padding(16.dp)) {
        AsyncImage(
            model = tweet.sender?.avatar,
            contentDescription = null,
            modifier = Modifier.clip(CircleShape).size(48.dp)
                                .clickable{isShowAvatarDialog.value  = !isShowAvatarDialog.value
                                    },
            contentScale = ContentScale.Crop,
        )
        Column(modifier = Modifier.padding(start = 16.dp)) {
            Text(tweet.sender!!.nick,
                fontSize = 16.sp,
                modifier = Modifier.clickable{isEditingComment.value = !isEditingComment.value}
            )
            tweet.content?.let {
                Text(it,
                    fontSize = 14.sp,
                    modifier = Modifier.clickable{isEditingComment.value = !isEditingComment.value}
                ) }
            if (isEditingComment.value) {
                TextFieldWithButtons(
                        value = commentText.value,
                        onValueChange = { commentText.value = it },
                        onSave = {
                        onCommentSave(commentText.value)
                        isEditingComment.value = true
                    },
                        onCancel = {
                            isEditingComment.value = false}
                    )
            }
            if (isShowAvatarDialog.value) {
                DialogWithImage(
                    imageUrl = tweet.sender?.avatar,
                    onClose = { isShowAvatarDialog.value = !isShowAvatarDialog.value }
                )
            }
        }
    }
}

