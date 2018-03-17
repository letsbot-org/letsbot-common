package org.letsbot.common.tools

import java.text.ParseException

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
 * Useful methods for process handling.
 *
 * Date: 16/3/18
 * @author narciso.cerezo@gmail.com
 */
@SuppressWarnings("GroovyUnusedDeclaration")
class ProcessTools {

    /**
     * Very simple process execution: run the command, wait for it by consuming its output and
     * return the output of the command.
     *
     * @param command command line
     * @return process output
     * @throws Exception on errors parsing or running the process
     */
    static String runProcess( String command ) throws Exception {
        ProcessBuilder pb = new ProcessBuilder( parseCommand( command ) )
        Process process = pb.start()
        process.text
    }

    /**
     * Very simple process execution: run the command, wait for it by consuming its output and
     * return the output of the command.
     *
     * @param command command line
     * @return process output
     * @throws Exception on errors parsing or running the process
     */
    static String runProcess( List<String> command ) throws Exception {
        ProcessBuilder pb = new ProcessBuilder( command )
        Process process = pb.start()
        process.text
    }

    /**
     * Parse a command line into a list of elements as preferred by ProcessBuilder, preserving
     * and honoring delimiters like &quot; and &apos;.
     *
     * @param command the string with the raw command
     * @return a list of separate elements for ProcessBuilder, might be empty (if command is empty or null)
     * @throws ParseException
     */
    static List<String> parseCommand( String command ) throws ParseException {
        List<String> commandList
        if( command == null || command.trim().length() == 0 ) {
            return Collections.emptyList()
        }
        else {
            char[] chars = command.chars
            commandList = []
            StringBuilder current = new StringBuilder()
            char delimiter = 0
            for( char c : chars ) {
                if( delimiter ) {
                    if( c == delimiter ) {
                        commandList << "${delimiter}${current}${delimiter}".toString()
                        delimiter = 0
                        current.delete( 0, current.length() )
                    }
                    else {
                        current.append c
                    }
                }
                else {
                    if( c == '"'.charAt( 0 ) ) {
                        if( current.length() ) {
                            commandList << current.toString()
                            current.delete( 0, current.length() )
                        }
                        delimiter = '"'.charAt( 0 )
                    }
                    else if( c == "'".charAt( 0 ) ) {
                        if( current.length() ) {
                            commandList << current.toString()
                            current.delete( 0, current.length() )
                        }
                        delimiter = "'".charAt( 0 )
                    }
                    else if( c == ' '.charAt( 0 ) ) {
                        if( current.length() ) {
                            commandList << current.toString()
                            current.delete( 0, current.length() )
                        }
                    }
                    else {
                        current.append c
                    }
                }
            }
            if( delimiter ) {
                throw new ParseException( "Invalid reload command: $command", 0 )
            }
            if( current.length() ) {
                commandList << current.toString()
            }
        }

        return commandList
    }

}
