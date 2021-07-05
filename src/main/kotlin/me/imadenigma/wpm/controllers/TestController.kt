package me.imadenigma.wpm.controllers

import javafx.beans.property.SimpleBooleanProperty
import javafx.beans.property.SimpleIntegerProperty
import javafx.beans.property.SimpleObjectProperty
import me.imadenigma.wpm.utils.QuizType
import me.imadenigma.wpm.utils.getCachePath
import tornadofx.*
import java.io.File
import java.util.concurrent.TimeUnit

class TestController : Controller() {

    val timeUnitProperty = SimpleObjectProperty<TimeUnit>(TimeUnit.MINUTES)
    val sliderProperty = SimpleIntegerProperty(1)
    val choices: MutableSet<Char> = mutableSetOf()
    val cpmProperty = SimpleBooleanProperty()
    val wpmProperty = SimpleBooleanProperty(true)
    val visualProperty = SimpleBooleanProperty()
    val hardProperty = SimpleBooleanProperty()

    lateinit var selectedFiles: Array<File>

    fun createQuiz() {
        val duration = timeUnitProperty.get().toSeconds(sliderProperty.longValue())
        val type = when {
            choices.size == 2 -> QuizType.BOTH
            choices.first() == 'c' -> QuizType.CPM
            else -> QuizType.WPM
        }
        val isVisual = visualProperty.get()
        val isHardcoreMode = hardProperty.get()
    }

    fun importFile(file: File) {
        with(file) {
            val target = File(getCachePath(), file.name)
            if (target.exists()) importFile(File(getCachePath(), "${file.nameWithoutExtension}1${file.extension}"))
            else {
                this.createNewFile()
                this.copyTo(target)
            }
        }
    }

}