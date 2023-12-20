package io.sengokudaikon.isn.compendium.ports.world

import io.sengokudaikon.isn.compendium.domain.spell.SpellEffectModel
import io.sengokudaikon.isn.compendium.domain.spell.SpellModel
import io.sengokudaikon.isn.compendium.operations.world.spell.query.SpellEffectQuery
import io.sengokudaikon.isn.compendium.operations.world.spell.query.SpellQuery
import io.sengokudaikon.isn.infrastructure.ports.ReadPort

interface ListSpellPort : ReadPort<SpellQuery, List<SpellModel>>
interface ByIdSpellPort : ReadPort<SpellQuery, SpellModel>
interface ByNameSpellPort : ReadPort<SpellQuery, SpellModel>
interface ListSpellEffectPort : ReadPort<SpellEffectQuery.All, List<SpellEffectModel>>
interface ByIdSpellEffectPort : ReadPort<SpellEffectQuery.ById, SpellEffectModel>
interface ByNameSpellEffectPort : ReadPort<SpellEffectQuery.ByName, SpellEffectModel>
