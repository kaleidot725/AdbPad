# 保留 Jetpack Compose 的编译器生成代码和注解
-keep class androidx.compose.** { *; }
-keep class androidx.lifecycle.** { *; }
-keepattributes *Annotation*

# 禁止混淆 Compose 的内联函数
-keepclassmembers class ** {
    @androidx.compose.runtime.Composable <methods>;
}

# 保留 UI Preview 用于开发
-keep class androidx.compose.ui.tooling.** { *; }

# 保留 Kotlin 协程的内联函数和调试信息
-keep class kotlinx.coroutines.** { *; }
-keepattributes *Annotation*
-keepclassmembers class **$* { *; }

# 保留 Koin 的 DI 容器
-keep class org.koin.** { *; }
-keepclassmembers class org.koin.** { *; }

# Room 的实体类和 DAO
-keep @androidx.room.* class * { *; }
-keep class androidx.room.** { *; }
-keep interface androidx.room.** { *; }
-keep class android.arch.** { *; }
-keep @androidx.room.Entity class *
-keep class androidx.sqlite.** { *; }



# DataStore 相关规则
-keep class androidx.datastore.** { *; }
-keepattributes *Annotation*

# 保留 Compose Multiplatform 的底层实现
-keep class org.jetbrains.compose.** { *; }



# ----------------------------------------- Basic ------------------------------------------------ #
-keepattributes *Annotation*

# For native methods, see http://proguard.sourceforge.net/manual/examples.html#native
-keepclasseswithmembernames class * {
    native <methods>;
}

# For enumeration classes, see http://proguard.sourceforge.net/manual/examples.html#enumerations
-keepclassmembers enum * {
    public static **[] values();
    public static ** valueOf(java.lang.String);
}


# ----------------------------------------- Okio ------------------------------------------------- #
# Animal Sniffer compileOnly dependency to ensure APIs are compatible with older versions of Java.
-dontwarn org.codehaus.mojo.animal_sniffer.*


# ----------------------------------------- OkHttp ----------------------------------------------- #
# JSR 305 annotations are for embedding nullability information.
-dontwarn javax.annotation.**

# A resource is loaded with a relative path so the package of this class must be preserved.
-adaptresourcefilenames okhttp3/internal/publicsuffix/PublicSuffixDatabase.gz

# Animal Sniffer compileOnly dependency to ensure APIs are compatible with older versions of Java.
-dontwarn org.codehaus.mojo.animal_sniffer.*

# OkHttp platform used only on JVM and when Conscrypt and other security providers are available.
-dontwarn okhttp3.internal.platform.**
-dontwarn org.conscrypt.**
-dontwarn org.bouncycastle.**
-dontwarn org.openjsse.**


# ----------------------------------------- kotlinx serialization -------------------------------- #
-keepattributes *Annotation*, InnerClasses
-dontnote kotlinx.serialization.AnnotationsKt # core serialization annotations

# kotlinx-serialization-json specific. Add this if you have java.lang.NoClassDefFoundError kotlinx.serialization.json.JsonObjectSerializer
-keepclassmembers class kotlinx.serialization.json.** {
    *** Companion;
}
-keepclasseswithmembers class kotlinx.serialization.json.** {
    kotlinx.serialization.KSerializer serializer(...);
}


# ----------------------------------------- ktor ------------------------------------------------- #
-keep class io.ktor.** { *; }
-keepclassmembers class io.ktor.** { volatile <fields>; }
-keep class io.ktor.client.engine.cio.** { *; }
-keep class kotlinx.coroutines.** { *; }
-dontwarn kotlinx.atomicfu.**
-dontwarn io.netty.**
-dontwarn com.typesafe.**
-dontwarn org.slf4j.**

# Obfuscation breaks coroutines/ktor for some reason
-dontobfuscate


# ----------------------------------------- App Ruls --------------------------------------------- #
# Change here com.github.panpf.sketch.sample
-keepclassmembers @kotlinx.serialization.Serializable class com.github.panpf.sketch.sample.** {
    # lookup for plugin generated serializable classes
    *** Companion;
    # lookup for serializable objects
    *** INSTANCE;
    kotlinx.serialization.KSerializer serializer(...);
}
# lookup for plugin generated serializable classes
-if @kotlinx.serialization.Serializable class com.github.panpf.sketch.sample.**
-keepclassmembers class com.github.panpf.sketch.sample.<1>$Companion {
    kotlinx.serialization.KSerializer serializer(...);
}