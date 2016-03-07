[![](https://jitpack.io/v/maarqin/httpproject.svg)](https://jitpack.io/#maarqin/httpproject)

HttpProject
===========
Project to help you (Android Developer) to make requests to the server easily.
You save time and organize your code, this is the mission of this project

Download
--------

Download [the latest JAR][1] or grab via Gradle:
```groovy
allprojects {
  repositories {
    ...
    maven { url "https://jitpack.io" }
  }
}
```
```groovy
compile 'com.github.maarqin:httpproject:v1.2.0'
```
or Maven:
```xml
<repositories>
	<repository>
	    <id>jitpack.io</id>
	    <url>https://jitpack.io</url>
	</repository>
</repositories>
```
```xml
<dependency>
    <groupId>com.github.maarqin</groupId>
    <artifactId>httpproject</artifactId>
    <version>v1.2.0</version>
</dependency>
```
```java
String value = "something";
JSONObject object = new JSONObject();

object.put("key", value);

Post post = new Post(WSConfig.URL);
post.setBody(object);

Request request = new Request(post);
request.setListener(new ResultRequest(context, R.string.app_name, "Enviando dados...") {

	@Override
	public void onSuccess(JSONObject object, boolean b) throws Exception {
	    if (b) {
	        saveData(object);
	    } else {
	        Toast.makeText(getApplication(), "Something wrong", Toast.LENGTH_SHORT).show();
	    }
	}
});

request.execute();
```

License
-------

    Copyright 2016 Thomaz.

    Licensed under the Apache License, Version 2.0 (the "License");
    you may not use this file except in compliance with the License.
    You may obtain a copy of the License at

       http://www.apache.org/licenses/LICENSE-2.0

    Unless required by applicable law or agreed to in writing, software
    distributed under the License is distributed on an "AS IS" BASIS,
    WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
    See the License for the specific language governing permissions and
    limitations under the License.
    
 [1]: http://github.com/maarqin/httpproject/releases
