package net.kisha.ui.products

import android.annotation.SuppressLint
import android.net.Uri
import android.widget.Toast
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.*
import androidx.compose.ui.*
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import coil.compose.rememberImagePainter
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import com.google.firebase.storage.ktx.storage
import net.kisha.navigation.ROUTE_HOME
import java.util.*

@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AddProductScreen(navController: NavController, onProductAdded: () -> Unit) {
    var productName by remember { mutableStateOf("") }
    var productDescription by remember { mutableStateOf("") }
    var productPrice by remember { mutableStateOf("") }
    var productImageUri by remember { mutableStateOf<Uri?>(null) }

    // Track if fields are empty
    var productNameError by remember { mutableStateOf(false) }
    var productDescriptionError by remember { mutableStateOf(false) }
    var productPriceError by remember { mutableStateOf(false) }
    var productImageError by remember { mutableStateOf(false) }

    val launcher = rememberLauncherForActivityResult(contract = ActivityResultContracts.GetContent()) { uri: Uri? ->
        uri?.let {
            productImageUri = it
        }
    }

    Scaffold(
        topBar = {
            CenterAlignedTopAppBar(
                title = {
                    Text(text = "Add Products",fontSize = 30.sp, color = Color.White)
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
        },
        content = {
            LazyColumn(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(9.dp)
                    .background(Color(0xFFFFDAD6))
            ) {
                item {
                    if (productImageUri != null) {
                        // Display selected image
                        Image(
                            painter = rememberImagePainter(productImageUri), // Using rememberImagePainter with Uri
                            contentDescription = null,
                            modifier = Modifier
                                .fillMaxWidth()
                                .height(200.dp)
                        )
                    } else {
                        // Display placeholder if no image selected
                        Box(
                            modifier = Modifier
                                .fillMaxWidth()
                                .height(200.dp),
                            contentAlignment = Alignment.Center
                        ) {
                            Text("No Image Selected", modifier = Modifier.padding(8.dp))
                        }
                    }
                    Spacer(modifier = Modifier.height(16.dp))

                    Button(colors = ButtonDefaults.buttonColors(Color(0xFFCFC0E8)),
                        onClick = { launcher.launch("image/*") }

                    ) {
                        Text("Select Image")
                    }
                    Spacer(modifier = Modifier.height(16.dp))
                    OutlinedTextField(
                        value = productName,
                        onValueChange = { productName = it },
                        label = { Text("Product Name") },
                        modifier = Modifier
                            .fillMaxWidth()
                    )
                    Spacer(modifier = Modifier.height(8.dp))
                    OutlinedTextField(
                        value = productDescription,
                        onValueChange = { productDescription = it },
                        label = { Text("Product Description") },
                        modifier = Modifier
                            .fillMaxWidth()
                    )
                    Spacer(modifier = Modifier.height(8.dp))
                    OutlinedTextField(
                        value = productPrice,
                        onValueChange = { productPrice = it },
                        label = { Text("Product Price") },
                        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
                        keyboardActions = KeyboardActions(onDone = { /* Handle Done action */ }),
                        modifier = Modifier
                            .fillMaxWidth()
                    )
                    Spacer(modifier = Modifier.height(16.dp))

                    if (productNameError) {
                        Text("Product Name is required", color = Color.Red)
                    }
                    if (productDescriptionError) {
                        Text("Product Description is required", color = Color.Red)
                    }
                    if (productPriceError) {
                        Text("Product Price is required", color = Color.Red)
                    }
                    if (productImageError) {
                        Text("Product Image is required", color = Color.Red)
                    }

                    // Button to add product
                    Button(colors = ButtonDefaults.buttonColors(Color(0xFFCFC0E8)),
                        onClick = {
                            // Reset error flags
                            productNameError = productName.isBlank()
                            productDescriptionError = productDescription.isBlank()
                            productPriceError = productPrice.isBlank()
                            productImageError = productImageUri == null

                            // Add product if all fields are filled
                            if (!productNameError && !productDescriptionError && !productPriceError && !productImageError) {
                                addProductToFirestore(
                                    navController,
                                    onProductAdded,
                                    productName,
                                    productDescription,
                                    productPrice.toDouble(),
                                    productImageUri
                                )
                            }
                        },

                    ) {
                        Text("Add Product")
                    }
                }
            }
        }
    )
}

private fun addProductToFirestore(navController: NavController, onProductAdded: () -> Unit, productName: String, productDescription: String, productPrice: Double, productImageUri: Uri?) {
    if (productName.isEmpty() || productDescription.isEmpty() || productPrice.isNaN() || productImageUri == null) {
        // Validate input fields
        return
    }

    val productId = UUID.randomUUID().toString()

    val firestore = Firebase.firestore
    val productData = hashMapOf(
        "name" to productName,
        "description" to productDescription,
        "price" to productPrice,
        "imageUrl" to ""
    )

    firestore.collection("products").document(productId)
        .set(productData)
        .addOnSuccessListener {
            uploadImageToStorage(productId, productImageUri) { imageUrl ->
                firestore.collection("products").document(productId)
                    .update("imageUrl", imageUrl)
                    .addOnSuccessListener {
                        // Display toast message
                        Toast.makeText(
                            navController.context,
                            "Product added successfully!",
                            Toast.LENGTH_SHORT
                        ).show()

                        // Navigate to another screen
                        navController.navigate(ROUTE_HOME)

                        // Invoke the onProductAdded callback
                        onProductAdded()
                    }
                    .addOnFailureListener { e ->
                        // Handle error updating product document
                    }
            }
        }
        .addOnFailureListener { e ->
            // Handle error adding product to Firestore
        }
}

private fun uploadImageToStorage(productId: String, imageUri: Uri?, onSuccess: (String) -> Unit) {
    if (imageUri == null) {
        onSuccess("")
        return
    }

    val storageRef = Firebase.storage.reference
    val imagesRef = storageRef.child("products/$productId.jpg")

    imagesRef.putFile(imageUri)
        .addOnSuccessListener { taskSnapshot ->
            imagesRef.downloadUrl
                .addOnSuccessListener { uri ->
                    onSuccess(uri.toString())
                }
                .addOnFailureListener {
                    // Handle failure to get download URL
                }
        }
        .addOnFailureListener {
            // Handle failure to upload image
        }
}