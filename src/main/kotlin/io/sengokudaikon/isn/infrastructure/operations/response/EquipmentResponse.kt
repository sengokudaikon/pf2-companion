package io.sengokudaikon.isn.infrastructure.operations.response

import io.sengokudaikon.isn.compendium.domain.equipment.model.EquipmentModel
import io.swagger.v3.oas.annotations.responses.ApiResponse
import kotlinx.serialization.Serializable

@ApiResponse(responseCode = "200", description = "Success")
@Serializable
class EquipmentResponse: Response<EquipmentModel>()
