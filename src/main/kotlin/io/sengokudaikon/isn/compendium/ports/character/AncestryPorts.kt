package io.sengokudaikon.isn.compendium.ports.character

import io.sengokudaikon.isn.compendium.domain.ancestry.AncestryModel
import io.sengokudaikon.isn.compendium.operations.character.ancestry.query.AncestryQuery
import io.sengokudaikon.isn.infrastructure.ports.ReadPort

interface ListAncestryPort : ReadPort<AncestryQuery, List<AncestryModel>>
interface GetAncestryPort : ReadPort<AncestryQuery, AncestryModel>
interface GetByNameAncestryPort : ReadPort<AncestryQuery, AncestryModel>
