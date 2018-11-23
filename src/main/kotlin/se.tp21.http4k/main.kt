package se.tp21.http4k

fun main(args: Array<String>) {
    TemplateServer(9000).start().block()
}