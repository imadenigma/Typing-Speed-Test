package me.imadenigma.wpm.view

import com.jfoenix.controls.JFXButton
import com.jfoenix.controls.JFXSlider
import javafx.beans.property.*
import javafx.event.EventType
import javafx.geometry.Insets
import javafx.geometry.Orientation
import javafx.scene.Cursor
import javafx.scene.image.Image
import javafx.scene.input.KeyCode
import javafx.scene.input.KeyCombination
import javafx.scene.input.KeyEvent
import javafx.scene.layout.Background
import javafx.scene.layout.BackgroundFill
import javafx.scene.layout.CornerRadii
import javafx.scene.paint.Color
import javafx.scene.shape.StrokeType
import javafx.scene.text.Font
import javafx.stage.FileChooser
import kfoenix.*
import me.imadenigma.wpm.app.Styles
import me.imadenigma.wpm.controllers.TestController
import me.imadenigma.wpm.utils.Fonts
import tornadofx.*
import java.io.File
import java.io.FileInputStream
import java.lang.StringBuilder
import java.util.concurrent.TimeUnit
import javax.swing.KeyStroke
import kotlin.math.roundToInt

class MainView : View("Typing Speed Tester") {


    private val controller: TestController by inject()
    private val textProperty = SimpleStringProperty("")



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
            setOnMouseReleased {
                controller.sliderProperty.value = this.value.roundToInt()
            }
        }

        jfxcombobox(values = listOf(TimeUnit.SECONDS, TimeUnit.MINUTES, TimeUnit.HOURS)) {
            this.layoutY = 98.0
            this.layoutX = 610.0
            this.selectionModel.select(TimeUnit.MINUTES)
            this.setOnAction {
                when (this.selectedItem) {
                    TimeUnit.SECONDS -> style { this.backgroundColor.add(Color.GREEN) }
                    TimeUnit.HOURS -> style { this.backgroundColor.add(Color.ORANGE) }
                    else -> style { this.backgroundColor.add(Color.YELLOWGREEN) }
                }
                controller.timeUnitProperty.value = this.selectedItem
            }
            style {
                this.backgroundColor.add(Color.YELLOWGREEN)
            }
        }

        jfxcheckbox(controller.wpmProperty, "WPM (Words Per Minute)") {
            this.layoutX = 130.0
            this.layoutY = 190.0
            this.isSelected = true
            this.checkedColor = c("#00FF66")
            this.unCheckedColor = c("#E56363")
            style {
                this.textFill = c("#5869FF")
                this.font = Font(15.0)
            }
        }

        jfxcheckbox(controller.cpmProperty, "CPM (Character Per Minute)") {
            this.layoutX = 400.0
            this.layoutY = 190.0
            this.checkedColor = c("#00FF66")
            this.unCheckedColor = c("#E56363")
            style {
                this.textFill = c("#5869FF")
                this.font = Font(15.0)
            }
            setOnMouseClicked {
                if (!it.isConsumed) controller.choices.add('c')
                else controller.choices.remove('c')
            }
        }

        jfxcheckbox(controller.visualProperty, "Visual") {
            this.layoutX = 315.0
            this.layoutY = 250.0
            this.checkedColor = c("#00FF66")
            this.unCheckedColor = c("#E56363")
            style {
                this.font = Font(15.0)
                this.textFill = c("#5869FF")
            }
        }

        jfxcheckbox(controller.hardProperty, "Hard Test") {
            this.layoutX = 315.0
            this.layoutY = 300.0
            this.checkedColor = c("#00FF66")
            this.unCheckedColor = c("#E56363")
            style {
                this.font = Font(15.0)
                this.textFill = c("#5869FF")
            }
        }

        text(textProperty) {
            this.layoutX = 450.0
            this.layoutY = 257.0
            style {
                this.stroke = Color.WHITE
                this.font = Font(10.0)
            }
        }

        imageview(Image(FileInputStream("C:\\Users\\imadb\\IdeaProjects\\TornadoFX\\src\\main\\resources\\question.png"))) {
            this.layoutX = 403.0
            this.layoutY = 257.0
            onHover {
                if (it) textProperty.value = "If its selected\n when you start testing by shortcut\nit will display a text that you should write"
                else textProperty.value = null


            }


        }

        imageview(Image(FileInputStream("C:\\Users\\imadb\\IdeaProjects\\TornadoFX\\src\\main\\resources\\question.png"))) {
            this.layoutX = 435.0
            this.layoutY = 307.0
            onHover {
                if (it) textProperty.value = "Requires enabling visual\n* Texts gonna be random and not from yours\n* Duration will be 2 minutes\n* You can't complete if you commit a mistake !"
                else textProperty.value = null
            }
        }

        label("Add more texts to use (for language reasons...)") {
            this.layoutX = 60.0
            this.layoutY = 375.0
            this.font = Font(Fonts.ocra.name, 18.0)
            this.textFill = c("#5869FF")
        }

        jfxbutton("Import") {
            this.layoutX = 585.0
            this.layoutY = 373.0
            this.font = Font(Fonts.ocra.name, 15.0)
            this.textFill = c("#5869FF")
            style {
                this.backgroundColor.add(c("#9F9E66"))
            }
            setOnAction {
                chooseFile("Choose Text File", arrayOf(FileChooser.ExtensionFilter("TEXT files", "*.txt", "*.rtf", "*.log"))).forEach { controller.importFile(it) }
            }
        }

        label("Shortcut Key: ") {
            this.layoutX = 220.0
            this.layoutY = 420.0
            this.font = Font(Fonts.ocra.name, 32.0)
            this.textFill = c("#5869FF")
        }

        jfxtextfield("", shortcutK) {
            this.layoutX = 470.0
            this.layoutY = 411.0
            this.font = Font(Fonts.ocra.name, 28.0)
            this.focusColor = Color.LIGHTYELLOW
            style {
                this.textFill = Color.GRAY
                setOnKeyPressed {
                    val builder = StringBuilder()
                    if (it.code == KeyCode.BACK_SPACE) text = ""
                    if (!it.isShortcutDown) return@setOnKeyPressed
                    if (it.isControlDown && !builder.contains("CTRL") && !text.contains("CTRL")) {
                        if (builder.isNotEmpty() || text.isNotEmpty()) builder.append("+")
                        builder.append("CTRL")
                    }
                    if (it.isAltDown && !builder.contains("ALT") && !text.contains("ALT")) {
                        if (builder.isNotEmpty() || text.isNotEmpty()) builder.append("+")
                        builder.append("ALT")

                    }
                    if (it.isShiftDown && !builder.contains("SHIFT") && !text.contains("SHIFT")) {
                        if (builder.isNotEmpty() || text.isNotEmpty()) builder.append("+")
                        builder.append("SHIFT")
                    }
                    if (it.code.isLetterKey) {
                        if (builder.isNotEmpty() || text.isNotEmpty()) builder.append("+")
                        builder.append(it.code)
                    }
                    text = "$text$builder"
                    positionCaret(100)
                }


            }
        }

        jfxbutton("START") {
            this.setPrefSize(235.0, 51.0)
            layoutX = 284.0
            layoutY = 490.0
            buttonType = JFXButton.ButtonType.RAISED
            addClass(Styles.startButton)
        }

    }

    companion object {
        var shortcutK = "CTRL+ALT+D"
    }
}