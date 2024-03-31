package org.mozilla.reference.browser.benchmark

///* This Source Code Form is subject to the terms of the Mozilla Public
// * License, v. 2.0. If a copy of the MPL was not distributed with this
// * file, You can obtain one at http://mozilla.org/MPL/2.0/. */

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.benchmark.macro.BaselineProfileMode
import androidx.benchmark.macro.CompilationMode
import androidx.benchmark.macro.StartupMode
import androidx.benchmark.macro.StartupTimingMetric
import androidx.benchmark.macro.junit4.MacrobenchmarkRule
import androidx.test.ext.junit.runners.AndroidJUnit4
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.mozilla.reference.browser.benchmark.utils.measureRepeatedDefault

/**
 * This test class benchmarks the speed of app startup. Run this benchmark to verify how effective
 **/
@RunWith(AndroidJUnit4::class)
@RequiresApi(Build.VERSION_CODES.N)
class BaselineProfilesStartupBenchmark {

    @get:Rule
    val benchmarkRule = MacrobenchmarkRule()

    @Test
    fun startupNone() = startupBenchmark(CompilationMode.None())

    @Test
    fun startupPartialWithBaselineProfiles() =
        startupBenchmark(CompilationMode.Partial(baselineProfileMode = BaselineProfileMode.Require))

    private fun startupBenchmark(compilationMode: CompilationMode) =
        benchmarkRule.measureRepeatedDefault(
            metrics = listOf(StartupTimingMetric()),
            startupMode = StartupMode.COLD,
            compilationMode = compilationMode,
            setupBlock = {
                pressHome()
            },
        ) {
            startActivityAndWait()
        }
}
