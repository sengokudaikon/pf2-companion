package io.sengokudaikon.isn.compendium.domain.world.global.repository

import io.sengokudaikon.isn.compendium.domain.world.global.entity.Language
import io.sengokudaikon.isn.infrastructure.repository.RepositoryCreatePort
import io.sengokudaikon.isn.infrastructure.repository.RepositoryOutputPort
import io.sengokudaikon.isn.compendium.domain.world.global.model.Language as ModelLanguage

interface LanguageRepositoryPort : RepositoryOutputPort<Language>, RepositoryCreatePort<ModelLanguage, Language>
