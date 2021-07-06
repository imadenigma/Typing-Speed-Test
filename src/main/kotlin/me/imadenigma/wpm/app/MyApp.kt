package me.imadenigma.wpm.app

import com.dustinredmond.fxtrayicon.FXTrayIcon
import javafx.scene.control.MenuItem
import javafx.scene.image.Image
import javafx.stage.Stage
import me.imadenigma.wpm.view.MainView
import tornadofx.*
import java.awt.TrayIcon
import java.io.File
import java.io.FileInputStream

class MyApp : App(MainView::class, Styles::class) {

    override fun start(stage: Stage) {

        val file = File("C:\\Users\\imadb\\IdeaProjects\\TornadoFX\\src\\main\\resources\\images", "icon.png")
        val image = Image(FileInputStream(file))
        setStageIcon(image)
        trayIcon = FXTrayIcon(stage, file.toURI().toURL())


        with(stage) {
            this.width = 800.0
            this.height = 630.0
            this.isResizable = false
            super.start(this)

        }

    }

    override fun stop() {
        super.stop()
    }

    companion object {
        lateinit var trayIcon: FXTrayIcon
            private set
    }
}