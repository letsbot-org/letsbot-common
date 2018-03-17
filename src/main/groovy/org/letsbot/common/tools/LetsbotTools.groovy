package org.letsbot.common.tools

import org.apache.commons.codec.binary.Base64

import java.security.MessageDigest

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
 * Miscellaneous tools
 *
 * Date: 16/3/18
 * @author narciso.cerezo@gmail.com
 */
@SuppressWarnings("GroovyUnusedDeclaration")
class LetsbotTools {

    /**
     * Get a Base64 encoded MD5 digest of a file.
     * @param file the file
     * @return Base64 encoded MD5 digest
     */
    static String calculateMD5( File file ) {
        MessageDigest md = MessageDigest.getInstance("MD5")
        byte[] buffer = new byte[1024]
        file.withInputStream { input ->
            int count = input.read( buffer )
            while( count >= 0 ) {
                if( count > 0 ) {
                    md.update( buffer, 0, count )
                }
                count = input.read( buffer )
            }
        }
        byte[] digest = md.digest()
        byte[] bytes = Base64.encodeBase64(digest)
        new String( bytes )
    }

    /**
     * Get a Base64 encoded MD5 digest of a byte array.
     * @param data byte array
     * @return Base64 encoded MD5 digest
     */
    static String calculateMD5( byte[] data ) {
        MessageDigest md = MessageDigest.getInstance("MD5")
        md.update( data )
        byte[] digest = md.digest()
        byte[] bytes = Base64.encodeBase64(digest)
        new String( bytes )
    }

    /**
     * Format a time lapse in ms in a human readable string.
     *
     * @param timestamp time lapse in ms
     * @return formatted string
     */
    @SuppressWarnings("GroovyUnusedDeclaration")
    static String formatMS( final long timestamp ) {

        long t = timestamp
//        int ms = t % 1000
        t /= 1000
        int s = t % 60
        t /= 60
        int m = t % 60
        t /= 60
        int h = t % 24
        int d = t / 24

//        "${pad(d)} day(s) ${pad(h)} hour(s) ${pad(m)} minute(s) ${pad(s)} second(s) ${pad( ms, 3 )} millisecond(s)"
        "${pad(d)} day(s) ${pad(h)} hour(s) ${pad(m)} minute(s) ${pad(s)} second(s)"
    }

    /**
     * Pad an integer with 0s from the left
     * @param i number to pad
     * @param digits digits, 2 by default
     * @return string with padded number
     */
    static String pad( int i, int digits = 2 ) {
        Integer.toString( i ).padLeft( digits, '0' )
    }
}
