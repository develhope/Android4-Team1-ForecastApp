package co.develhope.meteoapp.network.domainmodel

data class Place(
    val name: String,
    val latitude: Double,
    val longitude: Double,
    val region: String
){
    override fun equals(other: Any?): Boolean {
        return other is Place && name == other.name && region == other.region
    }

    override fun hashCode(): Int {
        var result = name.hashCode()
        result = 31 * result + region.hashCode()
        return result
    }
}
