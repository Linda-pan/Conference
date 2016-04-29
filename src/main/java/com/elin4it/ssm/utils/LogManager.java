package com.elin4it.ssm.utils;

import org.slf4j.Logger;
import org.slf4j.Marker;
import org.slf4j.MarkerFactory;

/**
 * Created by jpan on 2016/4/28.
 */
public class LogManager {

    public static void d(Logger logger, String message) {
        if(logger == null) return;

        logger.debug(message);
    }

    public static void d(Logger logger, String format, Object... args) {
        if(logger == null) return;

        logger.debug(format, args);
    }

    public static void d(Logger logger, String marker, String message) {
        if(logger == null) return;

        Marker tMarker = MarkerFactory.getMarker(marker);

        logger.debug(tMarker, message);
    }

    public static void d(Logger logger, String marker, String format, Object... args) {
        if(logger == null) return;

        Marker tMarker = MarkerFactory.getMarker(marker);

        logger.debug(tMarker, format, args);
    }

    public static void e(Logger logger, String message, Throwable t) {
        if(logger == null) return;

        logger.error(message, t);
    }

    public static void e(Logger logger, String marker, String message, Throwable t) {
        if(logger == null) return;

        Marker tMarker = MarkerFactory.getMarker(marker);

        logger.error(tMarker, message, t);
    }

    public static void e(Logger logger, String format, Object... args) {
        if(logger == null) return;

        logger.error(format, args);
    }

    public static void e(Logger logger, String marker, String message) {
        if(logger == null) return;

        Marker tMarker = MarkerFactory.getMarker(marker);

        logger.error(tMarker, message);
    }

    public static void e(Logger logger, String marker, String format, Object... args) {
        if(logger == null) return;

        Marker tMarker = MarkerFactory.getMarker(marker);

        logger.error(tMarker, format, args);
    }
}
