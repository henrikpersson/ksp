/*
 * Copyright 2020 Google LLC
 * Copyright 2010-2020 JetBrains s.r.o. and Kotlin Programming Language contributors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

// TEST PROCESSOR: JavaModifierProcessor
// EXPECTED:
// C: PUBLIC ABSTRACT
// staticStr: PRIVATE
// s1: FINAL JAVA_TRANSIENT
// i1: PROTECTED JAVA_STATIC JAVA_VOLATILE
// NestedC: PUBLIC JAVA_STATIC
// <init>: PUBLIC
// InnerC: PUBLIC
// <init>: PUBLIC
// intFun: JAVA_SYNCHRONIZED JAVA_DEFAULT
// foo: ABSTRACT JAVA_STRICT
// OuterJavaClass: PUBLIC
// InnerJavaClass: PUBLIC
// NestedJavaClass: PUBLIC JAVA_STATIC
// OuterKotlinClass: OPEN
// InnerKotlinClass: INNER
// NestedKotlinClass: OPEN
// DependencyOuterJavaClass: OPEN PUBLIC
// DependencyNestedJavaClass: OPEN PUBLIC
// DependencyInnerJavaClass: OPEN PUBLIC INNER
// DependencyOuterKotlinClass: OPEN PUBLIC
// DependencyInnerKotlinClass: FINAL PUBLIC INNER
// DependencyNestedKotlinClass: OPEN PUBLIC
// END
// MODULE: module1
// FILE: DependencyOuterJavaClass.java
public class DependencyOuterJavaClass {
    public class DependencyInnerJavaClass {}
    public static class DependencyNestedJavaClass {}
}
// FILE: DependencyOuterKotlinClass.kt
open class DependencyOuterKotlinClass {
    inner class DependencyInnerKotlinClass
    open class DependencyNestedKotlinClass
}
// MODULE: main(module1)
// FILE: a.kt
annotation class Test

@Test
class Foo : C() {

}

@Test
class Bar : OuterJavaClass()

@Test
class Baz : OuterKotlinClass()

@Test
class JavaDependency : DependencyOuterJavaClass()

@Test
class KotlinDependency : DependencyOuterKotlinClass()

// FILE: C.java

public abstract class C {

    private String staticStr = "str"

    final transient String s1;

    protected static volatile int i1;

    default synchronized int intFun() {
        return 1;
    }

    abstract strictfp void foo() {}

    public static class NestedC {

    }

    public class InnerC {

    }
}

// FILE: OuterJavaClass.java
public class OuterJavaClass {
    public class InnerJavaClass {}
    public static class NestedJavaClass {}
}
// FILE: OuterKotlinClass.kt
open class OuterKotlinClass {
    inner class InnerKotlinClass
    open class NestedKotlinClass
}
