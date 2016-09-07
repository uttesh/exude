# exude  <a target="_blank" href="https://exude.herokuapp.com/">demo</a>

[![Maven Central](https://maven-badges.herokuapp.com/maven-central/com.uttesh/exude/badge.svg)](https://maven-badges.herokuapp.com/maven-central/com.uttesh/exude/)
[![Build Status](https://travis-ci.org/uttesh/exude.svg)](https://travis-ci.org/uttesh/exude)
<p>This is simple library for removing/filtering the stopping,stemming words from the text data, this library is in very basic level of development need to work on for later changes.</p>

This is the part of maven repository now,Directly add in pom following.

        <dependency>
            <groupId>com.uttesh</groupId>
            <artifactId>exude</artifactId>
            <version>0.0.3</version>
        </dependency>
        
<b>How to use exude Library</b>
<hr/>
Download latest version of exude <a href="https://repo1.maven.org/maven2/com/uttesh/exude/0.0.3/">download</a>

Features:

* Filter stopping words from given text/file/link
* Filter stemming words from given text/file/link
* Get swear words from given text/file/link

How Exude library works:

Step 1: Filter the duplicate words from the input data/file. </br>
Step 2: Filter the stopping words from step1 filtered data. </br>
Step 3: Filter the stemmer/swear words from step2 filtered data using the Porter algorithm which is used for suffix stripping. </br>

exude process sequence flow:
 
![demo](https://raw.github.com/uttesh/exude/master/docs/process/flow.png)

<b>Environment and dependent jar file</b>
<hr/>

1. Minimum JDK 1.6 or higher
2. Apache Tika jar (which is used to parse the files for the data extraction)


Sample code:

Sample Text Data

	 String inputData = "Kannada is a Southern Dravidian language, and according to Dravidian scholar Sanford Steever, its history can be conventionally divided into three periods; Old Kannada (halegannada) from 450–1200 A.D., Middle Kannada (Nadugannada) from 1200–1700 A.D., and Modern Kannada from 1700 to the present.[20] Kannada is influenced to an appreciable extent by Sanskrit. Influences of other languages such as Prakrit and Pali can also be found in Kannada language.";
     String output = ExudeData.getInstance().filterStoppings(inputData);
     System.out.println("output : "+output);
	 
Sample File Data

	String inputData = "any file path";
	String output = ExudeData.getInstance().filterStoppings(inputData);
	System.out.println("output : "+output);
	
Sample Link Data

	String inputData = "https://en.wikipedia.org/wiki/Rama";
	String output = ExudeData.getInstance().filterStoppings(inputData);
	System.out.println("output : "+output);

Get swear words from data/file/link

	String inputData = "enter text with bad words";
	String output = ExudeData.getInstance().getSwearWords(inputData);
	System.out.println("output : "+output);

New Feature:

1. Keep the duplicate words after the filterStoppings

Sample Text Data

	 String inputData = "testing testing testing the keep keep the the duplicate data data in result";
     String output = ExudeData.getInstance().filterStoppingsKeepDuplicates(inputData);
     System.out.println("output : "+output);

contributions
=============

Credit <a href="https://tika.apache.org/">apache tika</a> which is used to parse the files for the data extraction.

Exude library Developer : <a href="http://www.uttesh.com" target="_blank">uttesh.com</a>

<h3>
<a name="license" class="anchor" href="#license"><span class="mini-icon mini-icon-link"></span></a>License</h3>

<p>(The Apache License)</p>

<p>Copyright (c) 2016 Uttesh Kumar T.H.</p>

   Licensed under the Apache License, Version 2.0 (the "License");
   you may not use this file except in compliance with the License.
   You may obtain a copy of the License at

       http://www.apache.org/licenses/LICENSE-2.0

   Unless required by applicable law or agreed to in writing, software
   distributed under the License is distributed on an "AS IS" BASIS,
   WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
   See the License for the specific language governing permissions and
   limitations under the License.</p>


