package com.example.submission_app

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Character(
    val name: String,
    val description: String,
    val photo: String
) : Parcelable
