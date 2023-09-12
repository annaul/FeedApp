package com.example.feedapp.models

enum class Type {
    TEXT, TITLE_DESCRIPTION, IMAGE_TITLE_DESCRIPTION
}

sealed class CardModel(
    val cardType: Type
)

class Text(
    val value: String?,
    val textColor: String?,
    val fontSize: Int?
): CardModel(Type.TEXT)

class TitleDecription(
    val titleValue: String?,
    val titleTextColor: String?,
    val titleTextFontSize: Int?,
    val descriptionValue: String?,
    val descriptionTextColor: String?,
    val descriptionFontSize: Int?
) : CardModel(Type.TITLE_DESCRIPTION)

class ImageTitleDescription(
    val ImageUrl: String?,
    val ImageWidth: Int?,
    val ImageHeight: Int?,
    val titleValue: String?,
    val titleTextColor: String?,
    val titleTextFontSize: Int?,
    val descriptionValue: String?,
    val descriptionTextColor: String?,
    val descriptionFontSize: Int?
): CardModel(Type.IMAGE_TITLE_DESCRIPTION)
