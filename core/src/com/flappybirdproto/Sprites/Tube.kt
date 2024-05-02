package com.flappybirdproto.Sprites

import com.badlogic.gdx.graphics.Texture
import com.badlogic.gdx.math.Rectangle
import com.badlogic.gdx.math.Vector2
import java.util.*

class Tube(x: Int) {

    companion object {
        private const val FLUCTUATION : Int = 130
        private const val TUBE_GAP : Int = 100
        private const val LOWEST_OPENING : Int = 120
        const val TUBE_WIDTH : Int = 52
    }

    val topTube : Texture = Texture("toptube.png")
    val bottomTube : Texture = Texture("bottomtube.png")

    private var rand : Random = Random()

    var posTopTube : Vector2 = Vector2(x.toFloat(), (rand.nextInt(FLUCTUATION) + TUBE_GAP + LOWEST_OPENING).toFloat())
    var posBottomTube : Vector2 = Vector2(x.toFloat(), posTopTube.y - TUBE_GAP - bottomTube.height)

    private var boundsTop : Rectangle = Rectangle(posTopTube.x, posTopTube.y, topTube.width.toFloat(), topTube.height.toFloat())
    private var boundsBot : Rectangle = Rectangle(posBottomTube.x, posBottomTube.y, bottomTube.width.toFloat(), bottomTube.height.toFloat())


    fun reposition(x : Float){
        posTopTube.set(x, (rand.nextInt(FLUCTUATION) + TUBE_GAP + LOWEST_OPENING).toFloat())
        posBottomTube.set(x, posTopTube.y - TUBE_GAP - bottomTube.height)

        boundsTop.setPosition(posTopTube.x, posTopTube.y)
        boundsBot.setPosition(posBottomTube.x, posBottomTube.y)
    }

    fun collides(otherCollider : Rectangle) : Boolean{
        return otherCollider.overlaps(boundsTop) || otherCollider.overlaps(boundsBot)
    }

    fun dispose(){
        topTube.dispose()
        bottomTube.dispose()
    }


}