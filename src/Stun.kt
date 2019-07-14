import de.javawi.jstun.attribute.ChangeRequest
import de.javawi.jstun.attribute.MappedAddress
import de.javawi.jstun.attribute.MessageAttributeInterface
import de.javawi.jstun.attribute.UnknownMessageAttributeException
import de.javawi.jstun.header.MessageHeader
import de.javawi.jstun.header.MessageHeaderInterface
import java.net.*

fun getMappedAddress(socket: DatagramSocket): InetSocketAddress {
//    val stunServers = ("23.21.150.121:3478\n" +
//            /* ... */
//            "stunserver.org:3478").split('\n')

    val sendMH = MessageHeader(MessageHeaderInterface.MessageHeaderType.BindingRequest)
    sendMH.generateTransactionID()
    sendMH.addMessageAttribute(ChangeRequest())
    val data = sendMH.bytes
    val receivedData = ByteArray(500)
    val prevTimeout = socket.soTimeout
    socket.soTimeout = 100
    val packet = DatagramPacket(data, data.size)
    val receivedPacket = DatagramPacket(receivedData, receivedData.size)
    stunServers.forEach { it ->
        try {
            packet.address = InetAddress.getByName(it.split(':').first())
            packet.port = Integer.parseInt(it.split(':').elementAtOrNull(1) ?: "3478")
            socket.send(packet)
            socket.receive(receivedPacket)
            val receiveMH = MessageHeader(MessageHeaderInterface.MessageHeaderType.BindingRequest)
            receiveMH.parseAttributes(receivedData)
            val ma = receiveMH.getMessageAttribute(MessageAttributeInterface
                .MessageAttributeType.MappedAddress) as MappedAddress
            socket.soTimeout = prevTimeout
            return InetSocketAddress(ma.address.inetAddress, ma.port)
        } catch (e: UnknownHostException) {
        } catch (e: SocketTimeoutException) {
        } catch (e: UnknownMessageAttributeException) {
        } finally {
            println()
        }
    }

    return InetSocketAddress(InetAddress.getLocalHost(), -1)
}

val stunServers = ("23.21.150.121:3478\n" +
        "iphone-stun.strato-iphone.de:3478\n" +
        "numb.viagenie.ca:3478\n" +
        "s1.taraba.net:3478\n" +
        "s2.taraba.net:3478\n" +
        "stun.12connect.com:3478\n" +
        "stun.12voip.com:3478\n" +
        "stun.1und1.de:3478\n" +
        "stun.2talk.co.nz:3478\n" +
        "stun.2talk.com:3478\n" +
        "stun.3clogic.com:3478\n" +
        "stun.3cx.com:3478\n" +
        "stun.a-mm.tv:3478\n" +
        "stun.aa.net.uk:3478\n" +
        "stun.acrobits.cz:3478\n" +
        "stun.actionvoip.com:3478\n" +
        "stun.advfn.com:3478\n" +
        "stun.aeta-audio.com:3478\n" +
        "stun.aeta.com:3478\n" +
        "stun.alltel.com.au:3478\n" +
        "stun.altar.com.pl:3478\n" +
        "stun.annatel.net:3478\n" +
        "stun.antisip.com:3478\n" +
        "stun.arbuz.ru:3478\n" +
        "stun.avigora.com:3478\n" +
        "stun.avigora.fr:3478\n" +
        "stun.awa-shima.com:3478\n" +
        "stun.awt.be:3478\n" +
        "stun.b2b2c.ca:3478\n" +
        "stun.bahnhof.net:3478\n" +
        "stun.barracuda.com:3478\n" +
        "stun.bluesip.net:3478\n" +
        "stun.bmwgs.cz:3478\n" +
        "stun.botonakis.com:3478\n" +
        "stun.budgetphone.nl:3478\n" +
        "stun.budgetsip.com:3478\n" +
        "stun.cablenet-as.net:3478\n" +
        "stun.callromania.ro:3478\n" +
        "stun.callwithus.com:3478\n" +
        "stun.cbsys.net:3478\n" +
        "stun.chathelp.ru:3478\n" +
        "stun.cheapvoip.com:3478\n" +
        "stun.ciktel.com:3478\n" +
        "stun.cloopen.com:3478\n" +
        "stun.colouredlines.com.au:3478\n" +
        "stun.comfi.com:3478\n" +
        "stun.commpeak.com:3478\n" +
        "stun.comtube.com:3478\n" +
        "stun.comtube.ru:3478\n" +
        "stun.cope.es:3478\n" +
        "stun.counterpath.com:3478\n" +
        "stun.counterpath.net:3478\n" +
        "stun.cryptonit.net:3478\n" +
        "stun.darioflaccovio.it:3478\n" +
        "stun.datamanagement.it:3478\n" +
        "stun.dcalling.de:3478\n" +
        "stun.decanet.fr:3478\n" +
        "stun.demos.ru:3478\n" +
        "stun.develz.org:3478\n" +
        "stun.dingaling.ca:3478\n" +
        "stun.doublerobotics.com:3478\n" +
        "stun.drogon.net:3478\n" +
        "stun.duocom.es:3478\n" +
        "stun.dus.net:3478\n" +
        "stun.e-fon.ch:3478\n" +
        "stun.easybell.de:3478\n" +
        "stun.easycall.pl:3478\n" +
        "stun.easyvoip.com:3478\n" +
        "stun.efficace-factory.com:3478\n" +
        "stun.einsundeins.com:3478\n" +
        "stun.einsundeins.de:3478\n" +
        "stun.ekiga.net:3478\n" +
        "stun.epygi.com:3478\n" +
        "stun.etoilediese.fr:3478\n" +
        "stun.eyeball.com:3478\n" +
        "stun.faktortel.com.au:3478\n" +
        "stun.freecall.com:3478\n" +
        "stun.freeswitch.org:3478\n" +
        "stun.freevoipdeal.com:3478\n" +
        "stun.fuzemeeting.com:3478\n" +
        "stun.gmx.de:3478\n" +
        "stun.gmx.net:3478\n" +
        "stun.gradwell.com:3478\n" +
        "stun.halonet.pl:3478\n" +
        "stun.hellonanu.com:3478\n" +
        "stun.hoiio.com:3478\n" +
        "stun.hosteurope.de:3478\n" +
        "stun.ideasip.com:3478\n" +
        "stun.imesh.com:3478\n" +
        "stun.infra.net:3478\n" +
        "stun.internetcalls.com:3478\n" +
        "stun.intervoip.com:3478\n" +
        "stun.ipcomms.net:3478\n" +
        "stun.ipfire.org:3478\n" +
        "stun.ippi.fr:3478\n" +
        "stun.ipshka.com:3478\n" +
        "stun.iptel.org:3478\n" +
        "stun.irian.at:3478\n" +
        "stun.it1.hr:3478\n" +
        "stun.ivao.aero:3478\n" +
        "stun.jappix.com:3478\n" +
        "stun.jumblo.com:3478\n" +
        "stun.justvoip.com:3478\n" +
        "stun.kanet.ru:3478\n" +
        "stun.kiwilink.co.nz:3478\n" +
        "stun.kundenserver.de:3478\n" +
        "stun.l.google.com:19302\n" +
        "stun.linea7.net:3478\n" +
        "stun.linphone.org:3478\n" +
        "stun.liveo.fr:3478\n" +
        "stun.lowratevoip.com:3478\n" +
        "stun.lugosoft.com:3478\n" +
        "stun.lundimatin.fr:3478\n" +
        "stun.magnet.ie:3478\n" +
        "stun.manle.com:3478\n" +
        "stun.mgn.ru:3478\n" +
        "stun.mit.de:3478\n" +
        "stun.mitake.com.tw:3478\n" +
        "stun.miwifi.com:3478\n" +
        "stun.modulus.gr:3478\n" +
        "stun.mozcom.com:3478\n" +
        "stun.myvoiptraffic.com:3478\n" +
        "stun.mywatson.it:3478\n" +
        "stun.nas.net:3478\n" +
        "stun.neotel.co.za:3478\n" +
        "stun.netappel.com:3478\n" +
        "stun.netappel.fr:3478\n" +
        "stun.netgsm.com.tr:3478\n" +
        "stun.nfon.net:3478\n" +
        "stun.noblogs.org:3478\n" +
        "stun.noc.ams-ix.net:3478\n" +
        "stun.node4.co.uk:3478\n" +
        "stun.nonoh.net:3478\n" +
        "stun.nottingham.ac.uk:3478\n" +
        "stun.nova.is:3478\n" +
        "stun.nventure.com:3478\n" +
        "stun.on.net.mk:3478\n" +
        "stun.ooma.com:3478\n" +
        "stun.ooonet.ru:3478\n" +
        "stun.oriontelekom.rs:3478\n" +
        "stun.outland-net.de:3478\n" +
        "stun.ozekiphone.com:3478\n" +
        "stun.patlive.com:3478\n" +
        "stun.personal-voip.de:3478\n" +
        "stun.petcube.com:3478\n" +
        "stun.phone.com:3478\n" +
        "stun.phoneserve.com:3478\n" +
        "stun.pjsip.org:3478\n" +
        "stun.poivy.com:3478\n" +
        "stun.powerpbx.org:3478\n" +
        "stun.powervoip.com:3478\n" +
        "stun.ppdi.com:3478\n" +
        "stun.prizee.com:3478\n" +
        "stun.qq.com:3478\n" +
        "stun.qvod.com:3478\n" +
        "stun.rackco.com:3478\n" +
        "stun.rapidnet.de:3478\n" +
        "stun.rb-net.com:3478\n" +
        "stun.refint.net:3478\n" +
        "stun.remote-learner.net:3478\n" +
        "stun.rixtelecom.se:3478\n" +
        "stun.rockenstein.de:3478\n" +
        "stun.rolmail.net:3478\n" +
        "stun.rounds.com:3478\n" +
        "stun.rynga.com:3478\n" +
        "stun.samsungsmartcam.com:3478\n" +
        "stun.schlund.de:3478\n" +
        "stun.services.mozilla.com:3478\n" +
        "stun.sigmavoip.com:3478\n" +
        "stun.sip.us:3478\n" +
        "stun.sipdiscount.com:3478\n" +
        "stun.sipgate.net:10000\n" +
        "stun.sipgate.net:3478\n" +
        "stun.siplogin.de:3478\n" +
        "stun.sipnet.net:3478\n" +
        "stun.sipnet.ru:3478\n" +
        "stun.siportal.it:3478\n" +
        "stun.sippeer.dk:3478\n" +
        "stun.siptraffic.com:3478\n" +
        "stun.skylink.ru:3478\n" +
        "stun.sma.de:3478\n" +
        "stun.smartvoip.com:3478\n" +
        "stun.smsdiscount.com:3478\n" +
        "stun.snafu.de:3478\n" +
        "stun.softjoys.com:3478\n" +
        "stun.solcon.nl:3478\n" +
        "stun.solnet.ch:3478\n" +
        "stun.sonetel.com:3478\n" +
        "stun.sonetel.net:3478\n" +
        "stun.sovtest.ru:3478\n" +
        "stun.speedy.com.ar:3478\n" +
        "stun.spokn.com:3478\n" +
        "stun.srce.hr:3478\n" +
        "stun.ssl7.net:3478\n" +
        "stun.stunprotocol.org:3478\n" +
        "stun.symform.com:3478\n" +
        "stun.symplicity.com:3478\n" +
        "stun.sysadminman.net:3478\n" +
        "stun.t-online.de:3478\n" +
        "stun.tagan.ru:3478\n" +
        "stun.tatneft.ru:3478\n" +
        "stun.teachercreated.com:3478\n" +
        "stun.tel.lu:3478\n" +
        "stun.telbo.com:3478\n" +
        "stun.telefacil.com:3478\n" +
        "stun.tis-dialog.ru:3478\n" +
        "stun.tng.de:3478\n" +
        "stun.twt.it:3478\n" +
        "stun.u-blox.com:3478\n" +
        "stun.ucallweconn.net:3478\n" +
        "stun.ucsb.edu:3478\n" +
        "stun.ucw.cz:3478\n" +
        "stun.uls.co.za:3478\n" +
        "stun.unseen.is:3478\n" +
        "stun.usfamily.net:3478\n" +
        "stun.veoh.com:3478\n" +
        "stun.vidyo.com:3478\n" +
        "stun.vipgroup.net:3478\n" +
        "stun.virtual-call.com:3478\n" +
        "stun.viva.gr:3478\n" +
        "stun.vivox.com:3478\n" +
        "stun.vline.com:3478\n" +
        "stun.vo.lu:3478\n" +
        "stun.vodafone.ro:3478\n" +
        "stun.voicetrading.com:3478\n" +
        "stun.voip.aebc.com:3478\n" +
        "stun.voip.blackberry.com:3478\n" +
        "stun.voip.eutelia.it:3478\n" +
        "stun.voiparound.com:3478\n" +
        "stun.voipblast.com:3478\n" +
        "stun.voipbuster.com:3478\n" +
        "stun.voipbusterpro.com:3478\n" +
        "stun.voipcheap.co.uk:3478\n" +
        "stun.voipcheap.com:3478\n" +
        "stun.voipfibre.com:3478\n" +
        "stun.voipgain.com:3478\n" +
        "stun.voipgate.com:3478\n" +
        "stun.voipinfocenter.com:3478\n" +
        "stun.voipplanet.nl:3478\n" +
        "stun.voippro.com:3478\n" +
        "stun.voipraider.com:3478\n" +
        "stun.voipstunt.com:3478\n" +
        "stun.voipwise.com:3478\n" +
        "stun.voipzoom.com:3478\n" +
        "stun.vopium.com:3478\n" +
        "stun.voxgratia.org:3478\n" +
        "stun.voxox.com:3478\n" +
        "stun.voys.nl:3478\n" +
        "stun.voztele.com:3478\n" +
        "stun.vyke.com:3478\n" +
        "stun.webcalldirect.com:3478\n" +
        "stun.whoi.edu:3478\n" +
        "stun.wifirst.net:3478\n" +
        "stun.wwdl.net:3478\n" +
        "stun.xs4all.nl:3478\n" +
        "stun.xtratelecom.es:3478\n" +
        "stun.yesss.at:3478\n" +
        "stun.zadarma.com:3478\n" +
        "stun.zadv.com:3478\n" +
        "stun.zoiper.com:3478\n" +
        "stun1.faktortel.com.au:3478\n" +
        "stun1.l.google.com:19302\n" +
        "stun1.voiceeclipse.net:3478\n" +
        "stun2.l.google.com:19302\n" +
        "stun3.l.google.com:19302\n" +
        "stun4.l.google.com:19302\n" +
        "stunserver.org:3478").split('\n')