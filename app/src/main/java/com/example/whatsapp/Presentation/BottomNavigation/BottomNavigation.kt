package com.example.whatsapp.Presentation.BottomNavigation

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.example.whatsapp.Presentation.Navigation.Routes
import com.example.whatsapp.R

@Composable
fun BottomNavigationBar(
    navController: NavHostController,
    currentRoute: String = ""
) {
    BottomAppBar(tonalElevation = 12.dp, contentColor = Color.Black) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            // Chats Tab
            Column(
                modifier = Modifier
                    .padding(horizontal = 16.dp)
                    .clickable {
                        navController.navigate(Routes.HomeScreen) {
                            popUpTo(navController.graph.startDestinationId) {
                                saveState = true
                            }
                            launchSingleTop = true
                            restoreState = true
                        }
                    },
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Icon(
                    painter = painterResource(id = R.drawable.message_4475881),
                    contentDescription = "Chats",
                    modifier = Modifier.size(28.dp),
                    tint = if (currentRoute.contains("HomeScreen")) 
                        colorResource(id = R.color.light_Green) else Color.Black
                )
                Spacer(modifier = Modifier.height(2.dp))
                Text(
                    text = "Chats",
                    fontWeight = FontWeight.Bold,
                    color = if (currentRoute.contains("HomeScreen")) 
                        colorResource(id = R.color.light_Green) else Color.Black
                )
            }

            // Updates Tab
            Column(
                modifier = Modifier
                    .padding(horizontal = 16.dp)
                    .clickable {
                        navController.navigate(Routes.UpdateScreen) {
                            popUpTo(navController.graph.startDestinationId) {
                                saveState = true
                            }
                            launchSingleTop = true
                            restoreState = true
                        }
                    },
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Icon(
                    painter = painterResource(id = R.drawable.update_icon),
                    contentDescription = "Updates",
                    modifier = Modifier.size(28.dp),
                    tint = if (currentRoute.contains("UpdateScreen")) 
                        colorResource(id = R.color.light_Green) else Color.Black
                )
                Spacer(modifier = Modifier.height(2.dp))
                Text(
                    text = "Updates",
                    fontWeight = FontWeight.Bold,
                    color = if (currentRoute.contains("UpdateScreen")) 
                        colorResource(id = R.color.light_Green) else Color.Black
                )
            }

            // Communities Tab
            Column(
                modifier = Modifier
                    .padding(horizontal = 16.dp)
                    .clickable {
                        navController.navigate(Routes.CommunityScreen) {
                            popUpTo(navController.graph.startDestinationId) {
                                saveState = true
                            }
                            launchSingleTop = true
                            restoreState = true
                        }
                    },
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Icon(
                    painter = painterResource(id = R.drawable.communities_icon),
                    contentDescription = "Communities",
                    modifier = Modifier.size(28.dp),
                    tint = if (currentRoute.contains("CommunityScreen")) 
                        colorResource(id = R.color.light_Green) else Color.Black
                )
                Spacer(modifier = Modifier.height(2.dp))
                Text(
                    text = "Communities",
                    fontWeight = FontWeight.Bold,
                    color = if (currentRoute.contains("CommunityScreen")) 
                        colorResource(id = R.color.light_Green) else Color.Black
                )
            }

            // Calls Tab
            Column(
                modifier = Modifier
                    .padding(horizontal = 16.dp)
                    .clickable {
                        navController.navigate(Routes.CallScreen) {
                            popUpTo(navController.graph.startDestinationId) {
                                saveState = true
                            }
                            launchSingleTop = true
                            restoreState = true
                        }
                    },
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Icon(
                    painter = painterResource(id = R.drawable.telephone),
                    contentDescription = "Calls",
                    modifier = Modifier.size(28.dp),
                    tint = if (currentRoute.contains("CallScreen")) 
                        colorResource(id = R.color.light_Green) else Color.Black
                )
                Spacer(modifier = Modifier.height(2.dp))
                Text(
                    text = "Calls",
                    fontWeight = FontWeight.Bold,
                    color = if (currentRoute.contains("CallScreen")) 
                        colorResource(id = R.color.light_Green) else Color.Black
                )
            }
        }
    }
}

