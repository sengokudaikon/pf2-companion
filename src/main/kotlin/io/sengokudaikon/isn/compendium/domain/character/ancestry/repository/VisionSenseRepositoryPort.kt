package io.sengokudaikon.isn.compendium.domain.character.ancestry.repository

import io.sengokudaikon.isn.compendium.domain.character.ancestry.entity.VisionSense
import io.sengokudaikon.isn.infrastructure.repository.RepositoryCreatePort
import io.sengokudaikon.isn.infrastructure.repository.RepositoryOutputPort
import io.sengokudaikon.isn.compendium.domain.character.ancestry.model.VisionSense as ModelVisionSense

interface VisionSenseRepositoryPort :
    RepositoryOutputPort<VisionSense>,
    RepositoryCreatePort<ModelVisionSense, VisionSense>
