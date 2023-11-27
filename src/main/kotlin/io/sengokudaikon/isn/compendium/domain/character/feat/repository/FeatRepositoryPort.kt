package io.sengokudaikon.isn.compendium.domain.character.feat.repository

import io.sengokudaikon.isn.compendium.domain.character.feat.entity.Feat
import io.sengokudaikon.isn.infrastructure.repository.RepositoryCreatePort
import io.sengokudaikon.isn.infrastructure.repository.RepositoryOutputPort
import io.sengokudaikon.isn.compendium.domain.character.feat.model.Feat as ModelFeat

interface FeatRepositoryPort : RepositoryOutputPort<Feat>, RepositoryCreatePort<ModelFeat, Feat>
