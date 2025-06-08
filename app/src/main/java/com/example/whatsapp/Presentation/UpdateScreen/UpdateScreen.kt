package com.example.whatsapp.Presentation.UpdateScreen

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.example.whatsapp.Presentation.BottomNavigation.BottomNavigationBar
import com.example.whatsapp.R

@Composable
fun UpdateScreen(navHostController: NavHostController)
{
val scrollState = rememberScrollState()
    val sampleStatus = listOf(
        StatusData (image = R.drawable.disha_patani , name ="Disha Patani" , time = "10 min ago " ),
     StatusData (image = R.drawable.ashish_chanchalani , name ="Ashish Chanchalani" , time = "2 min ago " ),
     StatusData (image = R.drawable.carryminati , name ="Carry Minati" , time = " 5 min ago " ),
    )
     val sampleChannels = listOf(
          Channels ( image = R.drawable.neat_roots , name = "Neat Roots" , description = "Latest news in tech" ),
          Channels ( image = R.drawable. img , name = "Food Lover" , description = "Discover new recipes"),
          Channels ( image = R.drawable. meta , name = "Meta" , description = "Explore the World "),
     )
    Scaffold (
        floatingActionButton = {

            FloatingActionButton(onClick = { /*TODO*/ },
                containerColor = colorResource(id = R.color.light_Green),
                modifier = Modifier.size(65.dp),
                contentColor = Color.White
            ) {
                Icon(
                    painter = painterResource(id = R.drawable.baseline_photo_camera_24),
                    contentDescription = null)
            }
        },

               bottomBar = { BottomNavigationBar(navHostController, "UpdateScreen") },
        topBar = { TopBar() }
    ){
           Column(modifier = Modifier.padding(it).fillMaxWidth().verticalScroll(scrollState))
           {
              Text (text = "Status",
                  fontSize = 20.sp,
                  fontWeight = FontWeight.Bold,
                  color  = Color.Black ,modifier = Modifier.padding(horizontal = 12.dp, vertical = 8.dp))
               MyStatus()

//                   LazyColumn{
//
//                       items (sampleStatus){
//                           StatusItem (statusData = it)
//
//                       }
//                   }
               sampleStatus.forEach{

                   StatusItem (statusData = it)
               }
               Spacer(modifier = Modifier.height(16.dp))

               HorizontalDivider(
                   color=Color.Gray)

                Text (text = "Channel",
                    fontSize = 20.sp,
                    fontWeight = FontWeight.Bold,
                    color  = Color.Black ,
                    modifier = Modifier.padding(horizontal = 12.dp, vertical = 8.dp)
                )

                Spacer (modifier = Modifier.height(8.dp))
               Column (modifier = Modifier.padding(horizontal = 16.dp)) {
                    Text (text = "stay updated on topics that matter to you. Find channels to follow below ")

                   Spacer (modifier = Modifier.height(32.dp))

                   Text(text="Find channels to follow")
               }
                    Spacer (modifier = Modifier.height(16.dp))

                 sampleChannels.forEach{
                     ChannelItemDesign (channel = it)
                 }

               }
           }
    }
