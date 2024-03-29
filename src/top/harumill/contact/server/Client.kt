package top.harumill.contact.server

import io.ktor.server.websocket.*
import io.ktor.websocket.*
import top.harumill.contact.Contact
import top.harumill.contact.UserInfo
import top.harumill.message.simpleMessage.SimpleMessage
import top.harumill.message.Message
import top.harumill.message.MessageChain
import top.harumill.message.objectToByte
import top.harumill.message.simpleMessage.PlainText
import top.harumill.utils.Logger

typealias Session = DefaultWebSocketServerSession

/**
 * 服务器上的客户端类，一个Client实例表示一个已经连接的客户端
 * 不应通过[SimpleMessage]发送
 */
class Client(val session: Session, id: Long) : Contact {
    companion object {
        const val serialVersionUID: Long = 11
    }

    override var info: UserInfo = UserInfo(id)

    override suspend fun sendMessage(message: Message) {
        sendMessage(SimpleMessage(message, UserInfo(0), info))
    }

    override suspend fun sendMessage(messageChain: MessageChain) {
        sendMessage(messageChain)
    }

    override suspend fun sendMessage(simpleMessage: SimpleMessage) {
        Logger.verbose(simpleMessage.toString())
        session.send(objectToByte(simpleMessage)!!)
    }

    suspend fun sendMessage(message: String) {
        sendMessage(PlainText(message))
    }

    override fun toString(): String {
        return info.toString()
    }
}

