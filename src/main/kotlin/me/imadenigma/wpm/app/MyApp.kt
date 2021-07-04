package me.imadenigma.wpm.app

import javafx.stage.Stage
import me.imadenigma.wpm.view.MainView
import tornadofx.App

class MyApp: App(MainView::class, Styles::class) {

    override fun start(stage: Stage) {
        with(stage) {
            this.width = 800.0
            this.height = 630.0
            this.isResizable = false
            super.start(this)
        }

    }
}