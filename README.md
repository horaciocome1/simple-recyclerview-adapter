# Simple RecyclerView Adapter 
[![](https://jitpack.io/v/horaciocome1/simple-recyclerview-adapter.svg)](https://jitpack.io/#horaciocome1/simple-recyclerview-adapter)[![License](https://img.shields.io/badge/license-Apache%202-blue.svg)](https://www.apache.org/licenses/LICENSE-2.0) [![API](https://img.shields.io/badge/API-14%2B-brightgreen.svg?style=flat)](https://android-arsenal.com/api?level=14)

## Getting Started
This is a library that abstracts, and completely hide the Adapter and ViewHolder class creation part.
It is general purpose object that can be costumized to each case (item layout) by the time of creation. Similar to what is done on Listview with default adapters.
Compatible with androidx.

## Pre-requesites
This library can be implemented on any android project with minimun API 14. You must have a list and an item_layout for each item of the list

## Adding to your project
Lets start by adding a corresponding repository in your _root_ `build.gradle` file. (prefer below all other)
```gradle
	allprojects {
		repositories {
			...
			maven { url 'https://jitpack.io' }
		}
	}
 ```
The next task is to add the dependecy to your _app_ `build.gradle` file.
```gradle
	dependencies {
          ...
	        implementation 'com.github.horaciocome1:simple-recyclerview-adapter:0.1.0'
	}
```
Now you ready to go. Except that you should _**sync your project**_ first.

### Do not use with app compart or support design
As mention on IO18, android support libraries will not be updated anymore, thats the main reason ive moved to androidx and new material design libraries. That's why if you have any appcompat or design support library as dependency the build will fail. Because the androidx on these lib will conflict with android support on your app.
I am wondering if it is necessary to do a separate lib for suppor appcompat. Email me if you thinking on that.

## How to use
Intanciate a class `SimpleRecyclerViewAdapter` by using its own builder.
```java
SimpleRecyclerViewAdapter adapter = new SimpleRecyclerViewAdapter.Builder()
        .setItemLayout(R.layout.your_item_layout)
        .setList(yourList)
        .setOnBindViewHolder(new SimpleRecyclerViewAdapter.OnBindViewHolder() {
            @Override
            public void onBind(@NonNull SimpleRecyclerViewAdapter.ViewHolder holder, Object object) {
                // bind object data to the view
            }
        })
        .build();
recyclerView.setAdapter(adapter);
```
### Examples
Lets have a look at some common binding examples 
```java
@Override
public void onBind(@NonNull SimpleRecyclerViewAdapter.ViewHolder holder, Object object) {
    // binding a string to a textview
    ((TextView) holder.getViewById(R.id.your_item_layout_textview_id)).setText((String) object)

    // binding and image to an imageview using Picasso or Glide
    Picasso.with(context)
            .load(((Person) object).getProfilePic())
            .into((ImageView) holder.getViewById(R.id.your_item_layout_imageview_id))
}
```

### Kotlin
```kotlin
val adapter = SimpleRecyclerViewAdapter.Builder()
        .setList(posts())
        .setItemLayout(R.layout.item_post)
        .setOnBindViewHolder { holder, `object` -> // bind your data here }
        .build()
recyclerView.adapter = adapter
```

As you can see, casting is mandatory. Remember that this is a general porpose Adapter, althougth it is returning your own object, the class itself dont know it.

### Troubleshooting
Notice that it is crucial to call `build( ... )` so that the listener could be initialized.
It makes no sense only calling `build( ... )`.
```kotlin
val adapter = SimpleRecyclerViewAdapter.Builder().build()
recyclerView.adapter = adapter
```
This might lead to app crash.

### "Build or sinchronization failed!"
Please reference to the part on the start where i talked about support libraries.

## Licenses
   Copyright 2018 Horácio Flávio Comé Júnior

   Licensed under the Apache License, Version 2.0 (the "License");
   you may not use this file except in compliance with the License.
   You may obtain a copy of the License at

       http://www.apache.org/licenses/LICENSE-2.0

   Unless required by applicable law or agreed to in writing, software
   distributed under the License is distributed on an "AS IS" BASIS,
   WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
   See the License for the specific language governing permissions and
   limitations under the License.

## How to contribute
I am open to suggestions of any kind.
Please be expressive, so others so we'all will be able to understand each other!
Report any issues, please!

# Simple RecyclerView Utils
This is part of a serie of libraries that pretend to make recyclerview usage more easy.
For a touch listener please see [Simple RecyclerView Touch Listener](https://github.com/horaciocome1/simple-recyclerview-touch-listener)