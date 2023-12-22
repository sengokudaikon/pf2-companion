package io.sengokudaikon.isn.chargen.ports

import io.sengokudaikon.isn.chargen.operations.character.command.CharacterCommand
import io.sengokudaikon.isn.infrastructure.operations.response.CharacterResponse

interface CharacterCreationPort {
    fun create(command: CharacterCommand.Create): CharacterResponse
}
