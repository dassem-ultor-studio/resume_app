package dassem.app.resume.model

import com.google.gson.annotations.SerializedName

data class Education(
    @SerializedName("dates") val dates: String,
    @SerializedName("university") val university: String,
    @SerializedName("course") val course: String,
    @SerializedName("key_modules") val keyModules: String
)