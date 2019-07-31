package reactivecircus.releaseprobe.testing

import reactivecircus.releaseprobe.core.util.AnimationConfigs

class TestAnimationConfigs : AnimationConfigs() {

    override val defaultStartOffset: Int
        get() = 0

    override val defaultListItemAnimationStartOffset: Int
        get() = 0

    override val adapterPayloadAnimationDuration: Int
        get() = 0
}
