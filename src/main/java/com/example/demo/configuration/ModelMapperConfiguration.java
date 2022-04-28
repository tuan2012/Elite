package com.example.demo.configuration;

import org.modelmapper.Converter;
import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ModelMapperConfiguration {
    @Bean(name = "modelMapperCommon")
    public ModelMapper modelMapper() {
        ModelMapper modelMapper = new ModelMapper();
        Converter<?, ?> convertNull =
                context -> context.getSource() == null ? context.getDestination() : context.getSource();
        modelMapper.addConverter(convertNull);

        return modelMapper;
    }


}
