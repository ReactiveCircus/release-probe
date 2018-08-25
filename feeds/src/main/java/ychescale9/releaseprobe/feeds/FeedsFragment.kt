package ychescale9.releaseprobe.feeds

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import org.koin.androidx.scope.ext.android.scopedWith
import org.koin.dsl.path.moduleName
import ychescale9.releaseprobe.base.BaseFragment

class FeedsFragment : BaseFragment() {

//    private lateinit var feedsAdapter: FeedsAdapter
//
//    private val viewModel: FeedsViewModel
//
//    private val animationHelper: AnimationHelper

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        scopedWith(javaClass.kotlin.moduleName)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_feeds, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        scopedWith(javaClass.kotlin.moduleName)
        // TODO
    }
}
