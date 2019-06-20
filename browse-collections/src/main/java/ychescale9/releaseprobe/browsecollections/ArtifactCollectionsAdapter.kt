package ychescale9.releaseprobe.browsecollections

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.AnimationUtils
import androidx.core.graphics.toColorInt
import androidx.core.text.PrecomputedTextCompat
import androidx.core.widget.TextViewCompat
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.item_artifact_collection.view.*
import ychescale9.infra.android.extension.isAnimationOn
import ychescale9.releaseprobe.domain.artifactcollection.model.ArtifactCollection
import ychescale9.releaseprobe.core.R as ResourcesR
import ychescale9.releaseprobe.core.util.AnimationHelper

class ArtifactCollectionsAdapter(
    private val actionListener: ActionListener,
    private val animationHelper: AnimationHelper
) : ListAdapter<ArtifactCollection, ArtifactCollectionViewHolder>(diffCallback) {
    private var lastAnimatedPosition = -1

    interface ActionListener {
        fun onItemClick(artifactCollection: ArtifactCollection)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ArtifactCollectionViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_artifact_collection, parent, false)
        return ArtifactCollectionViewHolder(view)
    }

    override fun onBindViewHolder(holder: ArtifactCollectionViewHolder, position: Int) {
        val artifactCollection = getItem(position)
        holder.bind(artifactCollection, actionListener)

        if (holder.adapterPosition > lastAnimatedPosition && holder.itemView.context.isAnimationOn()) {
            val animation = AnimationUtils.loadAnimation(holder.itemView.context, ResourcesR.anim.slide_in_and_fade_in)
            animation.startOffset = (animationHelper.defaultListItemAnimationStartOffset *
                    holder.adapterPosition).toLong()
            holder.itemView.startAnimation(animation)
            lastAnimatedPosition = holder.adapterPosition
        }
    }
}

class ArtifactCollectionViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

    fun bind(artifactCollection: ArtifactCollection, actionListener: ArtifactCollectionsAdapter.ActionListener) {
        val cardColor = artifactCollection.themeColor.toColorInt()
        itemView.run {
            rootCardView.setCardBackgroundColor(cardColor)

            artifactCollectionNameTextView.setTextFuture(PrecomputedTextCompat.getTextFuture(
                    artifactCollection.name,
                    TextViewCompat.getTextMetricsParams(artifactCollectionNameTextView),
                    null))

            artifactCollectionDescriptionTextView.setTextFuture(PrecomputedTextCompat.getTextFuture(
                    artifactCollection.description,
                    TextViewCompat.getTextMetricsParams(artifactCollectionDescriptionTextView),
                    null))
        }

        itemView.setOnClickListener { actionListener.onItemClick(artifactCollection) }
    }
}

private val diffCallback: DiffUtil.ItemCallback<ArtifactCollection> =
        object : DiffUtil.ItemCallback<ArtifactCollection>() {

            override fun areItemsTheSame(
                oldItem: ArtifactCollection,
                newItem: ArtifactCollection
            ) = oldItem.name == newItem.name

            override fun areContentsTheSame(
                oldItem: ArtifactCollection,
                newItem: ArtifactCollection
            ) = oldItem == newItem
        }
