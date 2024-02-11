package ir.adicom.myapplication.ui.details

import ir.adicom.myapplication.domain.Details
import ir.adicom.myapplication.util.formatDate

data class DetailsUiState(
    val detail: Details = Details(),
    val offline: Boolean = false
) {
    val formattedUserSince = formatDate(detail.userSince)
}