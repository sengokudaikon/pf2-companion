package io.sengokudaikon.dbfinder.domain.world.item.repository

import io.sengokudaikon.dbfinder.domain.world.item.entity.Item
import io.sengokudaikon.shared.persistence.repository.RepositoryCreatePort
import io.sengokudaikon.shared.persistence.repository.RepositoryOutputPort
import io.sengokudaikon.dbfinder.domain.character.feat.model.Item as ModelItem

interface ItemsRepositoryPort : RepositoryOutputPort<Item>, RepositoryCreatePort<ModelItem, Item>
