package co.develhope.meteoapp.network

import android.util.Log
import com.google.gson.JsonDeserializationContext
import com.google.gson.JsonDeserializer
import com.google.gson.JsonElement
import com.google.gson.JsonParseException
import com.google.gson.JsonPrimitive
import com.google.gson.JsonSerializationContext
import com.google.gson.JsonSerializer
import org.threeten.bp.LocalDate
import org.threeten.bp.LocalDateTime
import org.threeten.bp.OffsetDateTime
import org.threeten.bp.ZoneOffset
import org.threeten.bp.format.DateTimeFormatter
import java.lang.reflect.Type



class OffsetDateTimeTypeAdapter : JsonSerializer<OffsetDateTime>, JsonDeserializer<OffsetDateTime> {

    override fun serialize(
        src: OffsetDateTime,
        typeOfSrc: Type,
        context: JsonSerializationContext
    ): JsonElement = JsonPrimitive(formatter.format(src))

    @Throws(JsonParseException::class)
    override fun deserialize(
        json: JsonElement,
        typeOfT: Type,
        context: JsonDeserializationContext
    ): OffsetDateTime {
        val string = json.asString
        Log.d("TypeAdapter", "$string")

        return if(string.contains("T")){
            val date = LocalDateTime.parse(string).atZone(ZoneOffset.UTC)
            Log.d("TypeAdapter with Time", "$date")
            date.toOffsetDateTime()
        }else{
            val date = LocalDate.parse(string)
                .atStartOfDay(ZoneOffset.UTC)

            Log.d("TypeAdapter without time", "$date")
            date.toOffsetDateTime()
        }
    }

    companion object {
        private val formatter = DateTimeFormatter.ISO_OFFSET_DATE_TIME
    }
}