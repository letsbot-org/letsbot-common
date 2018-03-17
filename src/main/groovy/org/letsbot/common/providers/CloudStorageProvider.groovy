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
 * Definition of a Cloud Storage Provider.
 *
 * Date: 16/3/18
 * @author narciso.cerezo@gmail.com
 */
@SuppressWarnings("GroovyUnusedDeclaration")
interface CloudStorageProvider {

    /**
     * Configure the provider, called once before any other method.
     * @throws CloudStorageException on errors
     */
    void configure() throws CloudStorageException

    /**
     * Upload the package to the cloud provider.
     *
     * @param file file to upload
     * @throws CloudStorageException on errors
     */
    void uploadFile( File file ) throws CloudStorageException

    /**
     * Download the package from the cloud provider.
     *
     * @param file file to upload
     * @throws CloudStorageException on errors
     */
    void downloadFile( File file ) throws CloudStorageException

    /**
     * Check if the package exists in the cloud provider.
     * @return true if so
     * @throws CloudStorageException on errors
     */
    boolean packageExists() throws CloudStorageException
}