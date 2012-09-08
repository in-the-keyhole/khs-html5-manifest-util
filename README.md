khs-html5-manifest-util
=======================

Scans web document root folder and generates a HTML5 cache manifest file for OFF LINE operation

More information about off line HTML5 support checkout 

Features
--------
 * Scans web project for html,js,css,png,etc... generates HTML5 application cache file
 * Automatically senses for document root
 * Preserves manual editing of cache file
 * Options can be specified to override default options

Command Line Usage (Unix/Linux/OSX)
-----------------------------------
Execute executable jar (requires Java Runtime (JRE) to be installed)

Installation:

	Download html5manifest.jar from GITHUB files section

Usage (Run from a web project folder): 

		java -jar html5manifest.jar

Output: 

	file named MANIFEST.APPCACHE will be generated in document root folder 
	example contents show below...
	
	CACHE MANIFEST # 2012-9-6:v 12.36.11
	# Explicitly cached 'master entries 
	CACHE:
	# Generated, entries don't edit between {begin}..{end} markers
	#{begin}
	test.css
	test.html
	test.js
	test.png
	#{end}
	NETWORK:
	FALLBACK:
	
Usage Options (Specify manifest file name and doc root)

		java -jar html5manifest.jar -f example.appcache -d /src/main/webapp


Maven Usage
-----------

Apply the following plugin to your POM.XML Plugins section, manifest file will be generated during build phase

	<plugin>
         <groupId>org.codehaus.mojo</groupId>
         <artifactId>exec-maven-plugin</artifactId>
         <version>1.2.1</version>  
          <executions>
                <execution>
                    <phase>package</phase>
                    <goals>
                        <goal>java</goal>
                    </goals>
                </execution>
            </executions>
                
          <configuration>
                <mainClass>com.khs.appcache.Main</mainClass>
                    <arguments>
                        <argument>argument1</argument>
                    </arguments>
                </configuration> 
           		 </plugin>
      		       		
    	</plugins>


Add the following dependency to your POM.XML <dependencies> section

	<dependency>
   		<groupId>org.apache.maven</groupId>
   		<artifactId>maven-plugin-api</artifactId>
  		 <version>2.0</version>
	</dependency>









