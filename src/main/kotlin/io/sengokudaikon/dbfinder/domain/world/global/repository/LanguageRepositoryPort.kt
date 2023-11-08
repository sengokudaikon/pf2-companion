package io.sengokudaikon.dbfinder.domain.world.global.repository

import io.sengokudaikon.dbfinder.domain.world.global.entity.Language
import io.sengokudaikon.shared.persistence.repository.RepositoryCreatePort
import io.sengokudaikon.shared.persistence.repository.RepositoryOutputPort
import io.sengokudaikon.dbfinder.domain.world.global.model.Language as ModelLanguage

interface LanguageRepositoryPort : RepositoryOutputPort<Language>, RepositoryCreatePort<ModelLanguage, Language>
