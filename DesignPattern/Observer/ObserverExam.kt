interface Subscriber {
    fun update(videoTitle: String)
}

// YouTube Channel 클래스 (Subject)
class YouTubeChannel {
    private val subscribers = mutableListOf<Subscriber>()
    var latestVideoTitle: String = ""

    fun subscribe(subscriber: Subscriber) {
        subscribers.add(subscriber)
    }

    fun unsubscribe(subscriber: Subscriber) {
        subscribers.remove(subscriber)
    }

    fun notifySubscribers() {
        for (subscriber in subscribers) {
            subscriber.update(latestVideoTitle)
        }
    }

    fun uploadVideo(title: String) {
        latestVideoTitle = title
        println("Uploading video: $title")
        notifySubscribers()
    }
}

// 구독자 클래스 (Concrete Observer)
class ConcreteSubscriber(private val name: String) : Subscriber {
    override fun update(videoTitle: String) {
        println("$name received notification: New video uploaded - $videoTitle")
    }
}

fun main() {
    val channel = YouTubeChannel()
    val subscriber1 = ConcreteSubscriber("Subscriber 1")
    val subscriber2 = ConcreteSubscriber("Subscriber 2")

    channel.subscribe(subscriber1)
    channel.subscribe(subscriber2)

    channel.uploadVideo("Kotlin Observer Pattern Tutorial")

    channel.unsubscribe(subscriber1)

    channel.uploadVideo("Advanced Kotlin Features")
}