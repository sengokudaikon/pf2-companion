package io.sengokudaikon.isn.compendium.ports.character

import io.sengokudaikon.isn.compendium.domain.ancestry.AncestryFeatureModel
import io.sengokudaikon.isn.compendium.domain.ancestry.AncestryModel
import io.sengokudaikon.isn.compendium.operations.character.ancestry.query.AncestryFeatsQuery
import io.sengokudaikon.isn.compendium.operations.character.ancestry.query.AncestryQuery
import io.sengokudaikon.isn.infrastructure.ports.ReadPort

interface ListAncestryPort : ReadPort<AncestryQuery, List<AncestryModel>>
interface ByIdAncestryPort : ReadPort<AncestryQuery, AncestryModel>
interface ByNameAncestryPort : ReadPort<AncestryQuery, AncestryModel>
interface ListAncestryFeatPort : ReadPort<AncestryFeatsQuery, List<AncestryFeatureModel>>
interface ByIdAncestryFeatPort : ReadPort<AncestryFeatsQuery, AncestryFeatureModel>
interface ByNameAncestryFeatPort : ReadPort<AncestryFeatsQuery, AncestryFeatureModel>
