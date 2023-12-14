package io.sengokudaikon.isn.infrastructure.operations

interface Query {
    interface All<T> : Query { val page: Int; val size: Int }
    interface ById<T> : Query { val id: String }
    interface ByName<T> : Query { val name: String }
}
