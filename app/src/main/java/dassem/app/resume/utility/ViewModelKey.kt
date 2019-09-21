package dassem.app.resume.utility

import androidx.lifecycle.ViewModel
import dagger.MapKey
import java.lang.annotation.Documented
import kotlin.reflect.KClass

@Suppress("DEPRECATED_JAVA_ANNOTATION")
@Documented
@MapKey
internal annotation class ViewModelKey(val value: KClass<out ViewModel>)
