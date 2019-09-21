package dassem.app.resume.model

import com.google.gson.annotations.SerializedName

data class Resume(
    @SerializedName("name") val name: String,
    @SerializedName("profile_image") val profileImage: String,
    @SerializedName("profile") val profile: String,
    @SerializedName("key_achievements") val keyAchievements: String,
    @SerializedName("education") val education: Education,
    @SerializedName("employment") val employment: List<Employment>,
    @SerializedName("interests") val interests: String
)