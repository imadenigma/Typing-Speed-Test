package me.imadenigma.wpm.app

import me.imadenigma.wpm.utils.Fonts
import com.jfoenix.controls.JFXButton
import javafx.application.Platform
import javafx.scene.layout.HBox
import javafx.scene.paint.Color
import javafx.scene.text.Font
import javafx.scene.text.FontWeight
import kfoenix.JFXStylesheet
import kfoenix.jfxbutton
import tornadofx.*
import java.io.File
import java.io.FileInputStream

class Styles : JFXStylesheet() {

    companion object {
        val startButton by cssclass()
        val slider by cssclass()
        val durationLabel by cssclass()

    }

    init {
        startButton {
            this.font = Font(Fonts.ocra.name, 25.0)
            backgroundColor.add(c("#635757"))
            this.textFill = c("#B56464")

        }

        slider {
            this.backgroundColor.add(c("#E19A9D"))
        }

        durationLabel {
            this.textFill = c("#FF6B6B")
            this.font = Fonts.ocra
        }
    }

}

