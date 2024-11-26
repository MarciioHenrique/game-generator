package com.uenp.codegenerator.domain

enum class Directories(val folder: String) {
    SCRIPTS("scripts"),
    SCENES("scenes"),
    GLOBALS("scripts/globals"),
    IMPORT("import"),
    FONTS("import/fonts"),
    MENU("import/menu"),
    IMAGES("import/images"),
    SOUNDS("import/sounds"),
    VIDEOS("import/videos"),
    START_SCREEN("import/startScreen"),
}