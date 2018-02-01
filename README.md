# oracle-connector

An OpenICF connector built to work with the [ForgeRock Identity Management Provisioning System](https://www.forgerock.com/platform/identity-management/identity-provisioning). This connector allows the ForgeRock Identity Platform to provision users to an Oracle Enterprise Resource Planning (ERP) system. 

**NOTE:** This is a community connector and is made available so that customers with a ForgeRock subscription can build, develop or fork this repository. See the legal disclaimer bit below.

## Building
The code in this repository has binary dependencies that live in the ForgeRock maven repository. Maven can be configured to authenticate to this repository by following the following [ForgeRock Knowledge Base Article](https://backstage.forgerock.com/knowledge/kb/article/a74096897).

To get and build this repository you will need a build environment that has git, maven and a JDK installed. 

This has been confirmed as buildin with Maven 3.3.3, Oracle JDK 1.8.0_91.

### Install dependency
Download ojdbc6.jar from `http://www.oracle.com/technetwork/database/enterprise-edition/jdbc-112010-090769.html`

From your download directory:
`mvn install:install-file -Dfile=ojdbc6.jar -DgroupId=com.oracle -DartifactId=ojdbc6 -Dversion=11.2.0.3.0 -Dpackaging=jar -DgeneratePom=true `

### Build

```
$ git clone <repo_url>
$ cd <repo_url>
$ mvn clean install
```

## Licensing

The Code and binaries are covered under the [CDDL 1.0 license](https://forgerock.org/cddlv1-0/). Essentially you may use the release binaries in production at your own risk.

### Legal Disclaimer Bit
All components herein are provided AS IS and without a warranty of any kind by ForgeRock or any licensors of such code. ForgeRock and all licensors of such code disclaims all liability or obligations of such code and any use, distribution or operation of such code shall be exclusively subject to the licenses contained in each file and recipient is obligated to read and understand each such license before using the Software. Notwithstanding anything to the contrary, ForgeRock has no obligations for the use or support of such code under any ForgeRock license agreement.
