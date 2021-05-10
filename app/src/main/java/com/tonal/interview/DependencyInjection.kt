package com.tonal.interview

import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import com.tonal.interview.data.MovementRepository

/**
 * This is a *very* simple Dependency Injection system.
 * For the purpose of this interview project, it is simple to understand and doesn't rely on
 * existing knowledge of Dagger/Koin/etc.
 */
object DependencyInjection {
    lateinit var application: TonalApplication

    private val moshi = Moshi.Builder()
        .add(KotlinJsonAdapterFactory())
        .build()

    val movementRepository get() = MovementRepository(application, moshi)
}