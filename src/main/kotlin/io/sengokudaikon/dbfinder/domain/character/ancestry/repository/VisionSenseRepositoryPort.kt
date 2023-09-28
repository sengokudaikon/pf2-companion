package io.sengokudaikon.dbfinder.domain.character.ancestry.repository

import io.sengokudaikon.dbfinder.domain.character.ancestry.entity.VisionSense
import io.sengokudaikon.shared.persistence.repository.RepositoryCreatePort
import io.sengokudaikon.shared.persistence.repository.RepositoryOutputPort
import io.sengokudaikon.dbfinder.domain.character.ancestry.model.VisionSense as ModelVisionSense

interface VisionSenseRepositoryPort :
    RepositoryOutputPort<VisionSense>,
    RepositoryCreatePort<ModelVisionSense, VisionSense>
