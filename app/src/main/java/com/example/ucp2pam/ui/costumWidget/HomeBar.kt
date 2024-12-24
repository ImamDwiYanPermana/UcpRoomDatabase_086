package com.example.ucp2pam.ui.costumWidget

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun HomeBar(
    onBack: () -> Unit,
    showBackButton: Boolean = true,
    modifier: Modifier = Modifier
) {
    Box(
        modifier = modifier
            .fillMaxWidth()
            .background(
                brush = Brush.horizontalGradient(
                    colors = listOf(
                        Color.DarkGray, // Putih
                        Color(0xFF6366AE) // Ungu
                    )
                ),
                shape = RoundedCornerShape(bottomEnd = 60.dp)
            )
            .padding(horizontal = 22.dp, vertical = 26.dp)
    ) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            // Tulisan di kanan, rata kanan
            Column(
                modifier = Modifier
                    .weight(1f)
                    .padding(start = 16.dp)
                    .fillMaxWidth(), // Mengisi seluruh lebar
                horizontalAlignment = Alignment.End // Rata kanan
            ) {
                Text(
                    text = "UHHUYYYY",
                    color = Color.White,
                    fontWeight = FontWeight.Bold,
                    fontSize = 26.sp,
                    textAlign = TextAlign.End // Teks rata kanan
                )
                Text(
                    text = "AHHA",
                    color = Color.White.copy(alpha = 0.7f),
                    fontWeight = FontWeight.Normal,
                    fontSize = 16.sp,
                    textAlign = TextAlign.End // Teks rata kanan
                )
            }
        }

        // Tombol kembali (opsional)
        if (showBackButton) {
            IconButton(
                onClick = onBack,
                modifier = Modifier
                    .size(40.dp)
                    .clip(CircleShape)
                    .background(Color.White.copy(alpha = 0.15f))
                    .padding(4.dp)
            ) {
                Icon(
                    imageVector = Icons.Default.ArrowBack,
                    contentDescription = "Back",
                    tint = Color.White
                )
            }
        }
    }
}
