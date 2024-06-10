package com.example.instragramclone.main

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavController
import com.example.instragramclone.IgViewModel

@Composable
fun MyPostsScreen(navController: NavController, vm:IgViewModel){
    Column(modifier = Modifier.fillMaxSize()) {
        Column(Modifier.weight(1f)) {
            Text(text = "MyPosts")
        }
        BottomNavigationMenu(
            selectedItem = BottomNavigationItem.POSTS,
            navController = navController
        )
    }
}