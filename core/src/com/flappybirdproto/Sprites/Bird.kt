package com.flappybirdproto.Sprites

import com.badlogic.gdx.graphics.Texture
import com.badlogic.gdx.graphics.g2d.TextureRegion
import com.badlogic.gdx.math.Rectangle
import com.badlogic.gdx.math.Vector3

class Bird(x : Int, y : Int) {

    private val GRAVITY : Int = -15
    private val MOVEMENT : Int = 100

    private var velocity : Vector3 = Vector3(0.toFloat(), 0.toFloat(), 0.toFloat())

    var position : Vector3 = Vector3(x.toFloat(), y.toFloat() , 0.toFloat())

    private var birdAnimationTexture : Texture = Texture("birdanimation.png")

    var birdAnimation : Animation = Animation(TextureRegion(birdAnimationTexture), 3, 0.5F)


    var bounds : Rectangle = Rectangle(position.x, position.y, birdAnimation.getCurrentFrame().regionWidth.toFloat(),
            birdAnimation.getCurrentFrame().regionHeight.toFloat())

    fun update(dt : Float){
        birdAnimation.update(dt)

        applyVelocity(dt)

        applyGravity()

        updateColliderPos()

        //Block the bird at the top of the screen
        if(position.y < 0){
            position.y = 0.toFloat()
        }

    }

    fun applyGravity() {
        if(position.y > 0){
            velocity.add(0.toFloat(), GRAVITY.toFloat(), 0.toFloat())
        }
    }

    fun applyVelocity(dt : Float) {
        velocity.scl(dt)
        position.add(MOVEMENT.toFloat() * dt, velocity.y, 0.toFloat())
        velocity.scl(1/dt)
    }

    fun updateColliderPos() {
        bounds.setPosition(position.x, position.y)
    }

    fun jump(){
        velocity.y = 250.toFloat()
    }

    fun dispose(){
        birdAnimationTexture.dispose()
    }


}