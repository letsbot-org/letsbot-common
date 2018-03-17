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
 * Specification for ProcessTools.
 *
 * Date: 17/3/18
 * @author narciso.cerezo@gmail.com
 */
class ProcessToolsSpecification extends Specification {

    @Unroll('Converts #string into: #expectedResult')
    "Can convert different command line strings into lists for ProcessBuilder"() {

        when:
        def result = ProcessTools.parseCommand(string)

        then:
        result == expectedResult

        where:
        expectedResult      | string
        []                  | null
        []                  | ""
        []                  | "     "
        ['pwd']             | "pwd"
        ['pwd']             | " pwd"
        ['pwd']             | " pwd "
        ['pwd']             | "pwd "
        ['echo','"hi"']     | 'echo "hi"'
        ['echo','"hi"']     | 'echo      "hi"'
    }

    @Unroll('Run #command with #expectedResult')
    "Run system commands"() {

        when:
        def result = ProcessTools.runProcess( command )?.trim()

        then:
        result == expectedResult

        where:
        expectedResult      | command
        "hi"                | "echo hi"
    }
}
