package io.sengokudaikon.isn.compendium.ports.character

import io.sengokudaikon.isn.compendium.domain.action.ActionModel
import io.sengokudaikon.isn.compendium.operations.character.action.query.ActionQuery
import io.sengokudaikon.isn.infrastructure.ports.ReadPort

interface ListActionPort : ReadPort<ActionQuery, List<ActionModel>>
interface ByIdActionPort : ReadPort<ActionQuery, ActionModel>
interface ByNameActionPort : ReadPort<ActionQuery, ActionModel>
