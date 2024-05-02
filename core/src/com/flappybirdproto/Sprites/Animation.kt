package com.flappybirdproto.Sprites

import com.badlogic.gdx.graphics.g2d.TextureRegion
import com.badlogic.gdx.utils.Array

class Animation(region : TextureRegion, var frameCount : Int, cycleTime : Float) {

    private var frames : Array<TextureRegion> = Array()
    private var maxFrameTime : Float = cycleTime / frameCount
    private var currentFrameTime : Float = 0F

    private var frame : Int = 0

    init {
        val frameWidth : Int = region.regionWidth / frameCount
        for(i in 0..frameCount){
            frames.add(TextureRegion(region, i * frameWidth, 0, frameWidth, region.regionHeight))
        }


    }

    fun update(dt: Float){
        currentFrameTime += dt
        if(currentFrameTime > maxFrameTime){
            frame++
            currentFrameTime = 0F
        }

        if(frame >= frameCount){
            frame = 0
        }
    }

    fun getCurrentFrame() : TextureRegion{
        return frames.get(frame)
    }


}