package net.kisha.ui.auth

import android.content.res.Configuration
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import net.kisha.ui.theme.AppTheme
import net.kisha.ui.theme.spacing

@Composable
fun AuthHeader() {
    Column(
        modifier = Modifier.wrapContentSize(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        val spacing = MaterialTheme.spacing


        Image(
            modifier = Modifier
                .size(128.dp, 128.dp),
            painter = painterResource(id = R.drawable.icon),
            contentDescription = stringResource(id = R.string.app_name)
        )


    }
}

@Preview(showBackground = true, uiMode = Configuration.UI_MODE_NIGHT_NO)
@Composable
fun AppHeaderLight() {
    AppTheme {
        AuthHeader()
    }
}

@Preview(showBackground = true, uiMode = Configuration.UI_MODE_NIGHT_YES)
@Composable
fun AppHeaderDark() {
    AppTheme {
        AuthHeader()
    }
}