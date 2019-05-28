package com.avolosko.spacex.internal.di

import java.lang.annotation.Documented
import javax.inject.Scope

@Documented
@Scope
@Retention(AnnotationRetention.RUNTIME)
@Target(AnnotationTarget.TYPE)
annotation class FragmentScoped