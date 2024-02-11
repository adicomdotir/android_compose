package ir.adicom.myapplication.details

import com.aregyan.compose.domain.Details
import ir.adicom.myapplication.util.formatDate

data class DetailsUiState(
    val detail: Details = Details(),
    val offline: Boolean = false
) {
    val formattedUserSince = formatDate(detail.userSince)
}