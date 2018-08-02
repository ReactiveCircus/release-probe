package ychescale9.infra.extension

import android.content.Context
import android.content.Context.VIBRATOR_SERVICE
import android.os.Build
import android.os.VibrationEffect
import android.os.Vibrator

fun Context.vibrate(duration: Long) {
    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
        (getSystemService(VIBRATOR_SERVICE) as Vibrator)
                .vibrate(VibrationEffect.createOneShot(duration, VibrationEffect.DEFAULT_AMPLITUDE))
    } else {
        (getSystemService(VIBRATOR_SERVICE) as Vibrator).vibrate(duration)
    }
}
