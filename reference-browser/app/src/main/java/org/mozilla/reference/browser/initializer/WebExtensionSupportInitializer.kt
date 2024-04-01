/* This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at http://mozilla.org/MPL/2.0/. */
package org.mozilla.reference.browser.initializer

import android.content.Context
import androidx.annotation.Keep
import androidx.startup.Initializer
import mozilla.components.concept.engine.webextension.isUnsupported
import mozilla.components.support.webextensions.WebExtensionSupport
import org.mozilla.reference.browser.BrowserApplication

@Keep
class WebExtensionSupportInitializer : Initializer<Unit> {
    override fun create(context: Context) {
        val application = context as BrowserApplication
        val components = application.components

        WebExtensionSupport.initialize(
            runtime = components.core.engine,
            store = components.core.store,
            onNewTabOverride = { _, engineSession, url ->
                val tabId = components.useCases.tabsUseCases.addTab(
                    url = url,
                    selectTab = true,
                    engineSession = engineSession,
                )
                tabId
            },
            onCloseTabOverride = { _, sessionId ->
                components.useCases.tabsUseCases.removeTab(sessionId)
            },
            onSelectTabOverride = { _, sessionId ->
                components.useCases.tabsUseCases.selectTab(sessionId)
            },
            onExtensionsLoaded = { extensions ->
                components.core.addonUpdater.registerForFutureUpdates(extensions)

                val checker = components.core.supportedAddonsChecker
                val hasUnsupportedAddons = extensions.any { it.isUnsupported() }
                if (hasUnsupportedAddons) {
                    checker.registerForChecks()
                } else {
                    // As checks are a persistent subscriptions, we have to make sure
                    // we remove any previous subscriptions.
                    checker.unregisterForChecks()
                }
            },
            onUpdatePermissionRequest = components.core.addonUpdater::onUpdatePermissionRequest,
        )
    }

    override fun dependencies(): List<Class<out Initializer<*>>> = emptyList()
}