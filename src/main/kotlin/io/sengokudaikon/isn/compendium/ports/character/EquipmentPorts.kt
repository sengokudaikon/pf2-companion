package io.sengokudaikon.isn.compendium.ports.character

import io.sengokudaikon.isn.compendium.domain.equipment.model.ArmorModel
import io.sengokudaikon.isn.compendium.domain.equipment.model.EquipmentModel
import io.sengokudaikon.isn.compendium.domain.equipment.model.ShieldModel
import io.sengokudaikon.isn.compendium.domain.equipment.model.WeaponModel
import io.sengokudaikon.isn.compendium.operations.character.equipment.query.ArmorQuery
import io.sengokudaikon.isn.compendium.operations.character.equipment.query.EquipmentQuery
import io.sengokudaikon.isn.compendium.operations.character.equipment.query.ShieldQuery
import io.sengokudaikon.isn.compendium.operations.character.equipment.query.WeaponQuery
import io.sengokudaikon.isn.infrastructure.ports.ReadPort

interface ListEquipmentPort : ReadPort<EquipmentQuery, List<EquipmentModel>>
interface ByIdEquipmentPort : ReadPort<EquipmentQuery, EquipmentModel>
interface ByNameEquipmentPort : ReadPort<EquipmentQuery, EquipmentModel>
interface ListArmorPort : ReadPort<ArmorQuery, List<ArmorModel>>
interface ByIdArmorPort : ReadPort<ArmorQuery, ArmorModel>
interface ByNameArmorPort : ReadPort<ArmorQuery, ArmorModel>
interface ListWeaponPort : ReadPort<WeaponQuery, List<WeaponModel>>
interface ByIdWeaponPort : ReadPort<WeaponQuery, WeaponModel>
interface ByNameWeaponPort : ReadPort<WeaponQuery, WeaponModel>
interface ListShieldPort : ReadPort<ShieldQuery, List<ShieldModel>>
interface ByIdShieldPort : ReadPort<ShieldQuery, ShieldModel>
interface ByNameShieldPort : ReadPort<ShieldQuery, ShieldModel>
