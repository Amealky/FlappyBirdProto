package com.flappybirdproto.States

import com.badlogic.gdx.graphics.OrthographicCamera
import com.badlogic.gdx.graphics.g2d.SpriteBatch
import com.badlogic.gdx.math.Vector3

abstract class State {

    protected var cam : OrthographicCamera = OrthographicCamera()
    protected var mouse : Vector3 = Vector3()

    protected abstract fun handleInput()
    abstract fun update(dt : Float)
    abstract fun render(sb : SpriteBatch)
    abstract fun dispose()



}