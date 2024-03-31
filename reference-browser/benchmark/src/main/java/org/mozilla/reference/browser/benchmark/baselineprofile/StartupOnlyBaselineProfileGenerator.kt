///* This Source Code Form is subject to the terms of the Mozilla Public
// * License, v. 2.0. If a copy of the MPL was not distributed with this
// * file, You can obtain one at http://mozilla.org/MPL/2.0/. */

package org.mozilla.reference.browser.benchmark.baselineprofile

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.benchmark.macro.junit4.BaselineProfileRule
import androidx.test.ext.junit.runners.AndroidJUnit4
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.mozilla.reference.browser.benchmark.utils.TARGET_PACKAGE

/**
 * This test class generates a basic startup baseline profile for the target package.
 *
 * Refer to the [baseline profile documentation](https://d.android.com/topic/performance/baselineprofiles)
 * for more information.
 *
 * Make sure `autosignReleaseWithDebugKey=true` is present in local.properties.
 *
 * When using this class to generate a baseline profile, only API 33+ or rooted API 28+ are supported.
 **/
@RequiresApi(Build.VERSION_CODES.P)
@RunWith(AndroidJUnit4::class)
class StartupOnlyBaselineProfileGenerator {

    @get:Rule
    val rule = BaselineProfileRule()

    @Test
    fun generateBaselineProfile() {
        rule.collect(
            packageName = TARGET_PACKAGE,
        ) {
            startActivityAndWait()
        }
    }
}
