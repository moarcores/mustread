package com.itmo.mustread

import io.swagger.v3.oas.annotations.OpenAPIDefinition
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
@OpenAPIDefinition
class MustreadApplication

fun main(args: Array<String>) {
	runApplication<MustreadApplication>(*args)
}
