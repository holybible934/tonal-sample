package com.tonal.interview.data

import android.app.Application
import com.squareup.moshi.Moshi
import com.squareup.moshi.Types
import com.tonal.interview.R
import kotlinx.coroutines.CancellationException
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.withContext
import okio.buffer
import okio.source

/**
 * This is provided by [DependencyInjection]. Use that rather than creating another instance of this.
 */
class MovementRepository(
    private val context: Application,
    moshi: Moshi,
) {
    private val movementsListAdapter = moshi.adapter<List<Movement>>(Types.newParameterizedType(List::class.java, Movement::class.java))

    /**
     * Returns a list of all movements.
     *
     */
    suspend fun fetchMovements(artificialDelayMs: Long = 3_000L, artificialError: Boolean = false): List<Movement> {
        delay(artificialDelayMs)
        if (artificialError) {
            throw CancellationException("Fetching movements was cancelled!")
        }

        return withContext(Dispatchers.IO) {
            context.resources.openRawResource(R.raw.movements).source().buffer().use { source ->
                @Suppress("BlockingMethodInNonBlockingContext")
                movementsListAdapter.fromJson(source)
            }
        } ?: emptyList()
    }
}