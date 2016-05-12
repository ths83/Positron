package fr.univtln.groupc.entities;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;

import java.io.IOException;
import java.util.List;

/**
 * Created by marti on 12/05/2016.
 */
public class CCustomListResonatorSerializer extends JsonSerializer<List<CResonatorEntity>>{
    @Override
    public void serialize(List<CResonatorEntity> cResonatorEntities, JsonGenerator jsonGenerator, SerializerProvider serializerProvider) throws IOException, JsonProcessingException {
        
    }
}
