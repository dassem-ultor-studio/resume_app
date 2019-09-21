package dassem.app.resume.extensions

import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable

fun Disposable.addTo(disposable: CompositeDisposable): Disposable = apply { disposable.add(this) }
