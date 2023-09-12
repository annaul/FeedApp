package com.example.feedapp.models

enum class Type {
    TEXT, TITLE_DESCRIPTION, IMAGE_TITLE_DESCRIPTION
}

sealed class CardModel(
    val cardType: Type
)

class Text(
    val value: String = "",
    val textColor: String = "",
    val fontSize: Int = 24
): CardModel(Type.TEXT)

class TitleDecription(
    val titleValue: String = "",
    val titleTextColor: String = "",
    val titleTextFontSize: Int = 24,
    val descriptionValue: String ="",
    val descriptionTextColor: String = "",
    val descriptionFontSize: Int = 24
) : CardModel(Type.TITLE_DESCRIPTION)

class ImageTitleDescription(
    val ImageUrl: String = "",
    val ImageWidth: Int = 100,
    val ImageHeight: Int = 100,
    val titleValue: String = "",
    val titleTextColor: String = "",
    val titleTextFontSize: Int = 24,
    val descriptionValue: String = "",
    val descriptionTextColor: String = "",
    val descriptionFontSize: Int = 24
): CardModel(Type.IMAGE_TITLE_DESCRIPTION)


