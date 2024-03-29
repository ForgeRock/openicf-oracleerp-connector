<!--  
/*
 * The contents of this file are subject to the terms of the Common Development and
 * Distribution License (the License). You may not use this file except in compliance with the
 * License.
 *
 * You can obtain a copy of the License at legal/CDDLv1.0.txt. See the License for the
 * specific language governing permission and limitations under the License.
 *
 * When distributing Covered Software, include this CDDL Header Notice in each file and include
 * the License file at legal/CDDLv1.0.txt. If applicable, add the following below the CDDL
 * Header, with the fields enclosed by brackets [] replaced by your own identifying
 * information: "Portions copyright [year] [name of copyright owner]".
 *
 * Copyright 2018 ForgeRock AS.
 */
-->
# oracleerp-connector

An OpenICF connector built to work with the [ForgeRock Identity Management Provisioning System]
(https://www.forgerock.com/platform/identity-management/identity-provisioning). This connector allows the ForgeRock 
Identity Platform to provision users to an Oracle Enterprise Resource Planning (ERP) system. 

**NOTE:** This is a community connector and is made available so that customers with a ForgeRock subscription can build,
 develop or fork this repository. See the legal disclaimer bit below.
 
## Compatability
Works with [OpenICF 1.5](https://backstage.forgerock.com/docs/openicf/1.5/release-notes/index.html). Please read the 
release notes for more information on compatability with Java Connector Servers and version of OpenIDM/IDM. 
 
**NOTE:** The connector uses jdbc6 which is the JDBC driver for Oracle Database 11g. The latest Oracle database version 
(at time of writing Feb 2018) is Oracle Database 12c which uses the ojdbc7.jar driver.  

## Building
The code in this repository has binary dependencies that live in the ForgeRock maven repository. Maven can be configured
 to authenticate to this repository by following the following [ForgeRock Knowledge Base Article]
(https://backstage.forgerock.com/knowledge/kb/article/a74096897).

To get and build this repository you will need a build environment that has git, maven and a JDK installed. 

This has been confirmed as building with Maven 3.3.3, Oracle JDK 1.8.0_91.

### Install dependency
Download ojdbc6.jar from `http://www.oracle.com/technetwork/database/enterprise-edition/jdbc-112010-090769.html`

From your download directory:
`mvn install:install-file -Dfile=ojdbc6.jar -DgroupId=com.oracle -DartifactId=ojdbc6 -Dversion=11.2.0.3.0 -Dpackaging=jar -DgeneratePom=true `

### Build

```
$ git clone <repo_url>
$ cd <repo_dir>
$ mvn clean install
```

## Licensing

The Code and binaries are covered under the [CDDL 1.0 license](https://forgerock.org/cddlv1-0/). Essentially you may use
 the release binaries in production at your own risk.

### Legal Disclaimer Bit
All components herein are provided AS IS and without a warranty of any kind by ForgeRock or any licensors of such code. 
ForgeRock and all licensors of such code disclaims all liability or obligations of such code and any use, distribution 
or operation of such code shall be exclusively subject to the licenses contained in each file and recipient is obligated
 to read and understand each such license before using the Software. Notwithstanding anything to the contrary, ForgeRock
 has no obligations for the use or support of such code under any ForgeRock license agreement.
