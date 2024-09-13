package net.kisha.ui.dashboard



import android.annotation.SuppressLint
import android.app.ProgressDialog
import androidx.activity.compose.BackHandler
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.airbnb.lottie.compose.LottieAnimation
import com.airbnb.lottie.compose.LottieCompositionSpec
import com.airbnb.lottie.compose.animateLottieCompositionAsState
import com.airbnb.lottie.compose.rememberLottieComposition
import com.google.firebase.Firebase
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore

import com.google.firebase.firestore.SetOptions
import com.google.firebase.firestore.firestore
import net.kisha.R
import net.kisha.navigation.ROUTE_ADD_PRODUCT
import net.kisha.navigation.ROUTE_DASHBOARD
import net.kisha.navigation.ROUTE_HOME
import net.kisha.navigation.ROUTE_VIEW_PROD


private var progressDialog: ProgressDialog? = null
@OptIn(ExperimentalMaterial3Api::class)
@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
fun DashboardScreen(navController: NavHostController)  {

    var name by remember { mutableStateOf("") }
    val currentUser = FirebaseAuth.getInstance().currentUser
    val firestore = FirebaseFirestore.getInstance()
//    var User: User? by remember { mutableStateOf(null) }
    var isLoading by remember { mutableStateOf(true) }
//    var User by remember { mutableIntStateOf(0) }
    var currentPassword by remember { mutableStateOf("") }
    var newPassword by remember { mutableStateOf("") }
    var confirmPassword by remember { mutableStateOf("") }

    var loading by remember { mutableStateOf(false) }

    val firestores = Firebase.firestore


    val context = LocalContext.current

    BackHandler {
        navController.popBackStack()

    }


    // Fetch user details from Firestore
    LaunchedEffect(key1 = currentUser?.uid) {
        if (currentUser != null) {
            val userDocRef = firestore.collection("users").document(currentUser.uid)
            userDocRef.get()
                .addOnSuccessListener { document ->
                    if (document.exists()) {
//                        User = document.toObject<User>()
                    }
                    isLoading = false
                }
                .addOnFailureListener { e ->
                    // Handle failure
                    isLoading = false
                }
        }
    }

//    LaunchedEffect(Unit) {
//        firestores.collection("Students")
//            .get()
//            .addOnSuccessListener { result ->
//                studentCount = result.size()
//            }
//            .addOnFailureListener { exception ->
//                // Handle failures
//            }
//    }         android.R 000

    Scaffold(
        topBar = {
            CenterAlignedTopAppBar(
                title = {
                    Text(text = "Dashboard",fontSize = 30.sp, color = Color.White)
                },
                navigationIcon = {
                    androidx.compose.material3.IconButton(onClick = {
                        navController.navigate(ROUTE_HOME)
                    }) {
                        androidx.compose.material3.Icon(
                            Icons.AutoMirrored.Filled.ArrowBack,
                            "backIcon",
                            tint = Color.White
                        )
                    }
                },

                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = Color(0xFFFFB4AB),
                    titleContentColor = Color.White,

                    )

            )
        }, content = {
            Column(
                modifier = Modifier
                    .fillMaxSize()

            ) {
                val composition by rememberLottieComposition(spec = LottieCompositionSpec.RawRes(R.raw.cart))
                val progress by animateLottieCompositionAsState(composition)
                LottieAnimation(
                    composition, progress,
                    modifier = Modifier.size(300.dp)
                )
            }
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .background(Color(0xFFFFDAD6)),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally

            )
            {
//                Column(
//                    horizontalAlignment = Alignment.CenterHorizontally,
//                    verticalArrangement = Arrangement.Center
//                ) {
//
//                    Image(
//                        painter = painterResource(id = R.drawable.profile),
//                        contentDescription = null,
//                        modifier = Modifier
//                            .clickable {
//                                navController.navigate(ROUTE_PROFILE) {
//                                    popUpTo(ROUTE_DASHBOARD) { inclusive = true }
//                                }
//                            }
//                            .size(100.dp)
//                            .clip(RoundedCornerShape(10.dp))
//                    )
//                    androidx.compose.material3.Text(
//                        modifier = Modifier
//                        ,
//                        text = "Profile",
//                        textAlign = TextAlign.Center,
//                        fontSize = 20.sp,
//                        color = androidx.compose.material3.MaterialTheme.colorScheme.onSurface
//                    )
//                }
//                Spacer(modifier = Modifier.height(16.dp))
                Column(
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.Center
                ) {


                    Image(
                        painter = painterResource(id = R.drawable.viewprod),
                        contentDescription = null,
                        modifier = Modifier
                            .clickable {
                                navController.navigate(ROUTE_VIEW_PROD) {
                                    popUpTo(ROUTE_DASHBOARD) { inclusive = true }
                                }
                            }
                            .size(100.dp)
                            .clip(RoundedCornerShape(10.dp))
                    )
                    androidx.compose.material3.Text(
                        modifier = Modifier,
                        text = "View products",
                        textAlign = TextAlign.Center,
                        fontSize = 20.sp,
                        color = androidx.compose.material3.MaterialTheme.colorScheme.onSurface
                    )

                }
                Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center
            ) {

                Image(
                    painter = painterResource(id = R.drawable.addproduct),
                    contentDescription = null,
                    modifier = Modifier
                        .clickable {
                            navController.navigate(ROUTE_ADD_PRODUCT) {
                                popUpTo(ROUTE_HOME) { inclusive = true }
                            }
                        }
                        .size(100.dp)
                        .clip(RoundedCornerShape(10.dp))
                )
                androidx.compose.material3.Text(
                    modifier = Modifier
,
                    text = "Add products",
                    textAlign = TextAlign.Center,
                    fontSize = 20.sp,
                    color = androidx.compose.material3.MaterialTheme.colorScheme.onSurface
                )
            }




































            }

        })

}











@OptIn(ExperimentalMaterialApi::class)
@Composable
fun DashboardItem(item: DashboardItemData) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp),
        shape = RoundedCornerShape(8.dp),
        elevation = 8.dp,
        backgroundColor = Color.White,
        onClick = item.onClick
    ) {
        Row(
            modifier = Modifier
                .padding(16.dp)
                .fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Icon(
                imageVector = item.icon,
                contentDescription = "Dashboard Icon",
                tint = Color.Black,
                modifier = Modifier.size(36.dp)
            )
            Spacer(modifier = Modifier.width(16.dp))
            Text(
                text = item.title,
                style = MaterialTheme.typography.subtitle1,
                color = Color.Black
            )
            // Add a badge if the badge count is greater than 0
            if (item.badgeCount > 0) {
                Badge(count = item.badgeCount)
            }
        }
    }
}
@Composable
fun Badge(count: Int) {
    Box(
        modifier = Modifier
            .padding(start = 8.dp)
            .size(20.dp)
            .clip(CircleShape)
            .background(Color.Red),
        contentAlignment = Alignment.Center
    ) {
        Text(
            text = count.toString(),
            style = MaterialTheme.typography.caption,
            color = Color.White
        )
    }
}
data class DashboardItemData(
    val title: String,
    val icon: ImageVector,
    val badgeCount: Int,
    val onClick: () -> Unit
)
data class User(
    val userId: String = "",
    val school: String = "",
    val name: String = ""
)

fun saveUserDetails(user: User, param: (Any) -> Unit) {
    val firestore = FirebaseFirestore.getInstance()
    firestore.collection("users").document(user.userId)
        .set(user, SetOptions.merge())
        .addOnSuccessListener {

            progressDialog?.dismiss()
            // Success message or navigation
        }
        .addOnFailureListener {

            progressDialog?.dismiss()
            // Handle failure
        }
}
