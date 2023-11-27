package io.sengokudaikon.isn.compendium.ports.character

import io.sengokudaikon.isn.compendium.domain.character.ancestry.model.Ancestry
import io.sengokudaikon.isn.compendium.operations.character.ancestry.query.AncestryQuery
import io.sengokudaikon.isn.infrastructure.ports.ReadPort

interface AncestryPort : ReadPort<AncestryQuery, Ancestry>
