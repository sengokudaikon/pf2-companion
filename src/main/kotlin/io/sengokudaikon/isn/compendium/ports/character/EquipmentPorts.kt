package io.sengokudaikon.isn.compendium.ports.character

import io.sengokudaikon.isn.compendium.domain.equipment.model.ArmorModel
import io.sengokudaikon.isn.compendium.domain.equipment.model.BackpackModel
import io.sengokudaikon.isn.compendium.domain.equipment.model.ConsumableModel
import io.sengokudaikon.isn.compendium.domain.equipment.model.EquipmentModel
import io.sengokudaikon.isn.compendium.domain.equipment.model.KitModel
import io.sengokudaikon.isn.compendium.domain.equipment.model.ShieldModel
import io.sengokudaikon.isn.compendium.domain.equipment.model.TreasureModel
import io.sengokudaikon.isn.compendium.domain.equipment.model.WeaponModel
import io.sengokudaikon.isn.compendium.operations.character.equipment.query.ArmorQuery
import io.sengokudaikon.isn.compendium.operations.character.equipment.query.BackpackQuery
import io.sengokudaikon.isn.compendium.operations.character.equipment.query.ConsumableQuery
import io.sengokudaikon.isn.compendium.operations.character.equipment.query.EquipmentQuery
import io.sengokudaikon.isn.compendium.operations.character.equipment.query.KitQuery
import io.sengokudaikon.isn.compendium.operations.character.equipment.query.ShieldQuery
import io.sengokudaikon.isn.compendium.operations.character.equipment.query.TreasureQuery
import io.sengokudaikon.isn.compendium.operations.character.equipment.query.WeaponQuery
import io.sengokudaikon.isn.infrastructure.ports.ReadPort

interface ListEquipmentPort : ReadPort<EquipmentQuery, List<EquipmentModel>>
interface ByIdEquipmentPort : ReadPort<EquipmentQuery, EquipmentModel>
interface ByNameEquipmentPort : ReadPort<EquipmentQuery, EquipmentModel>
interface ListKitPort : ReadPort<KitQuery, List<KitModel>>
interface ByIdKitPort : ReadPort<KitQuery, KitModel>
interface ByNameKitPort : ReadPort<KitQuery, KitModel>
interface ListTreasurePort : ReadPort<TreasureQuery, List<TreasureModel>>
interface ByIdTreasurePort : ReadPort<TreasureQuery, TreasureModel>
interface ByNameTreasurePort : ReadPort<TreasureQuery, TreasureModel>
interface ListBackpackPort : ReadPort<BackpackQuery, List<BackpackModel>>
interface ByIdBackpackPort : ReadPort<BackpackQuery, BackpackModel>
interface ByNameBackpackPort : ReadPort<BackpackQuery, BackpackModel>
interface ListConsumablePort : ReadPort<ConsumableQuery, List<ConsumableModel>>
interface ByIdConsumablePort : ReadPort<ConsumableQuery, ConsumableModel>
interface ByNameConsumablePort : ReadPort<ConsumableQuery, ConsumableModel>
interface ListArmorPort : ReadPort<ArmorQuery, List<ArmorModel>>
interface ByIdArmorPort : ReadPort<ArmorQuery, ArmorModel>
interface ByNameArmorPort : ReadPort<ArmorQuery, ArmorModel>
interface ListWeaponPort : ReadPort<WeaponQuery, List<WeaponModel>>
interface ByIdWeaponPort : ReadPort<WeaponQuery, WeaponModel>
interface ByNameWeaponPort : ReadPort<WeaponQuery, WeaponModel>
interface ListShieldPort : ReadPort<ShieldQuery, List<ShieldModel>>
interface ByIdShieldPort : ReadPort<ShieldQuery, ShieldModel>
interface ByNameShieldPort : ReadPort<ShieldQuery, ShieldModel>
