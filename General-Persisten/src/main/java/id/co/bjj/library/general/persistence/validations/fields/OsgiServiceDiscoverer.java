package id.co.bjj.library.general.persistence.validations.fields;

import java.util.Collections;
import java.util.List;

import org.hibernate.validator.HibernateValidator;

import jakarta.validation.ValidationProviderResolver;
import jakarta.validation.spi.ValidationProvider;

public class OsgiServiceDiscoverer implements ValidationProviderResolver {

	@Override
	public List<ValidationProvider<?>> getValidationProviders() {
		return Collections.<ValidationProvider<?>>singletonList(new HibernateValidator());
	}
}
