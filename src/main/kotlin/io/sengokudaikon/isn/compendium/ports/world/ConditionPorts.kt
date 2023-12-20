package io.sengokudaikon.isn.compendium.ports.world

import io.sengokudaikon.isn.compendium.domain.condition.ConditionModel
import io.sengokudaikon.isn.compendium.operations.world.condition.query.ConditionQuery
import io.sengokudaikon.isn.infrastructure.ports.ReadPort

interface ListConditionPort : ReadPort<ConditionQuery, List<ConditionModel>>
interface ByIdConditionPort : ReadPort<ConditionQuery, ConditionModel>
interface ByNameConditionPort : ReadPort<ConditionQuery, ConditionModel>
