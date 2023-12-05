package io.sengokudaikon.isn.compendium.domain.action.repository

import io.sengokudaikon.isn.compendium.domain.action.ActionModel
import io.sengokudaikon.isn.infrastructure.repository.RepositoryOutputPort

interface ActionRepositoryPort : RepositoryOutputPort<ActionModel>
