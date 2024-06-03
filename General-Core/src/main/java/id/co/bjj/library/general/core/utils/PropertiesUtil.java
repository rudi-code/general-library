package id.co.bjj.library.general.core.utils;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Component;

import lombok.extern.slf4j.Slf4j;

/**
 * Utility for reading setting from application.properties
 * 
 * @author Steve Sentosa, 20 May 2024
 */
@Component
@Slf4j
public class PropertiesUtil {
	private PropertiesUtil() {
	}

	private static final String FILENAME = "application";

	public static String get(String key) {
		Properties prop = new Properties();
		String file = FILENAME;
		InputStream input = PropertiesUtil.class.getClassLoader().getResourceAsStream(file + ".properties");
		try {
			prop.load(input);
			return prop.getProperty(key);
		} catch (IOException ex) {
			if (log.isTraceEnabled()) {
				log.trace(ex.getMessage(), ex);
			} else {
				log.error(ex.getMessage(), ex);
			}
			return "";
		} finally {
			if (input != null) {
				try {
					input.close();
				} catch (IOException ex) {
					if (log.isTraceEnabled()) {
						log.trace(ex.getMessage(), ex);
					} else {
						log.error(ex.getMessage(), ex);
					}
				}
			}
		}
	}

	public static String get(String key, String defaultValue) {
		String result;
		String configuredValue = PropertiesUtil.get(key);
		if (StringUtils.isEmpty(configuredValue)) {
			result = defaultValue;
		} else {
			result = configuredValue;
		}
		return result;
	}
}
