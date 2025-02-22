/*
 * Copyright The OpenTelemetry Authors
 * SPDX-License-Identifier: Apache-2.0
 */

package io.opentelemetry.instrumentation.ktor.v3_0

import io.ktor.server.application.*
import io.opentelemetry.instrumentation.ktor.v3_0.server.KtorServerTracing
import io.opentelemetry.instrumentation.testing.junit.InstrumentationExtension
import io.opentelemetry.instrumentation.testing.junit.http.HttpServerInstrumentationExtension
import org.junit.jupiter.api.extension.RegisterExtension

class KtorHttpServerOldTest : AbstractKtorHttpServerTest() {

  companion object {
    @JvmStatic
    @RegisterExtension
    val TESTING: InstrumentationExtension = HttpServerInstrumentationExtension.forLibrary()
  }

  override fun getTesting(): InstrumentationExtension = TESTING

  override fun installOpenTelemetry(application: Application) {
    application.apply {
      install(KtorServerTracing) {
        setOpenTelemetry(TESTING.openTelemetry)
        capturedRequestHeaders(TEST_REQUEST_HEADER)
        capturedResponseHeaders(TEST_RESPONSE_HEADER)
      }
    }
  }
}
