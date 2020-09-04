package com.stenleone.hawkai.util.intent_media_manager

import android.Manifest
import android.content.Intent
import android.content.pm.PackageManager
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import com.stenleone.hawkai.App
import com.stenleone.hawkai.util.constant.IntentConstant


class IntentMediaManager(val fragment: Fragment) {

    fun createGalleryIntent() {

        val intent = Intent()
        intent.type = "image/*"
        intent.action = Intent.ACTION_GET_CONTENT
        fragment.startActivityForResult(
            Intent.createChooser(intent, "Select Picture"),
            IntentConstant.GALLERY
        )
    }

    private fun createCameraIntent() {
        val intent = Intent("android.media.action.IMAGE_CAPTURE")
        fragment.startActivityForResult(intent, IntentConstant.CAMERA)
    }

    fun createCameraWithPermission() {

        val permissionStatus =
            ContextCompat.checkSelfPermission(App.appContext, Manifest.permission.CAMERA)

        if (permissionStatus == PackageManager.PERMISSION_GRANTED) {
            createCameraIntent()
        } else {
            fragment.activity.let {
                ActivityCompat.requestPermissions(
                    it!!, arrayOf(Manifest.permission.CAMERA),
                    IntentConstant.CAMERA
                )
            }
        }
    }
}