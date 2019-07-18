package reactivecircus.releaseprobe.testing.helper

import reactivecircus.releaseprobe.core.util.AnimationHelper

class ScreenTestAnimationHelper : AnimationHelper() {

    override val defaultStartOffset: Int
        get() = 0

    override val defaultListItemAnimationStartOffset: Int
        get() = 0

    override val adapterPayloadAnimationDuration: Int
        get() = 0
}
