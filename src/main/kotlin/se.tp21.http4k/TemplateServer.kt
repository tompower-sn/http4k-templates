package se.tp21.http4k

import org.http4k.core.Method.GET
import org.http4k.core.Request
import org.http4k.core.Response
import org.http4k.core.Status.Companion.OK
import org.http4k.core.then
import org.http4k.filter.ServerFilters
import org.http4k.routing.RoutingHttpHandler
import org.http4k.routing.bind
import org.http4k.routing.routes
import org.http4k.server.Jetty
import org.http4k.server.asServer
import org.http4k.template.HandlebarsTemplates
import org.http4k.template.ViewModel

private data class Person(val name: String, val age: Int) : ViewModel

private fun Request.person(): Person =
    Person(
        name = query("name")!!,
        age = query("age")!!.toInt()
    )

private fun ViewModel.render() = HandlebarsTemplates().HotReload("src/main/resources")(this)

val templateApp: RoutingHttpHandler =
    ServerFilters.CatchAll().then(
        routes(
            "/person" bind GET to {
                Response(OK).body(it.person().render())
            }
        )
    )

fun TemplateServer(port: Int) = templateApp.asServer(Jetty(port))