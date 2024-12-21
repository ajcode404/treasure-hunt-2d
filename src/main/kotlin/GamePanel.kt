package com.ajcode404

import java.awt.Color
import java.awt.Dimension
import java.awt.Graphics
import java.awt.Graphics2D
import javax.swing.JPanel

class GamePanel : JPanel(), Runnable {

    // SCREEN SETTINGS
    private val originalTileSize = 16 // 16x16 tile
    private val scale = 4

    private val tileSize = originalTileSize * scale // 64x64 tile
    private val maxScreenCol = 16
    private val maxScreenRow = 12
    private val screenWidth = tileSize * maxScreenCol //  1024 pixels
    private val screenHeight = tileSize * maxScreenRow // 768 pixels

    companion object {
        // FPS
        private const val ONE_BILLION_NANO_SECOND = 1_000_000_000
        private const val ONE_MILLION_NANO_SECOND = 1_000_000
        private const val FPS = 60
    }

    val keyHandler = KeyHandler()

    // Set players default position
    var playerX = 100
    var playerY = 100
    var playerSpeed = 4

    init {
        this.preferredSize = Dimension(screenWidth, screenHeight)
        this.background = Color.black
        this.isDoubleBuffered = true
        this.addKeyListener(keyHandler)
        this.isFocusable = true
    }

    fun startGameThread() {
        val gameThread = Thread(this)
        gameThread.start()
    }

    override fun run() {
        while(true) {
            val drawInterval = ONE_BILLION_NANO_SECOND / FPS // 0.16666 seconds
            val nextDrawTime = System.nanoTime() + drawInterval

            // UPDATE : update information such as character information
            update()
            // DRAW : draw the screen with updated information
            repaint()

            // sleep method accept time in millis hence converting time to millis
            val remainingTime = (nextDrawTime - System.nanoTime()) / ONE_MILLION_NANO_SECOND
            if (remainingTime >= 0) {
                Thread.sleep(remainingTime)
            }
        }
    }

    fun update() {
        when {
            keyHandler.upPressed -> playerY -= playerSpeed
            keyHandler.downPressed -> playerY += playerSpeed
            keyHandler.leftPressed -> playerX -= playerSpeed
            keyHandler.rightPressed -> playerX += playerSpeed
        }
    }

    override fun paintComponent(g: Graphics?) {
        super.paintComponent(g)
        val g2 = g as Graphics2D
        g2.color = Color.white
        g2.fillRect(playerX, playerY, tileSize, tileSize)
        g2.dispose()
    }
}