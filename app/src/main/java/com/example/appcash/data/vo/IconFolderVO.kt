package com.example.appcash.data.vo

import androidx.room.Embedded
import com.example.appcash.data.entities.Folder

data class IconFolderVO(
    @Embedded
    val folder: Folder,
    val iconId: String
)
