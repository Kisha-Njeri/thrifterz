package net.kisha.ui.home






import android.annotation.SuppressLint
import android.content.Intent
import androidx.activity.compose.ManagedActivityResultLauncher
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.ActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.animation.core.Animatable
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
//noinspection UsingMaterialAndMaterial3Libraries
//noinspection UsingMaterialAndMaterial3Libraries
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.airbnb.lottie.compose.LottieAnimation
import com.airbnb.lottie.compose.LottieCompositionSpec
import com.airbnb.lottie.compose.animateLottieCompositionAsState
import com.airbnb.lottie.compose.rememberLottieComposition
import net.kisha.R
import net.kisha.navigation.ROUTE_HOME
import net.kisha.navigation.ROUTE_REGISTER
import net.kisha.navigation.ROUTE_VIEW_PROD


data class Screen(val title: String, val icon: Int)
@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter", "ResourceAsColor")
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen(navController: NavHostController) {

    var isDrawerOpen by remember { mutableStateOf(false) }

    val callLauncher: ManagedActivityResultLauncher<Intent, ActivityResult> =
        rememberLauncherForActivityResult(contract = ActivityResultContracts.StartActivityForResult()) { _ ->

        }
    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .background(color = Color(0xFFFFDAD6))
    ) {
        item {
            Column(
                modifier = Modifier
                    .fillMaxSize()

            ){
                val composition by rememberLottieComposition(spec = LottieCompositionSpec.RawRes(R.raw.cart))
                val progress by animateLottieCompositionAsState(composition)
                LottieAnimation(composition, progress,
                    modifier = Modifier.size(300.dp)
                )


                Column(
                    modifier = Modifier
                        .fillMaxSize()
                        .background(Color(0xFFFFDAD6)),
                    verticalArrangement = Arrangement.Center,
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                        Column(
                            horizontalAlignment = Alignment.CenterHorizontally,
                            verticalArrangement = Arrangement.Center
                        ) {

                                Image(
                                    painter = painterResource(id = R.drawable.signup),
                                    contentDescription = null,
                                    modifier = Modifier
                                        .clickable {
                                            navController.navigate(ROUTE_REGISTER) {
                                                popUpTo(ROUTE_HOME) { inclusive = true }
                                            }
                                        }
                                        .size(100.dp)
                                        .clip(RoundedCornerShape(10.dp))
                                )
                                Text(
                                    modifier = Modifier,
                                    text = "Sign up",
                                    textAlign = TextAlign.Center,
                                    fontSize = 20.sp,
                                    color = MaterialTheme.colorScheme.onSurface
                                )

                        }


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
                                                popUpTo(ROUTE_HOME) { inclusive = true }
                                            }
                                        }
                                        .size(100.dp)
                                        .clip(RoundedCornerShape(10.dp))
                                )
                                Text(
                                    modifier = Modifier
                                ,
                                    text = "View products",
                                    textAlign = TextAlign.Center,
                                    fontSize = 20.sp,
                                    color = MaterialTheme.colorScheme.onSurface
                                )

                        }
//                    Column(
//                        horizontalAlignment = Alignment.CenterHorizontally,
//                        verticalArrangement = Arrangement.Center
//                    ) {
//
//                        Image(
//                            painter = painterResource(id = R.drawable.dashboard),
//                            contentDescription = null,
//                            modifier = Modifier
//                                .clickable {
//                                    navController.navigate(ROUTE_DASHBOARD) {
//                                        popUpTo(ROUTE_HOME) { inclusive = true }
//                                    }
//                                }
//                                .size(100.dp)
//                                .clip(RoundedCornerShape(10.dp))
//                        )
//                        androidx.compose.material3.Text(
//                            modifier = Modifier,
//                            text = "Dashboard",
//                            textAlign = TextAlign.Center,
//                            fontSize = 20.sp,
//                            color = androidx.compose.material3.MaterialTheme.colorScheme.onSurface
//                        )
//
//                    }



//                    Spacer(modifier = Modifier.height(15.dp))

//                    Text(
//                        modifier = Modifier
//
//                            .clickable {
//                                navController.navigate(ROUTE_REGISTER) {
//                                    popUpTo(ROUTE_HOME) { inclusive = true }
//                                }
//                            },
//                        text = "Sign up",
//                        textAlign = TextAlign.Center,
//                        fontSize = 20.sp,
//                        color = MaterialTheme.colorScheme.onSurface
//                    )
////
//                    Spacer(modifier = Modifier.height(12.dp))
////
//                    Text(
//                        modifier = Modifier
//
//                            .clickable {
//                                navController.navigate(ROUTE_ADD_PRODUCT) {
//                                    popUpTo(ROUTE_HOME) { inclusive = true }
//                                }
//                            },
//                        text = "Add Products",
//                        textAlign = TextAlign.Center,
//                        fontSize = 20.sp,
//                        color = MaterialTheme.colorScheme.onSurface
//                    )
//
//                    Spacer(modifier = Modifier.height(12.dp))
//
//                    Text(
//                        modifier = Modifier
//
//                            .clickable {
//                                navController.navigate(ROUTE_VIEW_PROD) {
//                                    popUpTo(ROUTE_HOME) { inclusive = true }
//                                }
//                            },
//                        text = "View Products",
//                        textAlign = TextAlign.Center,
//                        fontSize = 20.sp,
//                        color = MaterialTheme.colorScheme.onSurface
//                    )
//
//                    Spacer(modifier = Modifier.height(12.dp))
//
//                    Text(
//                        modifier = Modifier
//
//                            .clickable {
//                                navController.navigate(ROUTE_DASHBOARD) {
//                                    popUpTo(ROUTE_HOME) { inclusive = true }
//                                }
//                            },
//                        text = "Dashboard",
//                        textAlign = TextAlign.Center,
//                        fontSize = 20.sp,
//                        color = MaterialTheme.colorScheme.onSurface
//                    )


            }

            }
        }
    }

//    Scaffold(
//        topBar = {
//            CenterAlignedTopAppBar(
//                title = {
//                    Text(text = "THRIFTERZ")
//                },
//                navigationIcon = @Composable {
//                    if (!isDrawerOpen) {
//                        IconButton(onClick = { isDrawerOpen = true }) {
//                            Icon(
//                                Icons.Default.Menu,
//                                contentDescription = "Menu",
//                                tint = Color.White
//                                )
//                        }
//                    }
//                },
//
//
//                actions = {
//                    IconButton(onClick = {
//                        navController.navigate(ROUTE_LOGIN) {
//                            popUpTo(ROUTE_HOME) { inclusive = true }
//                        }
//
//                    }) {
//                        Icon(
//                            imageVector = Icons.Filled.AccountCircle,
//                            contentDescription = null,
//                            tint = Color.White
//                        )
//                    }
//                },
//
//                colors = TopAppBarDefaults.topAppBarColors(
//                    containerColor = Color(0xFFFFB4AB),
//                    titleContentColor = Color.White,
//
//                )
//
//            )
//        },

//        content = @Composable {
//            Box(
//                modifier = Modifier
//                    .fillMaxSize()
//                    .clickable {
//                        if (isDrawerOpen) {
//                            isDrawerOpen = false
//                        }
//                    }
//            ) {


//                Column(
//                    modifier = Modifier
//                        .fillMaxSize()
//                        .background(Color(0xFFFFDAD6)),
//                    verticalArrangement = Arrangement.Center,
//                    horizontalAlignment = Alignment.CenterHorizontally
//                ) {
//
//                    Spacer(modifier = Modifier.height(15.dp))
//
//                    Text(
//                        modifier = Modifier
//
//                            .clickable {
//                                navController.navigate(ROUTE_LOGIN) {
//                                    popUpTo(ROUTE_HOME) { inclusive = true }
//                                }
//                            },
//                        text = "Login Here",
//                        textAlign = TextAlign.Center,
//                        fontSize = 20.sp,
//                        color = MaterialTheme.colorScheme.onSurface
//                    )
////
////                    Spacer(modifier = Modifier.height(12.dp))
////
//                    Text(
//                        modifier = Modifier
//
//                            .clickable {
//                                navController.navigate(ROUTE_ADD_PRODUCT) {
//                                    popUpTo(ROUTE_HOME) { inclusive = true }
//                                }
//                            },
//                        text = "Add Products",
//                        textAlign = TextAlign.Center,
//                        fontSize = 20.sp,
//                        color = MaterialTheme.colorScheme.onSurface
//                    )
//
//                    Spacer(modifier = Modifier.height(12.dp))
//
//                    Text(
//                        modifier = Modifier
//
//                            .clickable {
//                                navController.navigate(ROUTE_ADD_USER) {
//                                    popUpTo(ROUTE_HOME) { inclusive = true }
//                                }
//                            },
//                        text = "Add Users",
//                        textAlign = TextAlign.Center,
//                        fontSize = 20.sp,
//                        color = MaterialTheme.colorScheme.onSurface
//                    )
//
//                    Spacer(modifier = Modifier.height(12.dp))
//
//                    Text(
//                        modifier = Modifier
//
//                            .clickable {
//                                navController.navigate(ROUTE_VIEW_PROD) {
//                                    popUpTo(ROUTE_HOME) { inclusive = true }
//                                }
//                            },
//                        text = "View Products",
//                        textAlign = TextAlign.Center,
//                        fontSize = 20.sp,
//                        color = MaterialTheme.colorScheme.onSurface
//                    )
//
//                    Spacer(modifier = Modifier.height(12.dp))
//
//                    Text(
//                        modifier = Modifier
//
//                            .clickable {
//                                navController.navigate(ROUTE_DASHBOARD) {
//                                    popUpTo(ROUTE_HOME) { inclusive = true }
//                                }
//                            },
//                        text = "Dashboard",
//                        textAlign = TextAlign.Center,
//                        fontSize = 20.sp,
//                        color = MaterialTheme.colorScheme.onSurface
//                    )
//
//
//                }

//            }
//
//        },

//        bottomBar = { BottomBar(navController = navController) }







//    )

//    AnimatedDrawer(
//        isOpen = isDrawerOpen,
//        onClose = { isDrawerOpen = false }
//    )
//}

@Composable
fun AnimatedDrawer(isOpen: Boolean, onClose: () -> Unit) {
    val drawerWidth = remember { Animatable(if (isOpen) 250f else 0f) }

    LaunchedEffect(isOpen) {
        drawerWidth.animateTo(if (isOpen) 250f else 0f, animationSpec = tween(durationMillis = 300))
    }

    Surface(
        modifier = Modifier
            .fillMaxHeight()
            .width(drawerWidth.value.dp)
            ,
        color = Color.LightGray,
//        elevation = 16.dp
    ) {
        Column {
            Text(
                text = "Drawer Item 1"

            )
            Text(
                text = "Drawer Item 2"
            )
            Text(
                text = "Drawer Item 3",
                modifier = Modifier.clickable {  }
            )
            Spacer(modifier = Modifier.height(16.dp))
            Text(text = stringResource(id = R.string.developer))

        }
    }
}}





//
//@Composable
//fun BottomBar(navController: NavHostController) {
//    val selectedIndex = remember { mutableStateOf(0) }
//    BottomNavigation(
//        elevation = 10.dp,
//        backgroundColor = Color(0xff0FB06A)
//
//
//    ) {
//
//        BottomNavigationItem(icon = {
//            Icon(imageVector = Icons.Default.Home,"", tint = Color.White)
//        },
//            label = { Text(text = "Home",color =  Color.White) }, selected = (selectedIndex.value == 0), onClick = {
//
//            })
//
//        BottomNavigationItem(icon = {
//            Icon(imageVector = Icons.Default.Favorite,"",tint = Color.White)
//        },
//            label = { Text(text = "Favorite",color =  Color.White) }, selected = (selectedIndex.value == 1), onClick = {
//
//            })
//
//        BottomNavigationItem(icon = {
//            Icon(imageVector = Icons.Default.Person, "",tint = Color.White)
//        },
//            label = { Text(
//                text = "Students",
//                color =  Color.White) },
//            selected = (selectedIndex.value == 2),
//            onClick = {
//
//                navController.navigate(ROUTE_SEARCH) {
//                    popUpTo(ROUTE_HOME) { inclusive = true }
//                }
//
//            })
//
//    }
//}