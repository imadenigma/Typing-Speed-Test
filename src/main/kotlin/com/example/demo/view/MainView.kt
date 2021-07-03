package com.example.demo.view

import com.example.demo.app.Styles
import com.example.demo.utils.Fonts
import com.jfoenix.controls.JFXButton
import com.jfoenix.controls.JFXSlider
import javafx.geometry.Insets
import javafx.geometry.Orientation
import javafx.scene.Cursor
import javafx.scene.layout.Background
import javafx.scene.layout.BackgroundFill
import javafx.scene.layout.CornerRadii
import javafx.scene.paint.Color
import javafx.scene.text.Font
import kfoenix.jfxbutton
import kfoenix.jfxslider
import tornadofx.*
import java.io.File
import java.io.FileInputStream

class MainView : View("Typing Speed Tester") {

    override val root = anchorpane {
        this.background = Background(BackgroundFill(Color.web("#222831"), CornerRadii.EMPTY, Insets.EMPTY))

        line(0.0, 70.0, 800.0, 70.0) {
            this.stroke = c("#8A8F5A")
        }

        label("WPM & CPM Calculator") {
            layoutX = 250.0
            layoutY = 20.0
            this.font = Fonts.cherry
            this.textFill = c("#545EBA")
        }

        label("Select duration of test") {
            this.layoutX = 210.0
            this.layoutY = 85.0
            addClass(Styles.durationLabel)

        }

        jfxslider(1.0, Orientation.HORIZONTAL, JFXSlider.IndicatorPosition.LEFT) {
            this.max = 100.0
            this.setPrefSize(400.0, 10.0)
            this.layoutX = 200.0
            this.layoutY = 120.0
            style {
                backgroundColor.add(c("#222831"))
            }
        }

        jfxbutton("START") {
            this.setPrefSize(235.0, 51.0)
            layoutX = 284.0
            layoutY = 434.0
            buttonType = JFXButton.ButtonType.RAISED
            addClass(Styles.startButton)
        }


    }
}       