package com.flappybirdproto.States

import com.badlogic.gdx.Gdx
import com.badlogic.gdx.graphics.Texture
import com.badlogic.gdx.graphics.g2d.SpriteBatch
import com.badlogic.gdx.math.Vector2
import com.flappybirdproto.FlappyBirdEngine
import com.flappybirdproto.Sprites.Bird
import com.flappybirdproto.Sprites.Tube
import com.badlogic.gdx.utils.Array

class PlayState : State() {

    private val CAM_SPEED = 80
    private val TUBE_SPACING : Int = 150
    private val TUBE_COUNT : Int = 4
    private val GROUND_Y_OFFSET : Int = -30

    private var bird : Bird = Bird(50, 300)
    private var bg : Texture = Texture("bg.png")

    private var ground : Texture = Texture("ground.png")
    private var groundPos1 : Vector2 = Vector2(cam.position.x - cam.viewportWidth / 2, GROUND_Y_OFFSET.toFloat())
    private var groundPos2 : Vector2 = Vector2( (cam.position.x - cam.viewportWidth / 2) + ground.width, GROUND_Y_OFFSET.toFloat())

    private var tubes : Array<Tube> = Array()


    init {
        //Setup viewport
        cam.setToOrtho(false, FlappyBirdEngine.SCREEN_WIDTH/4 ,
                FlappyBirdEngine.SCREEN_HEIGHT/4)

        //Init pipes
        for (i in 1..TUBE_COUNT){
            tubes.add(Tube(i * (TUBE_SPACING + Tube.TUBE_WIDTH)))

        }

    }

    override fun handleInput() {
        if (Gdx.input.justTouched()){
            bird.jump()
        }
    }

    //Each frame
    override fun update(dt: Float) {
        handleInput()
        scrollGround()

        //Follow the bird with the camera
        cam.position.x = bird.position.x + CAM_SPEED

        checkTubesCollision()

        //If the bird touch the ground we reset the game
        if(bird.position.y <= ground.height + GROUND_Y_OFFSET){
            FlappyBirdEngine.gsm.set(PlayState())
        }

        bird.update(dt)
        cam.update()
    }

    override fun render(sb: SpriteBatch) {
        sb.projectionMatrix = cam.combined
        sb.begin()

        sb.draw(bg, cam.position.x - (cam.viewportWidth/2), 0.toFloat())
        sb.draw(bird.birdAnimation.getCurrentFrame(), bird.position.x, bird.position.y)

        for(tube in tubes){
            sb.draw(tube.topTube, tube.posTopTube.x, tube.posTopTube.y)
            sb.draw(tube.bottomTube, tube.posBottomTube.x, tube.posBottomTube.y)
        }

        sb.draw(ground, groundPos1.x, groundPos1.y)
        sb.draw(ground, groundPos2.x, groundPos2.y)
        sb.end()
    }

    override fun dispose() {
        bg.dispose()
        bird.dispose()
        ground.dispose()
        for(tube in tubes){
            tube.dispose()
        }

        Gdx.app.debug("DISPOSE", "Play State Dispose")
    }

    private fun scrollGround(){
        if(cam.position.x - cam.viewportWidth /2 > groundPos1.x + ground.width){
            groundPos1.add((ground.width * 2).toFloat(), 0F)
        }

        if(cam.position.x - cam.viewportWidth /2 > groundPos2.x + ground.width){
            groundPos2.add((ground.width * 2).toFloat(), 0F)
        }
    }

    private fun checkTubesCollision() {
        for(tube in tubes){

            //If a pipe go trough the left side we reset it on the right to loop
            if(cam.position.x - (cam.viewportWidth/2) > tube.posTopTube.x + tube.topTube.width){
                tube.reposition(tube.posTopTube.x + ((Tube.TUBE_WIDTH + TUBE_SPACING) * TUBE_COUNT))
            }

            //If a pipe collide with the bird we reset the game
            if(tube.collides(bird.bounds)){
                FlappyBirdEngine.gsm.set(PlayState())
                return
            }

        }
    }
}