package backend.models;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.ser.std.StdSerializer;

public class CustomProgram extends StdSerializer<List<Program>> {

    public CustomProgram() {
        this(null);
    }

    public CustomProgram(Class<List<Program>> t) {
        super(t);
    }

    @Override
    public void serialize(
            List<Program> programs,
            JsonGenerator generator,
            SerializerProvider provider)
            throws IOException, JsonProcessingException {

        List<Program> studs = new ArrayList<>();
        for (Program s : programs) {
            s.setTrainer(null);
            studs.add(s);
        }
        generator.writeObject(studs);
    }
}