package io.sengokudaikon.dbfinder.domain.character.feat.repository

import io.sengokudaikon.dbfinder.domain.character.feat.entity.Feat
import io.sengokudaikon.shared.persistence.repository.RepositoryCreatePort
import io.sengokudaikon.shared.persistence.repository.RepositoryOutputPort
import io.sengokudaikon.dbfinder.domain.character.feat.model.Feat as ModelFeat

interface FeatRepositoryPort : RepositoryOutputPort<Feat>, RepositoryCreatePort<ModelFeat, Feat>
