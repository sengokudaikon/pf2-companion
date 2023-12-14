package io.sengokudaikon.isn.compendium.ports.character

import io.sengokudaikon.isn.compendium.domain.background.BackgroundModel
import io.sengokudaikon.isn.compendium.operations.character.background.query.BackgroundQuery
import io.sengokudaikon.isn.infrastructure.ports.ReadPort

interface ListBackgroundPort : ReadPort<BackgroundQuery, List<BackgroundModel>>
interface ByIdBackgroundPort : ReadPort<BackgroundQuery, BackgroundModel>
interface ByNameBackgroundPort : ReadPort<BackgroundQuery, BackgroundModel>
