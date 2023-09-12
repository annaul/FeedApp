package com.example.feedapp.models

import org.json.JSONArray

fun mapCardsToComposeProps (cards: JSONArray): MutableList<CardModel> {
    val parsedCards = mutableListOf<CardModel>()

    for (i in 0 until cards.length()) {
        val cardType = cards.getJSONObject(i).get("card_type")
        val card = cards.getJSONObject(i).getJSONObject("card")
        // When would probably be better here
        // This would be much more minimal if I used retrofit or something similar
        if (cardType == "text") {
            val element = Text(
                value = card.get("value") as String,
                textColor = card.getJSONObject("attributes").get("text_color") as String,
                fontSize = card.getJSONObject("attributes").getJSONObject("font")
                    .get("size") as Int
            )
            parsedCards.add(element)
        } else if (cardType == "title_description") {
            val element = TitleDecription(
                titleValue = card.getJSONObject("title").get("value") as String,
                titleTextColor = card.getJSONObject("title").getJSONObject("attributes")
                    .get("text_color") as String,
                titleTextFontSize = card.getJSONObject("title").getJSONObject("attributes")
                    .getJSONObject("font").get("size") as Int,
                descriptionValue = card.getJSONObject("description").get("value") as String,
                descriptionTextColor = card.getJSONObject("description")
                    .getJSONObject("attributes").get("text_color") as String,
                descriptionFontSize = card.getJSONObject("description")
                    .getJSONObject("attributes").getJSONObject("font")
                    .get("size") as Int

            )
            parsedCards.add(element)
        } else if (cardType == "image_title_description") {
            val element = ImageTitleDescription(
                ImageUrl = card.getJSONObject("image").get("url") as String,
                ImageWidth = card.getJSONObject("image").getJSONObject("size")
                    .get("width") as Int,
                ImageHeight = card.getJSONObject("image").getJSONObject("size")
                    .get("height") as Int,
                titleValue = card.getJSONObject("title").get("value") as String,
                titleTextColor = card.getJSONObject("title")
                    .getJSONObject("attributes").get("text_color") as String,
                titleTextFontSize = card.getJSONObject("title")
                    .getJSONObject("attributes").getJSONObject("font")
                    .get("size") as Int,
                descriptionValue = card.getJSONObject("description").get("value") as String,
                descriptionTextColor = card.getJSONObject("description")
                    .getJSONObject("attributes").get("text_color") as String,
                descriptionFontSize = card.getJSONObject("description")
                    .getJSONObject("attributes").getJSONObject("font")
                    .get("size") as Int
            )
            parsedCards.add(element)
        }
    }
    return parsedCards
}