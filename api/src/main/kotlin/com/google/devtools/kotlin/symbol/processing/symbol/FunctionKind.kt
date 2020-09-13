/*
 * Copyright 2010-2020 JetBrains s.r.o. and Kotlin Programming Language contributors.
 * Use of this source code is governed by the Apache 2.0 license that can be found in the license/LICENSE.txt file.
 */

package com.google.devtools.kotlin.symbol.processing.symbol

/**
 * Kind of a function declaration.
 */
enum class FunctionKind {
    TOP_LEVEL, MEMBER, STATIC, ANONYMOUS, LAMBDA;
}