package fiddlecomputers.mods.oreplanets.utils;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import fiddlecomputers.mods.oreplanets.OrePlanets;
import fiddlecomputers.mods.oreplanets.config.ConfigManagerOP;

public class LoggerOP
{
    private static final Logger LOG = LogManager.getLogger("More Planets");
    private static final Logger LOG_DEBUG = LogManager.getLogger("More Planets Debug");

    public static void info(String message)
    {
        LoggerOP.LOG.info(message);
    }

    public static void error(String message)
    {
        LoggerOP.LOG.error(message);
    }

    public static void warning(String message)
    {
        LoggerOP.LOG.warn(message);
    }

    public static void debug(String message)
    {
        if (ConfigManagerOP.oreplanets_general.enableDebug || OrePlanets.isDevelopment)
        {
            LoggerOP.LOG_DEBUG.info(message);
        }
    }

    public static void info(String message, Object... obj)
    {
        LoggerOP.LOG.info(message, obj);
    }

    public static void error(String message, Object... obj)
    {
        LoggerOP.LOG.error(message, obj);
    }

    public static void warning(String message, Object... obj)
    {
        LoggerOP.LOG.warn(message, obj);
    }

    public static void debug(String message, Object... obj)
    {
        if (ConfigManagerOP.oreplanets_general.enableDebug || OrePlanets.isDevelopment)
        {
            LoggerOP.LOG_DEBUG.info(message, obj);
        }
    }
}