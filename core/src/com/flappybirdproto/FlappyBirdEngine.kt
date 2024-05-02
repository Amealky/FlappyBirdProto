package com.flappybirdproto

import com.badlogic.gdx.Application
import com.badlogic.gdx.ApplicationAdapter
import com.badlogic.gdx.Gdx
import com.badlogic.gdx.graphics.GL20
import com.badlogic.gdx.graphics.g2d.SpriteBatch
import com.flappybirdproto.States.GameStateManager
import com.flappybirdproto.States.MenuState

class FlappyBirdEngine : ApplicationAdapter() {

    private lateinit var batch : SpriteBatch

    companion object {
        var SCREEN_WIDTH : Float = 0.toFloat()
        var SCREEN_HEIGHT : Float = 0.toFloat()

        var gsm : GameStateManager = GameStateManager()
    }


    override fun create() {

        Gdx.app.logLevel = Application.LOG_DEBUG

        SCREEN_WIDTH = Gdx.graphics.width.toFloat()
        SCREEN_HEIGHT = Gdx.graphics.height.toFloat()

        batch = SpriteBatch()
        gsm.push(MenuState())

    }

    override fun render() {
        Gdx.gl.glClearColor(0f, 1f, 0f, 1f)
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT)

        gsm.update(Gdx.graphics.deltaTime)
        gsm.render(batch)

    }

    override fun dispose() {
        batch.dispose()
    }
}