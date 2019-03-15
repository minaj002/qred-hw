package com.qred;


import ma.glasnost.orika.MapperFactory;
import ma.glasnost.orika.MappingContext;
import ma.glasnost.orika.converter.BidirectionalConverter;
import ma.glasnost.orika.converter.ConverterFactory;
import ma.glasnost.orika.metadata.ClassMapBuilder;
import ma.glasnost.orika.metadata.Type;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Date;

public abstract class ObjectMapperConfiguration<A, B> {

    public abstract Class<A> getA();

    public abstract Class<B> getB();

    public void mapping(MapperFactory mapperFactory) {

        ClassMapBuilder<A, B> classMapBuilder = mapperFactory.classMap(getA(), getB());
        setupConverters(mapperFactory.getConverterFactory());
        fieldMapping(classMapBuilder);
        classMapBuilder.register();

    }

    protected void setupConverters(ConverterFactory converterFactory) {

        converterFactory.registerConverter(new BidirectionalConverter<LocalDate, LocalDate>() {
            @Override
            public LocalDate convertTo(LocalDate source, Type<LocalDate> destinationType, MappingContext mappingContext) {
//                DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
//                try {
//                    return new SimpleDateFormat("dd-MM-yyyy").parse(source);
//                } catch (ParseException e) {
//                    return null;
//                }
                return source;
            }

            @Override
            public LocalDate convertFrom(LocalDate source, Type<LocalDate> destinationType, MappingContext mappingContext) {
//                return new SimpleDateFormat("dd-MM-yyyy").format(source);
                return source;
            }
        });


    }

    protected void fieldMapping(ClassMapBuilder<A, B> classMapBuilder) {
        classMapBuilder.byDefault();
    }

}
