package data.mapper

interface Mapper <Entity, Response> {
    fun invoke(response: Response): Entity
}