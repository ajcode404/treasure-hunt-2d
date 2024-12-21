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

    val keyHandler = KeyHandler()

    // Set players default position
    var playerX = 100
    var playerY = 100
    var playerSpeed = 4

    companion object {
        // FPS
        private const val ONE_BILLION_NANO_SECOND = 1_000_000_000
        private const val ONE_MILLION_NANO_SECOND = 1_000_000
        private const val FPS = 60
    }

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

        val drawInterval: Double = ONE_BILLION_NANO_SECOND.toDouble() / FPS.toDouble()
        var delta = 0.toDouble()
        var lastTime = System.nanoTime()
        var currentTime: Long

        var timer = 0L
        var drawCount = 0

        while (true) {
            currentTime = System.nanoTime()
            delta += ((currentTime - lastTime) / drawInterval)

            timer += (currentTime - lastTime)
            lastTime = currentTime

            if (delta >= 1) {
                // UPDATE : update information such as character information
                update()
                // DRAW : draw the screen with updated information
                repaint()
                delta -= 1
                drawCount += 1
            }

            if (timer >= ONE_BILLION_NANO_SECOND) {
                println("FPS: $drawCount")
                drawCount = 0
                timer = 0
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