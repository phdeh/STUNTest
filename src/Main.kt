import java.net.*
import kotlin.concurrent.thread

fun main() {

    val socket = DatagramSocket(1488)
    val address1 = getMappedAddress(socket)

    println("Mapped address: ${address1.address}:${address1.port}")

    print(">")
    val stringAddress = readLine()?.split(':')

    if (stringAddress != null) {
        val address2 = InetSocketAddress(
            InetAddress.getByName(stringAddress[0]),
            (stringAddress.elementAtOrNull(1)?.toInt() ?: 0)
        )

        thread {
            val data = ByteArray(1) { 1 }
            val packet = DatagramPacket(data, data.size)
            packet.socketAddress = address2
            while (true) {
                socket.send(packet)
            }
        }
        thread {
            val data = ByteArray(32)
            val packet = DatagramPacket(data, data.size)
            socket.soTimeout = 500
            while (true) try {
                socket.receive(packet)
                println("${data[0]}")
            } catch (e: SocketTimeoutException) {
                println("timeout")
            }
        }
    }
}
