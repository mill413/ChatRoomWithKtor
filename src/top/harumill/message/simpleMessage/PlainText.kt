package top.harumill.message.simpleMessage

import top.harumill.message.SingleMessage

/**
 * 文本消息
 * @property content 文本内容
 */
class PlainText(text: String) : SingleMessage {
    companion object {
        const val serialVersionUID: Long = 21
    }

    private val content: String = text

    override fun contentToString(): String {
        return content
    }

    override fun toString(): String {
        return content
    }
}