package se.tp21.http4k

import org.http4k.core.*
import org.http4k.filter.ServerFilters
import org.http4k.routing.RoutingHttpHandler
import org.http4k.routing.bind
import org.http4k.routing.routes
import org.http4k.server.Http4kServer
import org.http4k.server.Jetty
import org.http4k.server.asServer
import org.http4k.template.HandlebarsTemplates
import org.http4k.template.ViewModel

val render = HandlebarsTemplates().HotReload("src/main/resources")

data class Person(val name: String, val age: Int) : ViewModel

private fun Request.person(): Person {
    val name = query("name")!!
    val age = query("age")!!.toInt()
    return Person(name, age)
}

fun TemplateApp(): RoutingHttpHandler =
    ServerFilters.CatchAll().then(
        routes(
            "/person" bind Method.GET to { Response(Status.OK).body(render(it.person())) }
        )
    )

fun TemplateServer(port: Int) = TemplateApp().asServer(Jetty(port))