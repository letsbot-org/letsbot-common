package org.letsbot.common.tools

import spock.lang.Specification
import spock.lang.Unroll

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
 * Specification for LetsbotTools.
 *
 * Date: 17/3/18
 * @author narciso.cerezo@gmail.com
 */
class LetsbotToolsSpecification extends Specification {

    @Unroll('Hash with MD5 #bytes into #expectedResult')
    "Hash files into base 64 encoded MD5 digests"() {

        given:
        def file = File.createTempFile( "spock", "tools" )

        when:
        file << bytes
        def result = LetsbotTools.calculateMD5( file )

        then:
        result == expectedResult
        file.delete()

        where:
        expectedResult                  | bytes
        "TcRdfMM1iDk8vyowFoepgQ=="      | "32090asdksaldsmo2392321m, cxndoio1"
    }

    @Unroll('Hash with MD5 #bytes into #expectedResult')
    "Hash byte arrays into base 64 encoded MD5 digests"() {

        when:
        def result = LetsbotTools.calculateMD5( bytes.bytes )

        then:
        result == expectedResult

        where:
        expectedResult                  | bytes
        "TcRdfMM1iDk8vyowFoepgQ=="      | "32090asdksaldsmo2392321m, cxndoio1"
    }

    @Unroll('Display #timestamp into #expectedResult')
    "Display a number of milliseconds as a time lapse in human readable form"() {

        when:
        def result = LetsbotTools.formatMS( timestamp )

        then:
        result == expectedResult

        where:
        expectedResult                                  | timestamp
        "00 day(s) 00 hour(s) 00 minute(s) 00 second(s)" | 0
        "00 day(s) 00 hour(s) 00 minute(s) 00 second(s)" | 50
        "00 day(s) 00 hour(s) 00 minute(s) 00 second(s)" | 999
        "00 day(s) 00 hour(s) 00 minute(s) 01 second(s)" | 1000
        "00 day(s) 00 hour(s) 00 minute(s) 59 second(s)" | 59999
        "02 day(s) 00 hour(s) 00 minute(s) 00 second(s)" | 172800000
    }

    @Unroll('Pad #number into #expectedResult')
    "Pad integers into different numbers of digits with leading zeroes"() {

        when:
        def result = LetsbotTools.pad( number, digits )

        then:
        result == expectedResult

        where:
        expectedResult      | number    | digits
        "0"                 | 0         | 0
        "0"                 | 0         | 1
        "00"                | 0         | 2
        "000"               | 0         | 3
        "1"                 | 1         | 0
        "1"                 | 1         | 1
        "01"                | 1         | 2
        "001"               | 1         | 3
        "11"                | 11        | 0
        "11"                | 11        | 1
        "11"                | 11        | 2
        "011"               | 11        | 3
        "111"               | 111       | 0
        "111"               | 111       | 1
        "111"               | 111       | 2
        "111"               | 111       | 3
    }

}
