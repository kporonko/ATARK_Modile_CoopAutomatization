package com.example.mobilecoopatark.components

import android.content.Context
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Icon
import androidx.compose.material.OutlinedButton
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Home
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.example.mobilecoopatark.dto.requests.AddCollectRequest
import com.example.mobilecoopatark.dto.requests.AddFeedingRequest
import com.example.mobilecoopatark.dto.responses.CoopSmallDesc
import com.example.mobilecoopatark.dto.responses.TempResponseItem
import com.example.mobilecoopatark.ktor.ApiService
import io.ktor.http.*
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter

@Composable
fun MainPage(navController: NavHostController, activity: ComponentActivity, profileId: Int){
    val isLoading: MutableState<Boolean> = remember { mutableStateOf(false) }
    var client = ApiService.create()
    val coroutineScope = rememberCoroutineScope()
    val coop1 = CoopSmallDesc(id = -1, name = "Aboba Coop", eggsByWeek = 23, thermometerApiKey = "", thermometerIp = "")

    val coopsList = remember {
        mutableStateOf(listOf(coop1))
    }
    coroutineScope.launch {
        val coops = client.getCoops(profileId)
        coopsList.value = coops
    }
    val context = LocalContext.current
    CoopsList(coops = coopsList.value, client, coroutineScope, context)
}

@Composable
fun CoopsList(coops: List<CoopSmallDesc>, client: ApiService, scope: CoroutineScope, context: Context){
    Column(
        modifier = Modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState())
            .padding(10.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .background(Color.Black),
            horizontalArrangement = Arrangement.Center) {
            Text(text = "Coops", textAlign = TextAlign.Center, color = Color.White, fontSize = 34.sp)
        }
        Row(
            modifier = Modifier
                .fillMaxWidth(),
            horizontalArrangement = Arrangement.Center) {
                Text(text = "Until next feeding: " + "TODO", textAlign = TextAlign.Center, color = Color.Black, fontSize = 34.sp)
            }
        coops.forEach { coop ->
            CoopCard(coop = coop, client, scope, context)
        }
    }
}

@Composable
fun CoopCard(coop: CoopSmallDesc, client : ApiService, coroutineScope: CoroutineScope, context: Context){

    var temp = TempResponseItem(created_at = "", entry_id = 1, field1 = "0")

    val tempList = remember {
        mutableStateOf(temp)
    }

    coroutineScope.launch {
        if(coop.id!=-1){
            tempList.value = client.getTemp(coop.thermometerIp, coop.thermometerApiKey).feeds[0]
        }
    }
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .wrapContentSize(Alignment.Center)
            .padding(top = 30.dp),
        horizontalArrangement = Arrangement.Center,
    ) {
        Box(
            modifier = Modifier
                .size(300.dp, 120.dp)
                .clip(RoundedCornerShape(20.dp))
                .background(Color.Gray)
                .padding(10.dp, 10.dp)
        ){
            Column(
                modifier = Modifier.fillMaxHeight(),
                horizontalAlignment = Alignment.Start,
                verticalArrangement = Arrangement.Center
            ) {
                Icon(imageVector = Icons.Rounded.Home, contentDescription = null, modifier = Modifier.size(70.dp))
            }
            Column(
                modifier = Modifier.fillMaxWidth(),
                horizontalAlignment = Alignment.End
            ) {
                Text(
                    text = coop.name,
                    color = Color.White,
                    fontWeight = FontWeight.Medium,
                    fontSize = 34.sp,
                    textAlign = TextAlign.Right,
                )
                Spacer(modifier = Modifier.size(5.dp))
                Text(
                    text = "Eggs Last Week: " + coop.eggsByWeek.toString(),
                    color = Color.White,
                    fontWeight = FontWeight.Medium,
                    fontSize = 16.sp,
                    textAlign = TextAlign.Right
                )
                Spacer(modifier = Modifier.size(5.dp))
                Text(
//                    text = "Temperature: " + "20.2C",
                    text = tempList.value.field1,
                    color = Color.White,
                    fontWeight = FontWeight.Medium,
                    fontSize = 16.sp,
                    textAlign = TextAlign.Right
                )
            }
        }
    }

    var isDialogShown = remember {
        mutableStateOf(false)
    }

    fun onPurchaseClick(){
        isDialogShown.value = true
    }

    fun onDismissDialog(){
        isDialogShown.value = false
    }


    var isDialogShownCollect = remember {
        mutableStateOf(false)
    }

    fun onPurchaseClickCollect(){
        isDialogShownCollect.value = true
    }

    fun onDismissDialogCollect(){
        isDialogShownCollect.value = false
    }
    Row(
        modifier = Modifier
            .size(300.dp, 50.dp),
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        OutlinedButton(
            onClick = {
                onPurchaseClick()
            }
        ) {
            Text(text = "Add Feeding")
        }
        OutlinedButton(
            onClick = {
                onPurchaseClickCollect()
            }
        ) {
            Text(text = "Collect Eggs")
        }
    }

    if (isDialogShown.value){
        CustomDialog(
            onDismiss = {
                onDismissDialog()
            },
            onConfirm = {
                coroutineScope.launch {
                    val date = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss'Z'")).toString()

                    val code = client.addFeeding(AddFeedingRequest(coopId = coop.id, feedingDate = date))
                    if (code == HttpStatusCode.Created){
                        val toast = Toast.makeText(context, "Created", 5000)
                        toast.show()
                        onDismissDialog()
                    }
                    else{
                        val toast = Toast.makeText(context, "Oops... Something went wrong", 5000)
                        toast.show()
                    }
                }
            },
            name = coop.name,
            id = coop.id
        )
    }
    if (isDialogShownCollect.value){
        CustomDialogCollect(
            onDismiss =
            {
                onDismissDialogCollect()
            },
            onConfirm =
            {
                coroutineScope.launch {
                    val date = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss'Z'")).toString()
                    val code = client.addCollect(AddCollectRequest(coopId = coop.id, collectDate = date, eggsCount = it.text.toInt()))
                    if (code == HttpStatusCode.Created){
                        val toast = Toast.makeText(context, "Created", 5000)
                        toast.show()
                        onDismissDialogCollect()
                    }
                    else{
                        val toast = Toast.makeText(context, "Oops... Something went wrong", 5000)
                        toast.show()
                    }
                }
            },
            name = coop.name,
            id = coop.id
        )
    }
}