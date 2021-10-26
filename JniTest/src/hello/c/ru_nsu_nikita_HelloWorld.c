#include <jni.h>
#include <stdio.h>

NIEXPORT jcharArray JNICALL Java_ru_nsu_nikita_HelloWorld_getHello
  (JNIEnv *, jobject)
  {
        return "Hello World!";
  }