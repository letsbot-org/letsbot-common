package org.letsbot.common.providers

import org.letsbot.common.LetsBotException

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
 * Exception for WebServerHooks.
 *
 * Date: 16/3/18
 * @author narciso.cerezo@gmail.com
 * @since 1.0.1
 */
class WebServerHookException extends LetsBotException {

    WebServerHookException() {
    }

    WebServerHookException( final String var1 ) {
        super( var1 )
    }

    WebServerHookException( final String var1, final Throwable var2 ) {
        super( var1, var2 )
    }

    WebServerHookException( final Throwable var1 ) {
        super( var1 )
    }

    WebServerHookException( final String var1, final Throwable var2, final boolean var3, final boolean var4 ) {
        super( var1, var2, var3, var4 )
    }
}
