package com.example.ecommerceclone.ui.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ShoppingCart
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Alignment.Companion.CenterVertically
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.example.ecommerceclone.R
import com.example.ecommerceclone.data.models.ProductItem
import com.example.ecommerceclone.ui.theme.Typography

@Composable
fun ProductList(productList: List<ProductItem>,onAddToCartClicked: () -> Unit,
                productItemClicked:(ProductItem)->Unit,onTextChanged:(String)->Unit) {
    Column {
        ToolbarForApp(title = "Product List"){
            onTextChanged(it)
        }
        LazyVerticalGrid(
            columns = GridCells.Fixed(2),
            contentPadding = PaddingValues(2.dp),
            verticalArrangement = Arrangement.SpaceAround
        ) {
            items(productList) {
                ProductItem(productItem = it, onAddToCartClicked , productItemClicked)
            }
        }
    }
}


@Composable
fun ProductItem(productItem: ProductItem, onAddToCartClicked: () -> Unit,productItemClicked:(ProductItem)->Unit) {
    ElevatedCard(
        elevation = CardDefaults.cardElevation(
            defaultElevation = 6.dp
        ),
        modifier = Modifier
            .height(400.dp)
            .padding(5.dp)
            .clickable {
                productItemClicked.invoke(productItem)
            }
    ) {

        Box(modifier = Modifier.fillMaxSize()) {


            Column(
                modifier = Modifier
                    .padding(horizontal = 10.dp, vertical = 10.dp)
            ) {

                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(250.dp)
                        .clip(RoundedCornerShape(8.dp))
                ) {
                    AsyncImage(
                        model = productItem.image,
                        contentDescription = productItem.title,
                        modifier = Modifier
                            .fillMaxSize(), // Error image
                        contentScale = ContentScale.Fit,
                        placeholder = painterResource(id = R.drawable.profile),
                        error = painterResource(id = R.drawable.profile)
                    )
                }

                Spacer(modifier = Modifier.padding(10.dp))

                Row(modifier = Modifier.fillMaxWidth()) {
                    Text(
                        text = if (productItem.title!!.length > 30) productItem.title!!.take(30) else productItem.title,
                        style = Typography.titleSmall,
                        modifier = Modifier
                            .fillMaxWidth(0.8f)
                            .padding(5.dp)
                    )

                    Text(
                        text = "⭐ ${productItem.rating?.rate}",
                        style = Typography.labelMedium,
                        modifier = Modifier
                            .wrapContentSize()
                            .padding(5.dp)
                            .align(CenterVertically)
                    )
                }

                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .heightIn(50.dp),
                    verticalAlignment = CenterVertically
                ) {
                    // Text aligned to the start
                    Text(
                        text = "${productItem.price}",
                        style = Typography.labelMedium,
                        modifier = Modifier
                            .padding(5.dp)
                            .weight(1f)  // Use weight to take up available space
                    )

                    // Spacer to push the Image to the end
                    Spacer(modifier = Modifier.weight(1f))

                    // Image (Shopping Cart Icon) aligned to the end
                    Image(
                        imageVector = Icons.Filled.ShoppingCart,
                        contentDescription = "Shopping Cart",
                        modifier = Modifier
                            .size(30.dp)
                            .clickable {
                                onAddToCartClicked.invoke()
                            },
                        contentScale = ContentScale.Crop,
                        // Apply tint to the icon
                        colorFilter = androidx.compose.ui.graphics.ColorFilter.tint(MaterialTheme.colorScheme.primary)
                    )
                }
            }

        }

    }
}