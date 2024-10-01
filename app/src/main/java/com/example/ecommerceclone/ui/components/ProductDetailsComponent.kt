package com.example.ecommerceclone.ui.components

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.ShoppingBag
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.example.ecommerceclone.data.models.ProductItem
import com.example.ecommerceclone.ui.theme.Backgroundcolor
import com.example.ecommerceclone.ui.theme.BorderColor
import com.example.ecommerceclone.ui.theme.GreenColor
import com.example.ecommerceclone.ui.theme.LightBlueColor
import com.example.ecommerceclone.ui.theme.Primary
import com.example.ecommerceclone.ui.theme.RedColor
import com.example.ecommerceclone.ui.theme.Typography

@Composable
fun BottomButtons(modifier: Modifier = Modifier, addToWishList: () -> Unit, addToBag: () -> Unit) {
    Row(
        modifier = modifier,
        horizontalArrangement = Arrangement.SpaceEvenly
    ) {

        Box(
            modifier = Modifier
                .width(170.dp)
                .height(50.dp)
                .border(BorderStroke(1.dp, BorderColor), RoundedCornerShape(8.dp))
                .clickable {
                    addToWishList()
                },
            contentAlignment = Alignment.Center
        ) {
            Row(verticalAlignment = Alignment.CenterVertically) {
                Icon(
                    imageVector = Icons.Filled.Favorite,
                    contentDescription = "Add to Wishlist"
                )

                Text(
                    text = "Wishlist", style = Typography.titleSmall,
                    modifier = Modifier
                        .padding(start = 5.dp)
                )
            }
        }
        Spacer(modifier = Modifier.width(5.dp))

        Box(
            modifier = Modifier
                .width(170.dp)
                .height(50.dp)
                .background(RedColor, shape = RoundedCornerShape(8.dp))
                .border(BorderStroke(1.dp, BorderColor), RoundedCornerShape(8.dp))
                .clickable {
                    addToBag()
                },
            contentAlignment = Alignment.Center
        ) {
            Row(verticalAlignment = Alignment.CenterVertically) {
                Icon(
                    imageVector = Icons.Filled.ShoppingBag,
                    contentDescription = "Add to Bag",
                    tint = Color.White,
                )

                Text(
                    text = "Add to Bag", style = Typography.titleSmall,
                    color = Color.White,
                    modifier = Modifier
                        .padding(start = 5.dp)
                )
            }
        }
    }
}

@Composable
fun ProductImageSection(productItem: ProductItem) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .height(400.dp)
    ) {
        AsyncImage(
            model = productItem.image,
            contentDescription = productItem.title,
            modifier = Modifier.fillMaxSize(),
            contentScale = ContentScale.Fit,
        )

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .align(Alignment.BottomEnd)
                .padding(10.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.End
        ) {
            Text(
                text = "${productItem.rating?.rate} ⭐ | ${productItem.rating?.count}k",
                color = Color.White,
                modifier = Modifier
                    .clip(RoundedCornerShape(8.dp))
                    .background(RedColor)
                    .padding(8.dp)
            )
        }
    }
}

@Composable
fun ProductDetailsSection(productItem: ProductItem) {
    Column {

        Row(modifier = Modifier
            .fillMaxWidth()
            .padding(10.dp),
            horizontalArrangement = Arrangement.SpaceBetween) {
            Text(
                text = "Price : ${productItem.price} $",
                style = Typography.titleSmall
            )

            Text(
                text = "Category : ${productItem.category}",
                style = Typography.titleSmall
            )
        }

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(10.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = "${productItem.description}",
                style = Typography.labelMedium
            )
        }

    }
}