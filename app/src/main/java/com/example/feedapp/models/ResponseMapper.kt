package com.example.feedapp.models

import com.example.feedapp.viewmodel.PageState

fun responseMapper(state: PageState.Content): MutableList<Any> {
    val cards = state.page.cards
    val parsedCards = mutableListOf<Any>()
    for (i in cards.indices) {
        val cardType = cards[i].cardType
        val card = cards[i].card
        if (cardType == "text") {
            val element = Text(
                value = card.value as String,
                textColor = card.attributes?.textColor as String,
                fontSize = card.attributes?.font.size
            )
            parsedCards.add(element)
        }
        else if (cardType == "title_description") {
            val element = TitleDecription(
                titleValue = card.title?.value as String,
                titleTextColor = card.title.attributes.textColor,
                titleTextFontSize = card.title.attributes.font.size,
                descriptionValue = card.description?.value as String,
                descriptionTextColor = card.description?.attributes.textColor,
                descriptionFontSize = card.description.attributes.font.size
            )
            parsedCards.add(element)
        }
            else if (cardType == "image_title_description") {
            val element = ImageTitleDescription(
                ImageUrl = card.image?.url as String,
                ImageWidth = card.image.size.width,
                ImageHeight = card.image.size.height,
                titleValue = card.title?.value as String,
                titleTextColor = card.title.attributes.textColor,
                titleTextFontSize = card.title.attributes.font.size,
                descriptionValue = card.description?.value as String,
                descriptionTextColor = card.description.attributes.textColor,
                descriptionFontSize = card.description.attributes.font.size
            )
            parsedCards.add(element)
        }
    }
    return parsedCards
}