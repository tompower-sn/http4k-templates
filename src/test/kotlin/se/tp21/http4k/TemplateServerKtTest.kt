package se.tp21.http4k

import com.natpryce.hamkrest.assertion.assertThat
import com.natpryce.hamkrest.contains
import com.natpryce.hamkrest.equalTo
import org.http4k.core.Method
import org.http4k.core.Request
import org.http4k.core.Status
import org.junit.jupiter.api.Test

internal class TemplateServerKtTest {

    @Test
    fun `returns a person with the right name and age`() {
        TemplateApp().invoke(Request(Method.GET, "/person?name=bob&age=45")).run {
            assertThat(status, equalTo(Status.OK))
            assertThat(body.toString(), contains("name: bob".toRegex()))
            assertThat(body.toString(), contains("age: 45".toRegex()))
        }
    }
    
}