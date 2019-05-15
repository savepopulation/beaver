package com.raqun.beaverlib.util

import com.raqun.beaverlib.Beaver

/*
 * Checks if Beaver already initialized.
 */
fun Beaver.assertIsNotInitialized() {
    if (isInitialized()) {
        throw IllegalArgumentException("Beaver already initialized! You must drop instance first!")
    }
}

/*
 * Checks if Beaver is not initialized
 */
fun Beaver.assertIsInitialized() {
    if (!isInitialized()) {
        throw IllegalArgumentException("Beaver is not initialized! You must init first!")
    }
}