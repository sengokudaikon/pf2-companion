package io.sengokudaikon.isn.compendium.domain.world.item.repository

import io.sengokudaikon.isn.compendium.domain.world.item.entity.Item
import io.sengokudaikon.isn.infrastructure.repository.RepositoryCreatePort
import io.sengokudaikon.isn.infrastructure.repository.RepositoryOutputPort
import io.sengokudaikon.isn.compendium.domain.character.feat.model.Item as ModelItem

interface ItemsRepositoryPort : RepositoryOutputPort<Item>, RepositoryCreatePort<ModelItem, Item>
