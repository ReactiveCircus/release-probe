package ychescale9.releaseprobe.testing.helper

import ychescale9.releaseprobe.util.AnimationHelper

class ScreenTestAnimationHelper : AnimationHelper() {

    override val defaultStartOffset: Int
        get() = 0

    override val defaultListItemAnimationStartOffset: Int
        get() = 0

    override val adapterPayloadAnimationDuration: Int
        get() = 0
}
