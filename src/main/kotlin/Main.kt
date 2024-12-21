package com.ajcode404

import javax.swing.JFrame

fun main() {
    val window = JFrame()
    window.defaultCloseOperation = JFrame.EXIT_ON_CLOSE
    window.isResizable = false
    window.title = "Treasure Hunt 2D"

    val gamePanel = GamePanel()
    window.add(gamePanel)
    window.pack()

    window.setLocationRelativeTo(null)
    window.isVisible = true

    gamePanel.startGameThread()
}