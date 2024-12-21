package com.ajcode404

import java.awt.event.KeyEvent
import java.awt.event.KeyListener

class KeyHandler : KeyListener {

    var upPressed: Boolean = false
        private set
    var downPressed: Boolean = false
        private set
    var leftPressed: Boolean = false
        private set
    var rightPressed: Boolean = false
        private set


    override fun keyTyped(e: KeyEvent) {
    }

    override fun keyPressed(e: KeyEvent) {
        when (e.keyCode) {
            KeyEvent.VK_W -> upPressed = true
            KeyEvent.VK_S -> downPressed = true
            KeyEvent.VK_A -> leftPressed = true
            KeyEvent.VK_D -> rightPressed = true
        }
    }

    override fun keyReleased(e: KeyEvent) {
        when (e.keyCode) {
            KeyEvent.VK_W -> upPressed = false
            KeyEvent.VK_S -> downPressed = false
            KeyEvent.VK_A -> leftPressed = false
            KeyEvent.VK_D -> rightPressed = false
        }
    }

}