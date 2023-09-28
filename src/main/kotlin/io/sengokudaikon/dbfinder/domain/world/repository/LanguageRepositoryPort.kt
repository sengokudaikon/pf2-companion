package io.sengokudaikon.dbfinder.domain.world.repository

import io.sengokudaikon.dbfinder.domain.world.entity.Language
import io.sengokudaikon.shared.persistence.repository.RepositoryCreatePort
import io.sengokudaikon.shared.persistence.repository.RepositoryOutputPort
import io.sengokudaikon.dbfinder.domain.world.model.Language as ModelLanguage

interface LanguageRepositoryPort : RepositoryOutputPort<Language>, RepositoryCreatePort<ModelLanguage, Language>
