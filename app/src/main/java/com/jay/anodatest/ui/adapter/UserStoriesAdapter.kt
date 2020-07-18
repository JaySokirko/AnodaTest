package com.jay.anodatest.ui.adapter

import android.graphics.drawable.Drawable
import android.text.SpannableStringBuilder
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import android.widget.Toast.LENGTH_SHORT
import androidx.appcompat.widget.AppCompatImageView
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleObserver
import androidx.lifecycle.OnLifecycleEvent
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.jay.anodatest.R
import com.jay.anodatest.model.UserStories
import com.jay.anodatest.util.appendTextByChar
import com.jay.anodatest.util.common.diffUtil.BaseDiffUtil
import com.jay.anodatest.util.common.diffUtil.UserStoriesDiffUtil
import com.jay.anodatest.util.highlightLabeledText
import com.jay.anodatest.util.makeBold
import com.jay.anodatest.util.ui.gestures.SwipeTouchListener.Companion.SWIPE_LEFT
import com.jay.anodatest.util.ui.gestures.SwipeTouchListener.Companion.SWIPE_RIGHT
import com.jay.anodatest.util.ui.view.TextViewHelper
import com.jay.anodatest.view.DotsView
import com.jay.anodatest.view.ImageViewer
import de.hdodenhof.circleimageview.CircleImageView
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.disposables.CompositeDisposable
import io.reactivex.rxjava3.disposables.Disposable
import io.reactivex.rxjava3.schedulers.Schedulers
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.cancel
import kotlinx.coroutines.launch

//По-хорошему, тут можно было бы связать MVVM и этот адаптер, ну, или еще какой-то красивый и
//причесанный архитектурный подход придумать, но не вижу в этом смысла для данной задачи.
class UserStoriesAdapter : RecyclerView.Adapter<BaseViewHolder<UserStories>>(), LifecycleObserver {

    private val userStories: MutableList<UserStories> = mutableListOf()
    private val diffUtil: BaseDiffUtil<UserStories> = UserStoriesDiffUtil()
    private val textViewHelper = TextViewHelper()
    private val coroutineScope = CoroutineScope(Dispatchers.Default)
    private val disposable = CompositeDisposable()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BaseViewHolder<UserStories> {
        val view: View = LayoutInflater.from(parent.context)
            .inflate(R.layout.layout_user_story, parent, false)

        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: BaseViewHolder<UserStories>, position: Int) {
        holder.bind(userStories[position])
    }

    override fun getItemCount(): Int = userStories.size

    override fun getItemId(position: Int): Long = position.toLong()

    override fun getItemViewType(position: Int): Int = position

    fun setData(data: List<UserStories?>) {
        diffUtil.setData(oldList = userStories, newList = data.filterNotNull())
        userStories.apply { clear(); addAll(data.filterNotNull()) }
        DiffUtil.calculateDiff(diffUtil, true).dispatchUpdatesTo(this)
    }


    inner class ViewHolder(itemView: View) : BaseViewHolder<UserStories>(itemView) {

        private val userName: TextView = itemView.findViewById(R.id.user_name)
        private val userImage: CircleImageView = itemView.findViewById(R.id.user_image)
        private val place: TextView = itemView.findViewById(R.id.place)
        private val imageViewer: ImageViewer = itemView.findViewById(R.id.image_viewer)
        private val dotsView: DotsView = itemView.findViewById(R.id.dots_view)
        private val likedBy: TextView = itemView.findViewById(R.id.liked_by_list)
        private val otherLikedByCount: TextView = itemView.findViewById(R.id.other_like_count)
        private val commentContent: TextView = itemView.findViewById(R.id.comment_content)
        private val postedTimeAgo: TextView = itemView.findViewById(R.id.posted_time_ago)

        override fun bind(item: UserStories) {
            userName.text = item.userName
            place.text = item.place
            postedTimeAgo.text = item.timeSinceUpload

            setupProfileImage(item)
            setupLikedBy(item)
            setupTagsContent(item)
            setupImageViewer(item)

            clickHandler()
        }

        private fun setupProfileImage(item: UserStories) {
            Glide.with(itemView.context)
                .load(item.profileImage)
                .into(userImage)
        }

        private fun setupLikedBy(item: UserStories) {
            //сначала устанавливаем весь текст, который нам пришел
            likedBy.text = item.likedBy?.joinToString(separator = " ")

            coroutineScope.launch {
                //затем берем только тот текст, который на данный момент виден юзеру
                val visibleText: String? = textViewHelper.getVisibleText(likedBy)

                //разделяем этот текст запятыми, кроме последней позиции
                val appendedText: String? = visibleText?.appendTextByChar(",")

                //и устанавливаем во вьюшку
                likedBy.post { likedBy.text = appendedText }

                //количество записей видимых юзеру
                val visibleRecordsCount: Int? = visibleText?.split(" ")?.size
                //всего записей пришло
                val totalRecordsCount: Int? = item.likedBy?.size

                if (visibleRecordsCount != null && totalRecordsCount != null) {
                    //количество записей, которые юзер не видит
                    val otherRecordsCount: String =
                        (totalRecordsCount - visibleRecordsCount).toString()

                    //выводим на экран количество записей, которые юзеру невидно
                    //получится что-то на подобии такого: and 94 others
                    otherLikedByCount.post {
                        otherLikedByCount.text =
                            otherRecordsCount.makeBold()
                                .append(" ")
                                .append(resources.getString(R.string.others).makeBold())
                    }
                }
            }
        }

        private fun setupTagsContent(item: UserStories) {
            val userName: SpannableStringBuilder? = item.userName?.makeBold()

            val comment: SpannableStringBuilder? =
                item.tags?.highlightLabeledText(
                    resources.getColor(R.color.blue_600),
                    "#", "@"
                )

            commentContent.text = userName?.append(" ")?.append(comment)
        }

        private fun setupImageViewer(item: UserStories) {
            val imagesList: MutableList<Drawable> = mutableListOf()

            val subscribe: Disposable = Observable.fromIterable(item.storyImagesUrl)
                .map { url -> Glide.with(itemView.context).load(url).submit().get() }
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(
                    { image: Drawable -> imagesList.add(image) },

                    { error: Throwable -> error.printStackTrace() },

                    {
                        imageViewer.setImages(imagesList)
                        setupDotView()
                    })
            disposable.add(subscribe)
        }

        private fun setupDotView() {
            dotsView.setDots(imageViewer.imageCount())
            imageViewer.swipeObserver
                .subscribe { direction ->

                    when (direction) {
                        SWIPE_RIGHT -> {
                            dotsView.highlightPreviousDot()
                        }
                        SWIPE_LEFT -> {
                            dotsView.highlightNextDot()
                        }
                    }
                }
        }

        private fun clickHandler() {
            itemView.findViewById<AppCompatImageView>(R.id.more_btn).setOnClickListener {
                Toast.makeText(context, "More at $layoutPosition", LENGTH_SHORT).show()
            }

            itemView.findViewById<AppCompatImageView>(R.id.comment_btn).setOnClickListener {
                Toast.makeText(context, "Comment at $layoutPosition", LENGTH_SHORT).show()
            }

            itemView.findViewById<AppCompatImageView>(R.id.like_btn).setOnClickListener {
                Toast.makeText(context, "Like at $layoutPosition", LENGTH_SHORT).show()
            }

            itemView.findViewById<AppCompatImageView>(R.id.save_btn).setOnClickListener {
                Toast.makeText(context, "Save at $layoutPosition", LENGTH_SHORT).show()
            }

            itemView.findViewById<AppCompatImageView>(R.id.send_btn).setOnClickListener {
                Toast.makeText(context, "Send at $layoutPosition", LENGTH_SHORT).show()
            }
        }
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_DESTROY)
    fun onDestroy() {
        coroutineScope.cancel()
        disposable.clear()
        textViewHelper.clear()
    }
}
