package com.elin4it.ssm.utils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

/**
 * 系统相关配置myconfig.properties
 * Created by jpan on 2016/4/28.
 */
public class ConfigPropertiesUtil {

    public static String getProperties(String key) {
        Properties properties = new Properties();
        InputStream in = ConfigPropertiesUtil.class.getResourceAsStream("/properties/myconfig.properties");
        try {
            properties.load(in);
        } catch (IOException e) {
            Logger logger = LoggerFactory.getLogger(ConfigPropertiesUtil.class);
            logger.error(e.getLocalizedMessage(), e);
        }

        return properties.getProperty(key);
    }

    static Properties properties = new Properties();

    public static String getProperties(String fn, String key) {

        InputStream in = ConfigPropertiesUtil.class.getResourceAsStream(fn);
        try {
            properties.load(in);
        } catch (IOException e) {
            Logger logger = LoggerFactory.getLogger(ConfigPropertiesUtil.class);
            logger.error(e.getLocalizedMessage(), e);
        }
        return properties.getProperty(key);
    }
}
