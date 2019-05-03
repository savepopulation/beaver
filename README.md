# beaver
Beaver is a lightweight url meta data parser library. Beaver, loads the metadata of the given url and stores it locally and caches in memory by default. Beaver uses ```Kotlin-Coroutines``` for background operations. ```Room``` is used for local cache operations.

### Tech Stack:
- Kotlin
- Kotlin Coroutines
- Room
- Repository pattern

### How to use?

It's easy to use. Just Init ```Beaver``` before start using.
```kotlin
Beaver.build(this)
```
And load url's metadata like the following:
```kotlin
launch {
  val metaData = Beaver.load("https://www.example.com").await()
  if (metaData != null) {
    // do what ever you want with your meta data..
  }
```
With defaults, load method checks memory cache, local cache and if there's no metadata available in caches it parses metada data from remote. 

You can force to refresh with ```RemoteDataSource```
```kotlin
Beaver.load("https://www.example.com",forceRefresh = true).await()
```

You can use only cache sources with
```kotlin
Beaver.load("https://www.example.com",forceLocal = true).await()
```

You can destroy ```Beaver``` easily with
```kotlin
Beaver.destroyBeaver()
```

You can drop cache
```kotlin
Beaver.dropBeaverCache()()
```

Or you can just drop local cache. By default, this clears all the local db
```kotlin
Beaver.dropBeaverLocalCache()()
```

If you want to drop only one item's local cache you can pass a url
```kotlin
Beaver.dropBeaverLocalCache("https://www.example.com")
```

### Customizing Beaver

```Beaver``` is fully customizable. 

You can implement your own metada data parser with implementing ```MetaDataParser``` and passing to ```Beaver``` By defaults, Beaver uses meta data parser implementation of **PannomKathrik's** ```RichLinkPreview``` library's meta data parser implementation. You can find this useful library from here: https://github.com/PonnamKarthik/RichLinkPreview

```kotlin
Beaver.build(this, metaDataParser = yourCustomMetaDataParserImpl)
```

By defaults, ```Beaver``` uses it's own ```AsnyManager``` for handling async operations. If you need you can implement ```AsyncManager``` pass it to ```Beaver``` and use your own implementation for async operations.
```kotlin
Beaver.build(this, asyncManager = yourCustomAsyncManagerImpl)
```

Also you can use your own memory cache implementation. You can implement ```DataSource.Cache<String, MetaData>?``` and pass while building.
```kotlin
Beaver.build(this, cacheDataSource = yourCacheDataSourceImpl)
```

You can customize your local storing implementation. By defaults ```Beaver``` uses it's own local cache implementation which uses Room. You can implement ```DataSource.Local<String, MetaData>?``` and pass it to ```Beaver``` instance while building.
```kotlin
Beaver.build(this, localDataSource = yourLocalDataSourceImpl)
```

### Dependency<br>
```
maven { url 'https://jitpack.io' }
```
```
dependencies {
    implementation 'com.github.savepopulation:beaver:1.0.0'
}
``` 

### Apps Using in Production
- [Phone Box](https://play.google.com/store/apps/details?id=com.raqun.phonebox)
- [AppLocker](https://play.google.com/store/apps/details?id=com.momentolabs.app.security.applocker)

### Screenshots:
<img src="https://github.com/savepopulation/beaver/blob/master/art/ss1.png"
height="384" width="210">




















