package io.sengokudaikon.isn.compendium.ports.world

import io.sengokudaikon.isn.compendium.domain.booncurse.BoonCurseModel
import io.sengokudaikon.isn.compendium.operations.world.booncurse.query.BoonCurseQuery
import io.sengokudaikon.isn.infrastructure.ports.ReadPort

interface ListBoonCursePort : ReadPort<BoonCurseQuery, List<BoonCurseModel>>
interface ByIdBoonCursePort : ReadPort<BoonCurseQuery, BoonCurseModel>
interface ByNameBoonCursePort : ReadPort<BoonCurseQuery, BoonCurseModel>
