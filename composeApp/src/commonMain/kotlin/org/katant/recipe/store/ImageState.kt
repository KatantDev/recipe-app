package org.katant.recipe.store

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.compose.ui.awt.ComposeWindow
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.graphics.toPainter
import java.io.File
import javax.imageio.ImageIO

open class ImageState (
    path: String? = null
) {
    var path by mutableStateOf(path)

    open fun selectImage() {
        val fileChooser = java.awt.FileDialog(ComposeWindow())
        fileChooser.setFilenameFilter { _, name -> name.endsWith(".jpg") || name.endsWith(".png") }
        fileChooser.isVisible = true

        if (fileChooser.files.isEmpty()) {
            return
        }
        val file = fileChooser.files[0]
        val filePath = "images/${file.name}"

        copyFileToAppDirectory(file, filePath = filePath)
        path = filePath
    }


    private fun copyFileToAppDirectory(sourceFile: File, filePath: String) {
        val destinationFile = File(filePath)
        sourceFile.copyTo(destinationFile, overwrite = true)
        println("Copied file to ${destinationFile.absolutePath}")
    }

    fun getPainter(): Painter {
        val file = path?.let { File(it) }
        val bufferedImage = ImageIO.read(file)
        return bufferedImage.toPainter()
    }
}