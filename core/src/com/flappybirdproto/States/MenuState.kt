package com.flappybirdproto.States

import com.badlogic.gdx.Gdx
import com.badlogic.gdx.graphics.Texture
import com.badlogic.gdx.graphics.g2d.SpriteBatch
import com.flappybirdproto.FlappyBirdEngine

class MenuState : State() {
    private var background : Texture = Texture("bg.png")
    private var playBtn : Texture = Texture("playbtn.png")


    override fun handleInput() {
        if(Gdx.input.justTouched()){
            FlappyBirdEngine.gsm.set(PlayState())
        }
    }

    override fun update(dt: Float) {
        handleInput()
    }

    override fun render(sb: SpriteBatch) {
        sb.begin()
        sb.draw(background, 0.toFloat(), 0.toFloat(), FlappyBirdEngine.SCREEN_WIDTH, FlappyBirdEngine.SCREEN_HEIGHT)
        sb.draw(playBtn, (FlappyBirdEngine.SCREEN_WIDTH /2) - playBtn.width , (FlappyBirdEngine.SCREEN_HEIGHT/2) - playBtn.height, 250.toFloat(), 150.toFloat())
        sb.end()

    }

    override fun dispose() {
        background.dispose()
        playBtn.dispose()

        Gdx.app.debug("DISPOSE : ", "Menu State Dispose")
    }




}