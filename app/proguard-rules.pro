#################################################################
#                   kotlinx serialization                       #
#################################################################

-keepattributes *Annotation*, InnerClasses
-dontnote kotlinx.serialization.AnnotationsKt

-keepclassmembers class kotlinx.serialization.json.**.* {
    *** Companion;
}
-keepclasseswithmembers class kotlinx.serialization.json.**.* {
    kotlinx.serialization.KSerializer serializer(...);
}

-keep,includedescriptorclasses class com.dtuskenis.papajohnscodes.**$$serializer { *; }
-keepclassmembers class com.dtuskenis.papajohnscodes.**.* {
    *** Companion;
}
-keepclasseswithmembers class com.dtuskenis.papajohnscodes.**.* {
    kotlinx.serialization.KSerializer serializer(...);
}
