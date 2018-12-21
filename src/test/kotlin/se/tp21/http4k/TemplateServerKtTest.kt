package se.tp21.http4k

import com.natpryce.hamkrest.assertion.assertThat
import com.natpryce.hamkrest.contains
import com.natpryce.hamkrest.equalTo
import org.http4k.core.Method.GET
import org.http4k.core.Request
import org.http4k.core.Status.Companion.OK
import org.junit.jupiter.api.Test

internal class TemplateServerKtTest {

    @Test
    fun `TemplateApp renders a person from a query string`() {
        templateApp(Request(GET, "/person?name=santa&age=100")).run {
            assertThat(status, equalTo(OK))
            assertThat(body.toString(), contains("name: santa age: 100".toRegex()))
        }
    }

}