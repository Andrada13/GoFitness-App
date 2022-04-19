package backend.models;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.ser.std.StdSerializer;

public class CustomTrainer extends StdSerializer<List<User>> {

    public CustomTrainer() {
        this(null);
    }

    public CustomTrainer(Class<List<User>> t) {
        super(t);
    }

    @Override
    public void serialize(
            List<User> trainers,
            JsonGenerator generator,
            SerializerProvider provider)
            throws IOException, JsonProcessingException {

        List<User> studs = new ArrayList<>();
        for (User s : trainers) {
            s.setPrograms(null);
            studs.add(s);
        }
        generator.writeObject(studs);
    }
}