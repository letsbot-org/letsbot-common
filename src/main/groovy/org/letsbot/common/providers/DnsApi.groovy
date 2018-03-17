package org.letsbot.common.providers

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
 * Interface for DNS API providers.
 *
 * Date: 14/3/18
 * @author narciso.cerezo@gmail.com
 */
@SuppressWarnings("GroovyUnusedDeclaration")
interface DnsApi {

    /**
     * Create a TXT DNS record for the DNS challenge.
     *
     * @param zone zone (like letsbot.org)
     * @param subDomain usually _acme_challenge or similar
     * @param record the contents of the record
     * @param ttl TTL for the record
     *
     * @return a record id, as returned by your provider
     * @throws DnsApiException on errors
     */
    String setTextRecord( String zone, String subDomain, String record, int ttl ) throws DnsApiException

    /**
     * Remove a TXT DNS record.
     *
     * @param zone zone (like letsbot.org)
     * @param recordId the id returned previously by your provider from setTextRecord
     * @throws DnsApiException on errors
     */
    void removeRecord( String zone, String recordId ) throws DnsApiException

    /**
     * Clean all previous challenges that might by hanging around in the zone.
     *
     * @param zone zone (like letsbot.org)
     * @throws DnsApiException on errors
     */
    void cleanChallenges( String zone ) throws DnsApiException

}