package org.letsbot.common.tools

import java.math.RoundingMode
import java.text.ParseException
import java.text.SimpleDateFormat

/*
 Copyright 2018 Narciso Cerezo Jimenez

 Licensed under the Apache License, Version 2.0 (the "License");
 you may not use this file except in compliance with the License.
 You may obtain a copy of the License at

 http://www.apache.org/licenses/LICENSE-2.0

 Unless required by applicable law or agreed to in writing, software
 distributed under the License is distributed on an "AS IS" BASIS,
 WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 See the License for the specific language governing permissions and
 limitations under the License.
 */

/**
 * Utility methods to convert objects between types.
 *
 * Date: 10/11/15
 * @author narciso.cerezo@gmail.com
 */
@SuppressWarnings("GroovyUnusedDeclaration")
class Conversion {

    public static final String DATE_EXPORT_FORMAT = "yyyy-MM-dd'T'HH:mm:ss.SSSZ"

    @SuppressWarnings("SpellCheckingInspection")
    private static datePatterns = [
            "yyyy/MM/dd'T'HH:mm:ss.SSSZ": /\d{4}\/\d{2}\/\d{2}T\d{2}:\d{2}:\d{2}\.\d{3}[+-]\d{4}/,
            "yyyy/MM/dd'T'HH:mm:ss.SSS": /\d{4}\/\d{2}\/\d{2}T\d{2}:\d{2}:\d{2}\.\d{3}/,
            "yyyy/MM/dd'T'HH:mm:ss": /\d{4}\/\d{2}\/\d{2}T\d{2}:\d{2}:\d{2}/,
            "yyyy/MM/dd'T'HH:mm:ssZ": /\d{4}\/\d{2}\/\d{2}T\d{2}:\d{2}:\d{2}[+-]\d{4}/,
            "yyyy/MM/dd'T'HH:mm": /\d{4}\/\d{2}\/\d{2}T\d{2}:\d{2}/,
            "yyyy/MM/dd'T'HH:mmZ": /\d{4}\/\d{2}\/\d{2}T\d{2}:\d{2}[+-]\d{4}/,
            "yyyy/MM/dd HH:mm:ss.SSSZ": /\d{4}\/\d{2}\/\d{2} \d{2}:\d{2}:\d{2}\.\d{3}[+-]\d{4}/,
            "yyyy/MM/dd HH:mm:ss.SSS": /\d{4}\/\d{2}\/\d{2} \d{2}:\d{2}:\d{2}\.\d{3}/,
            "yyyy/MM/dd HH:mm:ss": /\d{4}\/\d{2}\/\d{2} \d{2}:\d{2}:\d{2}/,
            "yyyy/MM/dd HH:mm:ssZ": /\d{4}\/\d{2}\/\d{2} \d{2}:\d{2}:\d{2}[+-]\d{4}/,
            "yyyy/MM/dd HH:mm": /\d{4}\/\d{2}\/\d{2} \d{2}:\d{2}/,
            "yyyy/MM/dd HH:mmZ": /\d{4}\/\d{2}\/\d{2} \d{2}:\d{2}[+-]\d{4}/,
            "yyyy/MM/dd": /\d{4}\/\d{2}\/\d{2}/,
            "yyyy/MM/ddZ": /\d{4}\/\d{2}\/\d{2}[+-]\d{4}/,

            "HH:mm:ss.SSSZ": /\d{2}:\d{2}:\d{2}\.\d{3}[+-]\d{4}/,
            "HH:mm:ss.SSS": /\d{2}:\d{2}:\d{2}\.\d{3}/,
            "HH:mm:ss": /\d{2}:\d{2}:\d{2}/,
            "HH:mm:ssZ": /\d{2}:\d{2}:\d{2}[+-]\d{4}/,
            "HH:mm": /\d{2}:\d{2}/,
            "HH:mmZ": /\d{2}:\d{2}[+-]\d{4}/,

            "yyyy-MM-dd'T'HH:mm:ss.SSSZ": /\d{4}-\d{2}-\d{2}T\d{2}:\d{2}:\d{2}\.\d{3}[+-]\d{4}/,
            "yyyy-MM-dd'T'HH:mm:ss.SSS": /\d{4}-\d{2}-\d{2}T\d{2}:\d{2}:\d{2}\.\d{3}/,
            "yyyy-MM-dd'T'HH:mm:ss": /\d{4}-\d{2}-\d{2}T\d{2}:\d{2}:\d{2}/,
            "yyyy-MM-dd'T'HH:mm:ssZ": /\d{4}-\d{2}-\d{2}T\d{2}:\d{2}:\d{2}[+-]\d{4}/,
            "yyyy-MM-dd'T'HH:mm": /\d{4}-\d{2}-\d{2}T\d{2}:\d{2}/,
            "yyyy-MM-dd'T'HH:mmZ": /\d{4}-\d{2}-\d{2}T\d{2}:\d{2}[+-]\d{4}/,
            "yyyy-MM-dd HH:mm:ss.SSSZ": /\d{4}-\d{2}-\d{2} \d{2}:\d{2}:\d{2}\.\d{3}[+-]\d{4}/,
            "yyyy-MM-dd HH:mm:ss.SSS": /\d{4}-\d{2}-\d{2} \d{2}:\d{2}:\d{2}\.\d{3}/,
            "yyyy-MM-dd HH:mm:ss": /\d{4}-\d{2}-\d{2} \d{2}:\d{2}:\d{2}/,
            "yyyy-MM-dd HH:mm:ssZ": /\d{4}-\d{2}-\d{2} \d{2}:\d{2}:\d{2}[+-]\d{4}/,
            "yyyy-MM-dd HH:mm": /\d{4}-\d{2}-\d{2} \d{2}:\d{2}/,
            "yyyy-MM-dd HH:mmZ": /\d{4}-\d{2}-\d{2} \d{2}:\d{2}[+-]\d{4}/,
            "yyyy-MM-dd": /\d{4}-\d{2}-\d{2}/,
            "yyyy-MM-ddZ": /\d{4}-\d{2}-\d{2}[+-]\d{4}/,

            "yyyyMMddHHmmssSSSZ": /\d{4}\d{2}\d{2}\d{2}\d{2}\d{2}\d{3}[+-]\d{4}/,
            "yyyyMMddHHmmssSSS": /\d{4}\d{2}\d{2}\d{2}\d{2}\d{2}\d{3}/,
            "yyyyMMddHHmmss": /\d{4}\d{2}\d{2}\d{2}\d{2}\d{2}/,
            "yyyyMMddHHmmssZ": /\d{4}\d{2}\d{2}\d{2}\d{2}\d{2}[+-]\d{4}/,
            "yyyyMMddHHmm": /\d{4}\d{2}\d{2}\d{2}\d{2}/,
            "yyyyMMddHHmmZ": /\d{4}\d{2}\d{2}\d{2}\d{2}[+-]\d{4}/,
            "yyyyMMdd": /\d{4}\d{2}\d{2}/,
            "yyyyMMddZ": /\d{4}\d{2}\d{2}[+-]\d{4}/,
    ]

    private static decimalPattern = /^[-+]?\d*[,.]\d*$/
    private static naturalPattern = /^[-+]?\d+$/

    private static Map<String, SimpleDateFormat> formatCache = [:]
    static {
        for( String format : datePatterns.keySet() ) {
            formatCache.put( format, new SimpleDateFormat( format ) )
        }
    }


    static boolean toBoolean( Object object, boolean defaultValue = false ) {
        asBoolean( object, defaultValue )
    }

    static Boolean asBoolean( Object object, Boolean defaultValue = null ) {
        try {
            if( object == null || object instanceof ConfigObject ) {
                return defaultValue
            }
            else if( object instanceof Boolean ) {
                return object as Boolean
            }
            else if( object instanceof Number ) {
                return (object as Number).intValue() != 0
            }
            else {
                def string = object.toString().toLowerCase().trim()
                return string == "true" || string == "yes" || string == "si" || string == "1"
            }
        }
        catch( Exception ignored ) {
            return defaultValue
        }
    }

    static int toInt( Object object, int defaultValue = 0 ) {
        asInt( object, defaultValue )
    }

    static Integer asInt( Object object, Integer defaultValue = null ) {
        try {
            if( object == null ) {
                return defaultValue
            }
            else if( object instanceof Boolean ) {
                return object ? 1 : 0
            }
            else if( object instanceof Number ) {
                return (object as Number).intValue()
            }
            else {
                def string = object.toString().trim()
                if( string == null ) {
                    defaultValue
                }
                else if( string.matches( naturalPattern ) ) {
                    try {
                        Integer.parseInt( string )
                    }
                    catch( NumberFormatException ignored ) {
                        defaultValue
                    }
                }
                else if( string.matches( decimalPattern ) ) {
                    new BigDecimal( string.replaceAll( ",", "." ) )?.setScale( 0, RoundingMode.HALF_EVEN )?.intValue()
                }
                else {
                    defaultValue
                }
            }
        }
        catch( Exception ignored ) {
            return defaultValue
        }
    }

    static long toLong( Object object, long defaultValue = 0L ) {
        asLong( object, defaultValue )
    }

    static Long asLong( Object object, Long defaultValue = null ) {
        try {
            if( object == null ) {
                return defaultValue
            }
            else if( object instanceof Number ) {
                return (object as Number).longValue()
            }
            else if( object instanceof Boolean ) {
                return object ? 1L : 0L
            }
            else {
                def string = object.toString().trim()
                if( string == null ) {
                    defaultValue
                }
                else if( string.matches( naturalPattern ) ) {
                    try {
                        Long.parseLong( string )
                    }
                    catch( NumberFormatException ignored ) {
                        defaultValue
                    }
                }
                else if( string.matches( decimalPattern ) ) {
                    new BigDecimal( string.replaceAll( ",", "." ) )?.setScale( 0, RoundingMode.HALF_EVEN )?.longValue()
                }
                else {
                    defaultValue
                }
            }
        }
        catch( Exception ignored ) {
            return defaultValue
        }
    }

    static double toDouble( Object object, double defaultValue = 0.0d ) {
        asDouble( object, defaultValue )
    }

    static Double asDouble( Object object, Double defaultValue = null ) {
        try {
            if( object == null ) {
                return defaultValue
            }
            else if( object instanceof Number ) {
                return (object as Number).doubleValue()
            }
            else if( object instanceof Boolean ) {
                return object ? 1.0d : 0.0d
            }
            else {
                def string = object.toString().trim()
                //noinspection GrUnresolvedAccess
                return string.isDouble() ? string.toDouble() : defaultValue
            }
        }
        catch( Exception ignored ) {
            return defaultValue
        }
    }

    static BigDecimal toBigDecimal(
            Object object,
            BigDecimal defaultValue = BigDecimal.ZERO,
            Integer scale = null,
            RoundingMode roundingMode = RoundingMode.HALF_UP
    ) {
        try {
            BigDecimal value = defaultValue
            if( object instanceof BigDecimal ) {
                value = object
            }
            else if( object instanceof Boolean ) {
                value = object ? BigDecimal.ONE : BigDecimal.ZERO
            }
            else if( object instanceof Number ) {
                try {
                    value = new BigDecimal( object.toString() )
                }
                catch( NumberFormatException ignored ) {
                    value = defaultValue
                }
            }
            else if( object != null ) {
                try {
                    value = new BigDecimal( object.toString().trim() )
                }
                catch( NumberFormatException ignored ) {
                }
            }
            if( scale != null && value != null ) {
                value = value.setScale( scale, roundingMode )
            }
            return value
        }
        catch( Exception ignored ) {
            return defaultValue
        }
    }

    static Date toDate( Object object, Date defaultValue = null ) {
        try {
            if( object == null ) {
                return defaultValue
            }
            else if( object instanceof Date ) {
                return object
            }
            else if( object instanceof Number ) {
                return new Date( (object as Number).longValue() )
            }
            else {
                def text = object.toString()
                SimpleDateFormat sdf = null
                for( String format : datePatterns.keySet() ) {
                    if( text.matches( datePatterns.get( format ) ) ) {
                        sdf = formatCache.get( format )
                        break
                    }
                }
                if( sdf ) {
                    try {
                        return sdf.parse( text )
                    }
                    catch( ParseException ignored ) {
                        return defaultValue
                    }
                }
            }
            return defaultValue
        }
        catch( Exception ignored ) {
            return defaultValue
        }
    }

    static Locale toLocale( Object source, Locale defaultValue = null ) {
        try {
            if( source == null ) {
                return defaultValue
            }
            else if( source instanceof Locale ) {
                return source
            }
            else {
                String string = source.toString().trim().replaceAll( "-", "_" )
                if( string.length() > 0 ) {
                    def parts = source.toString().split( "[_]" )
                    if( parts.length == 1 ) {
                        return new Locale( parts[0] )
                    }
                    else {
                        return new Locale( parts[0], parts[1] )
                    }
                }
                else {
                    return defaultValue
                }
            }
        }
        catch( Exception ignored ) {
            return defaultValue
        }
    }

    static String applyMaxSize( String string, int maxSize, boolean ellipsis = false ) {
        if( string && string.length() > maxSize ) {
            ellipsis ? string.substring( 0, maxSize - 3 ) + "..." : string.substring( 0, maxSize )
        }
        else {
            string
        }
    }

    /**
     * Allows a cast to be made on an object safely, by returning the object only if it's an instance of the class.
     *
     * @param object object ot cast
     * @param clazz class to be cast to
     * @param defaultValue default value if object is null or not an instance of the class, default to null
     * @return the object if it's an instance of the class and not null, default value otherwise
     */
    @SuppressWarnings("GroovyUnusedDeclaration")
    static safeCast( object, Class clazz, defaultValue = null ) {
        if( clazz != null && object != null ) {
            clazz.isInstance( object ) ? object : defaultValue
        }
        else {
            return defaultValue
        }
    }

}
