package io.sengokudaikon.isn.compendium.ports.character

import io.sengokudaikon.isn.compendium.domain.classs.ClassFeatureModel
import io.sengokudaikon.isn.compendium.domain.classs.ClassModel
import io.sengokudaikon.isn.compendium.operations.character.classs.query.ClassFeatureQuery
import io.sengokudaikon.isn.compendium.operations.character.classs.query.ClassQuery
import io.sengokudaikon.isn.infrastructure.ports.ReadPort

interface ListClassPort : ReadPort<ClassQuery, List<ClassModel>>
interface ByIdClassPort : ReadPort<ClassQuery, ClassModel>
interface ByNameClassPort : ReadPort<ClassQuery, ClassModel>
interface ListClassFeatPort : ReadPort<ClassFeatureQuery, List<ClassFeatureModel>>
interface ByIdClassFeatPort : ReadPort<ClassFeatureQuery, ClassFeatureModel>
interface ByNameClassFeatPort : ReadPort<ClassFeatureQuery, ClassFeatureModel>
