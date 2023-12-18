package io.sengokudaikon.isn.chargen.ports

import io.sengokudaikon.isn.chargen.domain.model.CharacterModel
import io.sengokudaikon.isn.chargen.operations.character.query.CharacterQuery
import io.sengokudaikon.isn.infrastructure.ports.ReadPort

interface ListAllCharactersPort: ReadPort<CharacterQuery.All, List<CharacterModel>>
interface ByNameCharacterPort: ReadPort<CharacterQuery.ByName, CharacterModel>
interface ByIdCharacterPort: ReadPort<CharacterQuery.ById, CharacterModel>
interface ListUserCharactersPort: ReadPort<CharacterQuery.ListByUser, List<CharacterModel>>
interface ByIdUserCharacterPort: ReadPort<CharacterQuery.ByIdAndUser, CharacterModel>
interface ByNameUserCharacterPort: ReadPort<CharacterQuery.ByNameAndUser, CharacterModel>