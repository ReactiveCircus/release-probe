package reactivecircus.releaseprobe.browsecollections

import android.view.LayoutInflater
import android.view.ViewGroup
import android.view.animation.AnimationUtils
import androidx.core.graphics.toColorInt
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import org.koin.core.KoinComponent
import org.koin.core.inject
import reactivecircus.blueprint.ui.extension.isAnimationOn
import reactivecircus.blueprint.ui.extension.setPrecomputedTextFuture
import reactivecircus.releaseprobe.browsecollections.databinding.ItemArtifactCollectionBinding
import reactivecircus.releaseprobe.core.util.AnimationConfigs
import reactivecircus.releaseprobe.core.util.ItemClickedCallback
import reactivecircus.releaseprobe.domain.artifactcollection.model.ArtifactCollection
import reactivecircus.releaseprobe.core.R as ResourcesR

class ArtifactCollectionsAdapter(
    private val itemClickedCallback: ItemClickedCallback<ArtifactCollection>,
    private val animate: Boolean
) : ListAdapter<ArtifactCollection, ArtifactCollectionViewHolder>(diffCallback), KoinComponent {

    private val animationConfigs: AnimationConfigs by inject()

    private var lastAnimatedPosition = -1

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): ArtifactCollectionViewHolder {
        val binding = ItemArtifactCollectionBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
        return ArtifactCollectionViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ArtifactCollectionViewHolder, position: Int) {
        val artifactCollection = getItem(position)
        holder.bind(artifactCollection, itemClickedCallback)

        if (animate && holder.itemView.context.isAnimationOn()) {
            val animation = AnimationUtils.loadAnimation(
                holder.itemView.context,
                ResourcesR.anim.slide_in_and_fade_in
            )
            animation.startOffset = (animationConfigs.defaultListItemAnimationStartOffset *
                    holder.adapterPosition).toLong()
            holder.itemView.startAnimation(animation)
            lastAnimatedPosition = holder.adapterPosition
        }
    }
}

class ArtifactCollectionViewHolder(
    private val binding: ItemArtifactCollectionBinding
) : RecyclerView.ViewHolder(binding.root) {

    fun bind(
        artifactCollection: ArtifactCollection,
        itemClickedCallback: ItemClickedCallback<ArtifactCollection>
    ) {
        val cardColor = artifactCollection.themeColor.toColorInt()
        binding.run {
            rootCardView.setCardBackgroundColor(cardColor)
            artifactCollectionNameTextView.setPrecomputedTextFuture(artifactCollection.name)
            artifactCollectionDescriptionTextView.setPrecomputedTextFuture(artifactCollection.description)

            root.setOnClickListener { itemClickedCallback(artifactCollection) }
        }
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
