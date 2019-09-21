package dassem.app.resume.model

import com.google.gson.annotations.SerializedName

data class Employment (

	@SerializedName("dates") val dates : String,
	@SerializedName("company") val company : String,
	@SerializedName("job_title") val jobTitle : String,
	@SerializedName("responsibilities") val responsibilities : String
)