package io.sengokudaikon.isn.chargen.operations.character.command

import io.ktor.resources.*

interface CharacterCommand {
    @Resource("/api/character")
    data class Create(
        val name: String,
        val description: String,
        val userId: String,
    ) : CharacterCommand

    @Resource("/api/character/{id}")
    data class Update(
        val id: String,
    ) : CharacterCommand
}
