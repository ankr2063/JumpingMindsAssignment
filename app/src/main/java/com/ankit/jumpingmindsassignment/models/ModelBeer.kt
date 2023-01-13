package com.ankit.jumpingmindsassignment.models

data class ModelBeer(
    val id: Int,
    val name: String,
    val tagline: String,
    val first_brewed: String,
    val description: String,
    val image_url: String,
    val abv: Float,
    val ibu: Float,
    val target_fg: Float,
    val target_og: Float,
    val ebc: Float,
    val srm: Float,
    val ph: Float,
    val attenuation_level: Float,
    val volume: ModelQuantity,
    val boil_volume: ModelQuantity,
    val method: ModelMethod,
    val ingredients: ModelIngredient,
    val food_pairing: List<String>,
    val browser_tips: String,
    val contributed_by: String
)