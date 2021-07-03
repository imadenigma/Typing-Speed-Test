package com.example.demo.utils

import javafx.scene.text.Font
import java.io.File
import java.io.FileInputStream

object Fonts {
    val font1: Font
        get() = Font.loadFont(FileInputStream(File("C:\\Users\\imadb\\IdeaProjects\\TornadoFX\\src\\main\\resources\\fonts\\font1.otf")), 32.0)

    val cherry: Font
        get() = Font.loadFont(FileInputStream(File("C:\\Users\\imadb\\IdeaProjects\\TornadoFX\\src\\main\\resources\\fonts\\cherry.ttf")), 50.0)

    val ocra: Font
        get() = Font.loadFont(FileInputStream(File("C:\\Users\\imadb\\IdeaProjects\\TornadoFX\\src\\main\\resources\\fonts\\OCR-A.ttf")), 20.0)
}