package com.example.demo.app

import com.example.demo.view.MainView
import javafx.fxml.FXMLLoader
import javafx.scene.Parent
import javafx.scene.image.Image
import javafx.stage.Stage
import javafx.stage.StageStyle
import tornadofx.App
import tornadofx.setStageIcon

class MyApp: App(MainView::class, Styles::class) {

    override fun start(stage: Stage) {
        with(stage) {
            this.minWidth = 800.0
            this.minHeight = 630.0
            this.width = 800.0
            this.height = 630.0
            this.isResizable = false
            super.start(this)
        }

    }
}