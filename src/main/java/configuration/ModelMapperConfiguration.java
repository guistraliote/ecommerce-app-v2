package configuration;

import io.quarkus.arc.DefaultBean;
import jakarta.enterprise.inject.Produces;
import jakarta.inject.Singleton;
import org.modelmapper.ModelMapper;

@Singleton
public class ModelMapperConfiguration {
    @Produces
    @DefaultBean
    public ModelMapper modelMapper() {
        return new ModelMapper();
    }
}
