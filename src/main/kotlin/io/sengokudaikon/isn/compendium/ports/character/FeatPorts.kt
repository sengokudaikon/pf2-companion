package io.sengokudaikon.isn.compendium.ports.character

import io.sengokudaikon.isn.compendium.domain.feat.FeatModel
import io.sengokudaikon.isn.compendium.operations.character.feat.query.FeatQuery
import io.sengokudaikon.isn.infrastructure.ports.ReadPort

interface ListFeatPort : ReadPort<FeatQuery.General, List<FeatModel>>
interface ByIdFeatPort : ReadPort<FeatQuery.General, FeatModel>
interface ByNameFeatPort : ReadPort<FeatQuery.General, FeatModel>
interface ListSkillFeatPort : ReadPort<FeatQuery.Skill, List<FeatModel>>
interface ByIdSkillFeatPort : ReadPort<FeatQuery.Skill, FeatModel>
interface ByNameSkillFeatPort : ReadPort<FeatQuery.Skill, FeatModel>
