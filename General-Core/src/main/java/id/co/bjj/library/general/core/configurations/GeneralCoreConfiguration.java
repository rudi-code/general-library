package id.co.bjj.library.general.core.configurations;

import org.modelmapper.Conditions;
import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.MapperFeature;
import com.fasterxml.jackson.databind.ObjectMapper;

@Configuration
@ComponentScan({ "id.co.bjj.library.general.core.utils", "id.co.bjj.library.general.service.settings",
		"id.co.bjj.library.general.persistence.querybuilders" })
public class GeneralCoreConfiguration {

    /**
     * prevent / avoid data lost from transient property in model database
     * @author Steve Sentosa, 21 May 2024
     * @return
     */
    @Bean
    ModelMapper modelMapper() {
		ModelMapper modelMapper = new ModelMapper();
		modelMapper.getConfiguration().setPropertyCondition(Conditions.isNotNull());
		return modelMapper;
	}

	/**
	 * Setting default object mapper
	 * @author Steve Sentosa, 21 May 2024
	 * @return
	 */
	@SuppressWarnings("deprecation")
	@Bean
	ObjectMapper objectMapper() {
		ObjectMapper objectMapper = new ObjectMapper().setSerializationInclusion(Include.NON_NULL);
		objectMapper.enable(MapperFeature.ACCEPT_CASE_INSENSITIVE_ENUMS);
		objectMapper.disable(DeserializationFeature.ACCEPT_FLOAT_AS_INT);
		objectMapper.enable(DeserializationFeature.ACCEPT_EMPTY_STRING_AS_NULL_OBJECT);
		return objectMapper;
	}
}