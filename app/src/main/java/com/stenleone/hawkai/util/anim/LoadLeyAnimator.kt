package com.stenleone.hawkai.util.anim

import android.animation.AnimatorSet
import android.animation.ObjectAnimator
import android.view.View

class LoadLeyAnimator(private val ico: View, private val bc: View) {

    fun loadAnim() {

        fadeIn()
        val scaleDownX = ObjectAnimator.ofFloat(ico, View.SCALE_X, 1f, 0.4f)
        val scaleDownY = ObjectAnimator.ofFloat(ico, View.SCALE_Y, 1f, 0.4f)
        val rotate = ObjectAnimator.ofFloat(ico, View.ROTATION, 0f, 360f)

        val anim = AnimatorSet()

        scaleDownX.repeatMode = ObjectAnimator.REVERSE
        scaleDownX.repeatCount = ObjectAnimator.INFINITE
        scaleDownY.repeatMode = ObjectAnimator.REVERSE
        scaleDownY.repeatCount = ObjectAnimator.INFINITE
        rotate.repeatMode = ObjectAnimator.REVERSE
        rotate.repeatCount = ObjectAnimator.INFINITE

        anim.play(scaleDownX).with(scaleDownY).with(rotate)
        anim.duration = 800
        anim.start()
    }

    private fun fadeIn() {

        val bcAnim = ObjectAnimator.ofFloat(bc, View.ALPHA, bc.alpha, 0.6f)
        val icoAnim = ObjectAnimator.ofFloat(ico, View.ALPHA, ico.alpha, 1f)
        bcAnim.duration = 200
        icoAnim.duration = 200
        bcAnim.start()
        icoAnim.start()
    }

    fun fadeOut() {

        val bcAnim = ObjectAnimator.ofFloat(bc, View.ALPHA, bc.alpha, 0f)
        val icoAnim = ObjectAnimator.ofFloat(ico, View.ALPHA, ico.alpha, 0f)
        bcAnim.duration = 400
        icoAnim.duration = 400
        bcAnim.start()
        icoAnim.start()
    }
}